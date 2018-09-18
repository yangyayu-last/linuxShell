package cn.com.servlet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * ssh连接Linux
 * 执行脚本
 * 获取返回值
 * @author Administrator
 *
 */
public class RemoteShellTool2 {

	private Connection conn;
	private String ipAddr;
	private String charset = Charset.defaultCharset().toString();
	private String userName;
	private String password;

	public RemoteShellTool2(String ipAddr, String userName, String password,
			String charset) {
		this.ipAddr = ipAddr;
		this.userName = userName;
		this.password = password;
		if (charset != null) {
			this.charset = charset;
		}
	}

	public boolean login() throws IOException {
		conn = new Connection(ipAddr);
		conn.connect(); // 连接
		return conn.authenticateWithPassword(userName, password); // 认证
	}

	public String exec(String cmds) {
		InputStream in = null;
		String result = "";
		try {
			if (this.login()) {
				Session session = conn.openSession(); // 打开一个会话
				session.execCommand(cmds);
				
				in = session.getStdout();
				//InputStream is = new FileInputStream("/test.sh");
				result = this.processStdout(in, this.charset);
				session.close();
				conn.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	/**
	 * 旧的，解析返回值的代码
	 */
	/*public String processStdout(InputStream in, String charset) {
	
		byte[] buf = new byte[1024];
		StringBuffer sb = new StringBuffer();
		try {
			while (in.read(buf) != -1) {
				sb.append(new String(buf, charset));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}*/

    /** 
    * 解析脚本执行返回的结果集 
    * @author Ickes 
    * @param in 输入流对象 
    * @param charset 编码 
    * @since V0.1 
    * @return 
    *       以纯文本的格式返回 
    */  
    private String processStdout(InputStream in, String charset){  
        InputStream    stdout = new StreamGobbler(in);  
        StringBuffer buffer = new StringBuffer();;  
        try {  
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,charset));  
            String line=null;  
            while((line=br.readLine()) != null){  
               // buffer.append(line+"\n");  
                buffer.append(line);  
            }  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer.toString();  
    }  
}