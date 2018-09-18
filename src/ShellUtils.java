package cn.com.servlet;

import java.io.File;
import java.io.FileOutputStream;
 
import org.apache.http.util.TextUtils;
 
/**
 * 获取shell脚本工具类
 * 将shell脚本导出到本机目录
 * @author lenovo
 *
 */
public class ShellUtils {
 
	/**
	 * 获取shell的String
	 * @param tomcatName tomcat信息
	 * @param pathName -w参数精确查找信息
	 * @param seconds 多少秒执行一次shell
	 * @return
	 */
	public static String getShell(String content){
		StringBuffer sb=new StringBuffer();
		//sb.append("#!/bin/bash \n echo \"$1\"");
		sb.append(content);
		return winString2Linux(sb.toString());
	}
	
	/**
	 * 将shell文件输出到固定路径下
	 * @param tomcatName
	 * @param pathName
	 * @param seconds
	 * @param path
	 * @throws Exception
	 */
	public static void getShellFile(String path,String content) throws Exception{
		String shellString=getShell(content);
		FileOutputStream fos = new FileOutputStream(new File(path));
		fos.write(shellString.getBytes());
		fos.close();
	}
	
	/**
	 * 将windows下的shell文件转换为Linux下的shell文件
	 * @param content shell内容
	 * @return
	 */
	public static String winString2Linux(final String content) {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        final char[] chars = content.toCharArray();
        char curChar;
        for (int i =0 ; i < chars.length; i++) {
            curChar = chars[i];
            if ('\r' != curChar) {
                buffer.append(curChar);
            }
        }
        return buffer.toString();
}
	
	public static void main(String[] args) throws Exception {
		String winString2Linux = ShellUtils.winString2Linux("#!/bin/bash \n echo \"$1\"");
		ShellUtils.getShellFile("D:/uploadTest/aaa.sh","#!/bin/bash \n echo \"$1\"");
	}
}