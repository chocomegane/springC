package jp.co.rakus.ecommers.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.com.bytecode.opencsv.CSVReader;
import jp.co.rakus.ecommers.domain.Cinema;
import jp.co.rakus.ecommers.service.OrderListService;

/**
 * 映画の商品をDBに追加するためのクラス.
 * 
 * @author yusuke.nakano
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/admin")
// @MultipartConfig(maxFileSize=1024*1024*5, maxRequestSize=1024*1024*50)
public class InsertCinemaController {

	/** ListViewServiceを利用するためのDI */
	@Autowired
	private OrderListService service;

	@Autowired
	private ServletContext context;

	private static final int MAX_FILE_SIZE = 5242880; // 1024*1024*5 5MB =
														// 5242880byte

	@ModelAttribute
	public CinemaForm setUpCinemaForm() {
		return new CinemaForm();
	}

	@ModelAttribute
	public BulkCinemaForm setUpBulkCinemaForm() {
		return new BulkCinemaForm();
	}

	/**
	 * 初期ページを表示するメソッド.
	 * 
	 * @param model
	 *            requestスコープを扱うための変数
	 * @return insertCinema.jspへフォワード
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String index(Model model) {
		return "insertCinema";
	}

	/**
	 * 映画の商品を追加する処理を行うメソッド.
	 * 
	 * @param form
	 *            CinemaForm型の変数
	 * @param result
	 *            BindingResult型の変数
	 * @param model
	 *            requestスコープを扱うための変数
	 * @return insertCinema.jspへフォワード
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String output(@Validated CinemaForm form, BindingResult result, RedirectAttributes redirectAttributes,
			Model model) throws NumberFormatException {
		/*************************************************************************/
		System.err.println(form.getReleaseDate());
		// エラーチェック

		boolean errorFlagOfImage = false;
		boolean errorFlagOfTitle = false;

		if (form.getImagePath().getSize() > MAX_FILE_SIZE) {
			model.addAttribute("err3", "【容量オーバー】5MB以内の画像を選択してください");
			errorFlagOfImage = true;
		}

		if (result.hasErrors()) {
			if (form.getImagePath().getOriginalFilename().equals("")) {
				String err = "画像を選択してください";
				model.addAttribute("err", err);
				errorFlagOfImage = true;
			}
			// return index(model);
		}

		if (form.getImagePath().getOriginalFilename().equals("")) {
			String err = "画像を選択してください";
			model.addAttribute("err", err);
			errorFlagOfImage = true;
			// return "insertCinema";
		}

		// 追加
		List<Cinema> cinemaList = service.findAll();
		for (Cinema item : cinemaList) {
			if (form.getTitle().equals(item.getTitle())) {
				errorFlagOfTitle = true;
			}
		}

		if (errorFlagOfTitle == true) {
			String err2 = "そのタイトルはすでに使われています";
			model.addAttribute("err2", err2);
			if (errorFlagOfImage == true) {
				return "insertCinema";
			}
			return "insertCinema";
		}

		if (errorFlagOfImage == true) {
			return "insertCinema";
		}

		/*************************************************************************/
		try {
			// cinemaFormのreleaseDateがString型なので、Date型に変換
			String releaseDate = form.getReleaseDate();
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDate);

			// imagePath関係の処理
			// String path = context.getRealPath("/img/");
			// form.getImagePath().transferTo( new File( path +
			// form.getImagePath().getOriginalFilename() ));

			Cinema cinema = new Cinema();
			// 画像のパスをセット////////////////////////////////////////
			String encode = Base64.getEncoder().encodeToString(form.getImagePath().getBytes());
			String encodeImage = "data:;base64," + encode;
			cinema.setImagePath(encodeImage);
			/////////////////////////////////////////////////////
			cinema.setReleaseDate(date);

			BeanUtils.copyProperties(form, cinema);

			// ひとつ前の仕様→
			// cinema.setImagePath(form.getImagePath().getOriginalFilename());
			cinema.setPrice(form.getIntPrice());
			cinema.setTime(form.getIntTime());

			service.save(cinema);

			redirectAttributes.addFlashAttribute("message", "正常に登録が完了しました");
			return "redirect:/admin/insert";
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("不正な値が入力されました");
			return "insertCinema";
		}
	}

	/**
	 * 一括ダウンロード用メソッド.
	 * 
	 * @return
	 */
	// if ("jpg".equals(extension)) {
	// outputImageFile = new File(application.getRealPath("/img") + name + "." +
	// extension);
	// } else if ("mp3".equals(extension)) {
	// outputImageFile = new File(application.getRealPath("/msc") + name + "." +
	// extension);
	// } else if ("csv".equals(extension)) {
	// outputImageFile = new File(application.getRealPath("/csv") + name + "." +
	// extension);
	// }
	//
	@RequestMapping("/bulkDownload")
	public String BulkDownload() {
		return "bulkDownload";
	}

	/**
	 * @param form
	 *            ファイル読み込み
	 * @return 遷移
	 * @throws Exception
	 */
	@RequestMapping("/bulkDownload/exe")
	public String BulkInsert(BulkCinemaForm form, Model model) throws Exception {
		List<String> imgErr = new ArrayList<>();
		String path = null;
		System.out.println("通過:" + form.getDownloadType());

		if (form.getDownloadType().equals("imgCode")) {

			boolean errFlg = false;
			/* fileを読み込みプロジェクト内のフォルダに入れる */
			MultipartFile csvMultipartFile = form.getCsvFile();
			path = context.getRealPath("/tmp/upload/");
			csvMultipartFile.transferTo(new File(path + csvMultipartFile.getOriginalFilename()));
			//////////////////////////////////////////////////////////////////////////////////////////

			try {
				/* プロジェクト内のファイルをとってくる */
				File csvFile = new File(path + csvMultipartFile.getOriginalFilename());
				InputStreamReader is = new InputStreamReader(new FileInputStream(csvFile), "SJIS");
				CSVReader reader = new CSVReader(is);

				String[] cinemaInformation;
				while ((cinemaInformation = reader.readNext()) != null) {
					Cinema cinema = new Cinema();
					// 0→タイトル 1→金額 2→ジャンル 3→時間 4→公開日 5→メディアタイプ 6→作成会社 7→監督
					// 8→レーティング 9→詳細 10→画像

					cinema.setTitle(cinemaInformation[0]);
					cinema.setPrice(Integer.parseInt(cinemaInformation[1]));
					cinema.setGenre(cinemaInformation[2]);
					cinema.setTime(Integer.parseInt(cinemaInformation[3]));
					cinema.setReleaseDate(new SimpleDateFormat("yyyy/MM/dd").parse(cinemaInformation[4]));
					cinema.setMediaType(cinemaInformation[5]);
					cinema.setCompany(cinemaInformation[6]);
					cinema.setDirectedBy(cinemaInformation[7]);
					cinema.setRating(cinemaInformation[8]);
					cinema.setDescription(cinemaInformation[9]);
					cinema.setImagePath(cinemaInformation[10]);
					cinema.setDeleted(false);

					service.save(cinema);
				}
				is.close();
				reader.close();

				/* 取り入れたプロジェクトのファイルを作成 */
				csvFile.delete();
				return "bulkDownload";
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}

			return "bulkDownload";
		}
		if (form.getDownloadType().equals("imgPath"))

			try {
				/* path設定 */
				path = context.getRealPath("/tmp/upload/");
				/* 新しいディレクトリをつくる */
				File newdir = new File(path);
				newdir.mkdir();

				String dirName = null;
				/* イメージパスを使用する場合 */
				if (form.getDownloadType().equals("imgPath")) {
					boolean errFlg = false;
					/* csvfile取得しプロジェクトのフォルダに追加 */

					MultipartFile csvMultipartFile = form.getCsvFile();
					csvMultipartFile.transferTo(new File(path + csvMultipartFile.getOriginalFilename()));
					System.out.println("path確認1csvMultipartFile：" + path + csvMultipartFile.getOriginalFilename());

					/* zipfile取得しプロジェクトのフォルダに追加 */////////////////////////////////////////////////////////////////////////////////////////////////
					MultipartFile zipMultipartFile = form.getZipFile();
					zipMultipartFile.transferTo(new File(path + zipMultipartFile.getOriginalFilename()));
					System.out.println("path確認2zipMultipartFile：" + path + zipMultipartFile.getOriginalFilename());

					/* プロジェクト内のファイルをとってくる */
					File csvFile = new File(path + csvMultipartFile.getOriginalFilename());
					File zipFile = new File(path + zipMultipartFile.getOriginalFilename());

					System.out.println("path確認3csvFile：" + path + csvMultipartFile.getOriginalFilename());
					System.out.println("path確認4zipFile：" + path + zipMultipartFile.getOriginalFilename());

					/* 拡張子確認 */
					if ("zip".equals(getSuffix(zipFile.getName()))) {
						/* ZipInputStreamでオープンする */
						ZipInputStream is = new ZipInputStream(new FileInputStream(zipFile));
						/* ZipInputStream から ZipEntryを取り出す。 */

						dirName = zipFile.getName().substring(0, zipFile.getName().lastIndexOf("."));
						new File(path + dirName).mkdir();
						System.out.println("path確認5mkdir：" + path + dirName);
						/* 解凍に使う道具 */
						ZipEntry entry;
						while ((entry = is.getNextEntry()) != null) {
							// ディレクトリの場合はスキップ
							if (entry.isDirectory())
								continue;

							// ディレクトリ作成
							String[] dirs = entry.getName().split("/");
							String subDirName = dirName;
							for (int i = 0; i < dirs.length - 1; i++) {
								subDirName += "/" + dirs[i];
								System.out.println("path確認6subDirName：" + subDirName);
								new File(subDirName).mkdir();

							}

							// 指定のディレクトリに内容をファイル書き込み
							System.out.println("path確認os：" + dirName + "/" + entry.getName());
							OutputStream os = new FileOutputStream(path + "/" + entry.getName());// プロジェクト内のディレクトリに指定した名前で保存

							int i;
							while ((i = is.read()) != -1)
								os.write(i);
							is.closeEntry();
							os.close();
						}
						is.close();

					} else {

						String zipErrMassege = "zipfileを選択してください";
						model.addAttribute("zipErrMassege", zipErrMassege);
						errFlg = true;
					}

					if ("csv".equals(getSuffix(csvFile.getName()))) {
						String csvErrMassege = "csvfileを選択してください";
						model.addAttribute("csvErrMassege", csvErrMassege);
					}

					System.out.println("通過1：" + errFlg);

					// errがあった場合の処理を行います。
					if (errFlg) {
						System.err.println("エラー発生");

						// プロジェクト内のディレクトリを削除
						FileUtils.deleteDirectory(newdir);
						return "bulkDownload";
					}
					InputStreamReader is = new InputStreamReader(new FileInputStream(csvFile), "SJIS");
					CSVReader reader = new CSVReader(is);

					String[] cinemaInformation;

					while ((cinemaInformation = reader.readNext()) != null) {
						Cinema cinema = new Cinema();
						// 0→タイトル 1→金額 2→ジャンル 3→時間 4→公開日 5→メディアタイプ 6→作成会社 7→監督
						// 8→レーティング 9→詳細 10→画像

						cinema.setTitle(cinemaInformation[0]);
						cinema.setPrice(Integer.parseInt(cinemaInformation[1]));
						cinema.setGenre(cinemaInformation[2]);
						cinema.setTime(Integer.parseInt(cinemaInformation[3]));
						cinema.setReleaseDate(new SimpleDateFormat("yyyy/MM/dd").parse(cinemaInformation[4]));
						cinema.setMediaType(cinemaInformation[5]);
						cinema.setCompany(cinemaInformation[6]);
						cinema.setDirectedBy(cinemaInformation[7]);
						cinema.setRating(cinemaInformation[8]);
						cinema.setDescription(cinemaInformation[9]);
						cinema.setImagePath(cinemaInformation[10]);
						cinema.setDeleted(false);
						System.out.println("通過2：" + checkFile(cinema, dirName));
						// ファイルの存在確認
						if (checkFile(cinema, dirName)) {

							String imgPath = path + "/" + dirName + "/" + cinema.getImagePath();
							byte[] imgFile = readFileToByte(imgPath);
							String encode = Base64.getEncoder().encodeToString(imgFile);
							String encodeImage = "data:;base64," + encode;
							cinema.setImagePath(encodeImage);
							System.out.println("通過3：" + checkFile(cinema, dirName));
							service.save(cinema);

						}
					}
					/* 取り入れたプロジェクトのファイルを削除 */
					is.close();
					reader.close();
					System.out.println(newdir);
					FileUtils.deleteDirectory(newdir);
					return "bulkDownload";
				}
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}

		String errMessage = "予期せぬエラーが起きましたもう一度failお確かめください";
		model.addAttribute("errMessage", errMessage);
		System.err.println("通過5：NG");
		return "bulkDownload";

	}

	/**
	 * 拡張子をとるメソッド
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {
		if (fileName == null) {
			return null;
		}
		int point = fileName.lastIndexOf(".");
		if (point != -1) {
			return fileName.substring(point + 1);
		}
		return fileName;
	}

	/**
	 * fileが存在するか確認するメソッド
	 * 
	 * @param cinema
	 * @param dirName
	 * @return
	 */
	private static boolean checkFile(Cinema cinema, String dirName) {
		try {

			File file = new File(dirName + "/" + cinema.getImagePath());
			return true;
		} catch (NullPointerException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * ファイルを読み込み、その中身をバイト配列で取得する
	 *
	 * @param filePath
	 *            対象ファイルパス
	 * @return 読み込んだバイト配列
	 * @throws Exception
	 *             ファイルが見つからない、アクセスできないときなど
	 */
	private byte[] readFileToByte(String filePath) throws Exception {
		byte[] b = new byte[1];
		FileInputStream fis = new FileInputStream(filePath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while (fis.read(b) > 0) {
			baos.write(b);
		}
		baos.close();
		fis.close();
		b = baos.toByteArray();

		return b;
	}
}
