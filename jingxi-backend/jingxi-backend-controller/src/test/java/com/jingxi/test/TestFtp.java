//package com.jingxi.test;
//
//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.io.FileInputStream;
//
//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.junit.Test;
//import com.jingxi.common.util.FtpUtil;
//
//public class TestFtp {
//
//	@Test
//	public void testFtp() throws Exception {
//		FTPClient ftpClient = new FTPClient();
//		ftpClient.connect("192.168.179.128");
//		ftpClient.login("ftpuser", "12345678");
//		FileInputStream inputStream = new FileInputStream(new File("F:\\pictureTest\\black.jpg"));
//		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//		ftpClient.changeWorkingDirectory("/home/ftpuser/images");
//		ftpClient.storeFile("5009.jpg", inputStream);
//		inputStream.close();
//		ftpClient.logout();
//	}
//}
