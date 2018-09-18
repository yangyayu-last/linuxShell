package cn.com.servlet;

import java.io.InputStream;
import java.util.Properties;

public class FtpTest {

	public static void main(String[] args) throws Exception {
		//给本机写Linux脚本文件
		ShellUtils.getShellFile("D:/uploadTest/888888.sh",ShellUtils.winString2Linux("#!/bin/bash \n echo \"$1\""));
		
		Ftp ftp2 = Ftp.getSftpUtil("192.168.199.146", 22, "root", "123456");
		ftp2.upload("/var/www/", "D:/uploadTest");
		// 释放本地线程存储的sftp客户端
		Ftp.release();
		//关闭通道
		ftp2.closeChannel();
		//执行脚本
		RemoteShellTool2 tool = new RemoteShellTool2("192.168.199.146", "root",
				"123456", "utf-8");
		
		//String result = tool.exec("echo 'HelloWorld'");
		
		String result = tool.exec("sh /var/www/888888.sh jjflkjdf");
		System.out.print(result);
	}
	
}
