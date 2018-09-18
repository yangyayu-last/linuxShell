# linuxShell
Windows服务器下，安装Linux虚拟机。java远程上传、调用shell脚本，并拿到返回值。<br>


产品有一个需求，java项目是部署到服务器上的，前台传入Linux脚本，后台运行并拿到返回值。<br>
原来还打算使用Cygwin，环境安装完成以后，暂未找到怎么使用代码进行命令调用。<br>
Linux虚拟机思路：<br>
通过在服务器上安装Linux虚拟机，代码现在本机生成shell脚本，然后将shell脚本上传到Linux虚拟机，再通过java代码ssh远程连接Linux调用shell脚本，并接收返回值。<br>
首先安装Linux虚拟机，关闭防火墙。<br>
查看LinuxIP，测试时使用root账号。<br>



