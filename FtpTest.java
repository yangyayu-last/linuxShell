package cn.com.servlet;

import cn.com.reachway.audit.script.ScriptService;

public class FtpTest {

	public static void main(String[] args) throws Exception {
		/*ScriptService scriptService = new ScriptService();
		scriptService.mkFile("D:/uploadTest", "121212.sh", "#!/bin/bash  echo  'firstTest' ");*/
		ShellUtils.getShellFile("D:/uploadTest/888888.sh");
		
		Ftp ftp2 = Ftp.getSftpUtil("192.168.199.146", 22, "root", "123456");
		ftp2.upload("/var/www/", "D:/uploadTest");
		// 释放本地线程存储的sftp客户端
		ftp2.release();
		//关闭通道
		ftp2.closeChannel();
		//执行脚本
		RemoteShellTool2 tool = new RemoteShellTool2("192.168.199.146", "root",
				"123456", "utf-8");

		//String result = tool.exec("echo 'HelloWorld'");
		
		String result = tool.exec("sh /var/www/888888.sh 测试参数");
		System.out.print(result);
	}
}
