package com.lord.learn.io.comm;

import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class TestCommIO {

	public static void main(String[] args) throws Exception {
		InputStream in = new URL( "http://jakarta.apache.org" ).openStream();
		 try {
			 System.out.println( IOUtils.toString( in ) );
			 
			 InputStream inputImage = new URL( "http://img10.gomein.net.cn/image/prodimg/production_image/201401/02/1114940050/1000569739_130.jpg" ).openStream();
			 byte[] picData = IOUtils.toByteArray(inputImage);
			 
		 } finally {
		   IOUtils.closeQuietly(in);
		 }
	}
}
