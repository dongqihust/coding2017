package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;


public class FileDownloader {
	
	String url;
	
	DownloadListener listener;
	
	ConnectionManager cm;
	

	public FileDownloader(String _url) {
		this.url = _url;
		
	}
	
	public void execute(){
		// ������ʵ����Ĵ��룬 ע�⣺ ��Ҫ�ö��߳�ʵ������
		// ��������������������ӿ�, ����Ҫд�⼸���ӿڵ�ʵ�ִ���
		// (1) ConnectionManager , ���Դ�һ�����ӣ�ͨ��Connection���Զ�ȡ���е�һ�Σ���startPos, endPos��ָ����
		// (2) DownloadListener, �����Ƕ��߳����أ� ���������Ŀͻ��˲�֪��ʲôʱ���������������Ҫʵ�ֵ�����
		//     �̶߳�ִ�����Ժ� ����listener��notifiedFinished������ �����ͻ��˾����յ�֪ͨ��
		// �����ʵ��˼·��
		// 1. ��Ҫ����ConnectionManager��open���������ӣ� Ȼ��ͨ��Connection.getContentLength��������ļ��ĳ���
		// 2. ��������3���߳����أ�  ע��ÿ���߳���Ҫ�ȵ���ConnectionManager��open����
		// Ȼ�����read������ read�������ж�ȡ�ļ��Ŀ�ʼλ�úͽ���λ�õĲ����� ����ֵ��byte[]����
		// 3. ��byte����д�뵽�ļ���
		// 4. ���е��̶߳���������Ժ� ��Ҫ����listener��notifiedFinished����
		
		// ����Ĵ�����ʾ�����룬 Ҳ����˵ֻ��һ���̣߳� ����Ҫ����ɶ��̵߳ġ�
		Connection conn1 = null;
		Connection conn2 = null;
		Connection conn3 = null;
		DownloadThread t1 = null;
		DownloadThread t2=null;
		DownloadThread t3=null;
		try {
			
			conn1= cm.open(this.url);
			conn2 = cm.open(this.url);
			conn3= cm.open(this.url);
			int length = conn1.getContentLength();	
			
			 	   
			 t1 =   new DownloadThread(conn1,0,length/3);		
			 t2 =  new DownloadThread(conn2,length/3,2*length/3);
			 t3=   new DownloadThread(conn3,2*length/3,length);
			t1.start();
			t2.start();
			t3.start();
			
		} catch (ConnectionException e) {			
			e.printStackTrace();
		}finally{
			if(conn1 != null){
				conn1.close();			
			}
			if(conn3 != null){
				conn3.close();
			}
			if(conn2 != null){
				conn2.close();
			}
			
		}
		while(t1.hasFinished&&t2.hasFinished&&t3.hasFinished){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listener.notifyFinished();
		}
		
		
		
		
	}
	
	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	
	
	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}
	
	public DownloadListener getListener(){
		return this.listener;
	}
	
}