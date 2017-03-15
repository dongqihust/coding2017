
package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	private HttpURLConnection connection;
	private InputStream is;


	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public ConnectionImpl(HttpURLConnection connection) throws IOException {
		super();
		this.connection = connection;
		is= this.connection.getInputStream();
	}

	public HttpURLConnection getConnection() {
		return connection;
	}

	public void setConnection(HttpURLConnection connection) {
		this.connection = connection;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		byte[] retBytes = new byte[endPos-startPos];
		is.skip(startPos);
		int hasRead = 0;
		int readSum = 0;
		byte[] bytes = new byte[1024*4];
		while((hasRead=is.read(bytes))!=-1){	
		//如果读入的数量+开始位置超出边界，只需要填满bytes[]即可
			if(startPos+readSum+hasRead>=endPos){
				System.arraycopy(bytes, 0, retBytes, readSum, endPos-readSum-startPos);				
				break;
			}else{
				System.arraycopy(bytes, 0, retBytes, readSum, hasRead);				
			}		
			readSum+=hasRead;
		}
		is.close();
		return retBytes;
	}

	@Override
	public int getContentLength() {
		
		return connection.getContentLength();
	}

	@Override
	public void close() {
		
		
	}

}