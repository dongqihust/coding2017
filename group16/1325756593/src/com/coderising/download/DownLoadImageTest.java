package com.coderising.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadImageTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		URL url= new URL("http://07.imgmini.eastday.com/mobile/20170314/20170314233641_55eebddfcc265c75e2866fc086767d57_3.jpeg");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		InputStream is = connection.getInputStream();
		
		File f = new File("E:\\test.jpeg");
		if(!f.exists()){
			f.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(f);
		int c = 0;
		byte[] bytes = new byte[1024];
		while((c=is.read(bytes))!=-1){
			fos.write(bytes,0,c);
		}
		is.close();
		fos.close();
		
	
		
		

	}

}
