package com.coderising.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;
import com.coderising.download.impl.ConnectionImpl;

public class DownloadThread extends Thread{
	public volatile boolean hasFinished = false;

	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		String fileName = "E:\\40_160829170958_1.jpg";
		File file = new File(fileName);
			
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			raf.seek(startPos);
			raf.write(conn.read(startPos, endPos));
			raf.close();
			ConnectionImpl connImpl = (ConnectionImpl) conn;
			conn.close();
			hasFinished=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
}