package jp.co.rakus.ecommers.domain;

import java.io.File;

public class Logic {
	public  void delete(String path) {
		  File f = new File(path);
		  // 存在チェック
		  if ( !f.exists() )
		  // ファイルの場合
		  if ( f.isFile() ) {
		    f.delete();
		  }
		  // ディレクトリの場合
		  if( f.isDirectory() ) {
		    // ディレクトリ内の一覧を取得
		    File[] files = f.listFiles();
		    // ディレクトリ内のファイルもしくはディレクトリを削除する
		    for ( int i = 0; i < files.length; i++ ) {
		      // 再帰処理
		      delete( files[i].getPath() );
		    }
		    // ディレクトリ削除
		    f.delete();

		  }
		
		}


}
