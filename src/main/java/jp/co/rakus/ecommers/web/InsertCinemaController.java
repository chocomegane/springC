package jp.co.rakus.ecommers.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

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
	
	private static final int MAX_FILE_SIZE = 5242880; // 1024*1024*5 5MB = 5242880byte
		
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
	 * @param model requestスコープを扱うための変数
	 * @return insertCinema.jspへフォワード
	 */
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String index(Model model) {
		return "insertCinema";
	}
	
	/**
	 * 映画の商品を追加する処理を行うメソッド.
	 * @param form CinemaForm型の変数
	 * @param result BindingResult型の変数
	 * @param model requestスコープを扱うための変数
	 * @return insertCinema.jspへフォワード
	 */
	@RequestMapping(value = "/insert", method=RequestMethod.POST)
	public String output(@Validated CinemaForm form, BindingResult result, RedirectAttributes redirectAttributes, Model model) throws NumberFormatException {
		/*************************************************************************/
		System.err.println(form.getReleaseDate());
		// エラーチェック
		
		boolean errorFlagOfImage = false;
		boolean errorFlagOfTitle = false;
		
		if(form.getImagePath().getSize() > MAX_FILE_SIZE) {
			model.addAttribute("err3", "【容量オーバー】5MB以内の画像を選択してください");
			errorFlagOfImage = true;
		}
		
		if(result.hasErrors()) {
			if(form.getImagePath().getOriginalFilename().equals("")) {
				String err = "画像を選択してください";
				model.addAttribute("err", err);
				errorFlagOfImage = true;
			}
			// return index(model);
		}
				
		if(form.getImagePath().getOriginalFilename().equals("")) {
			String err = "画像を選択してください";
			model.addAttribute("err", err);
			errorFlagOfImage = true;
			// return "insertCinema";
		}
		
		// 追加
		List<Cinema> cinemaList = service.findAll();
		for(Cinema item : cinemaList) {
			if(form.getTitle().equals(item.getTitle())) {
				errorFlagOfTitle = true;
			}
		}
		
		if(errorFlagOfTitle == true) {
			String err2 = "そのタイトルはすでに使われています";
			model.addAttribute("err2", err2);
			if(errorFlagOfImage == true) {
				return "insertCinema";
			}
			return "insertCinema";
		}
		
		if(errorFlagOfImage == true) {
			return "insertCinema";
		}
		
		/*************************************************************************/
		try {
			// cinemaFormのreleaseDateがString型なので、Date型に変換
			String releaseDate = form.getReleaseDate();
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDate);
			
			
			// imagePath関係の処理
//			String path = context.getRealPath("/img/");
//			form.getImagePath().transferTo( new File( path + form.getImagePath().getOriginalFilename() ));
			
			
			Cinema cinema = new Cinema();
			//画像のパスをセット////////////////////////////////////////
			String encode = Base64.getEncoder().encodeToString(form.getImagePath().getBytes());
			String encodeImage = "data:;base64," + encode;
			cinema.setImagePath(encodeImage);
			/////////////////////////////////////////////////////
			cinema.setReleaseDate(date);
						
			BeanUtils.copyProperties(form, cinema);
						
//			ひとつ前の仕様→ cinema.setImagePath(form.getImagePath().getOriginalFilename());
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
		 * @return
		 */

		@RequestMapping("/bulkDownload")
		public String BulkDownload()
		{
			return "bulkDownload";
		}
		
		
		
		@RequestMapping("/bulkDownload/exe")
		public String BulkInsert(BulkCinemaForm form) throws IllegalStateException, IOException, ParseException
		{
			MultipartFile csvMultipartFile=  form.getCsvFile();
			String path = context.getRealPath("/tmp/upload/");
			csvMultipartFile.transferTo( new File( path + csvMultipartFile.getOriginalFilename() ));
			try{
				File csvFile = new File(path + csvMultipartFile.getOriginalFilename());
				CSVReader reader= new CSVReader(new FileReader(csvFile));
				String[] cinemaInformation;
				 while((cinemaInformation = reader.readNext()) != null){
					 Cinema cinema = new Cinema();
					    cinema.setTitle(cinemaInformation[0]);
 					    cinema.setPrice(Integer.parseInt(cinemaInformation[1]));
					    cinema.setGenre(cinemaInformation[2]);
					    cinema.setTime(Integer.parseInt(cinemaInformation[3]));
					    cinema.setReleaseDate( new SimpleDateFormat("yyyy/MM/dd").parse(cinemaInformation[4]));
					    cinema.setMediaType(cinemaInformation[5]);
					    cinema.setCompany(cinemaInformation[6]);
					    cinema.setDirectedBy(cinemaInformation[7]);
					    cinema.setRating(cinemaInformation[8]);
					    cinema.setDescription(cinemaInformation[9]);
					    cinema.setImagePath(cinemaInformation[10]);
					    cinema.setDeleted(false);
					    
					    service.save(cinema);    
				 }
			}catch(FileNotFoundException e)
			{
				System.out.println(e);
			}catch (IOException e) {
				System.out.println(e);
			}
				
			
			
			return "bulkDownload";
		}
		
	}
