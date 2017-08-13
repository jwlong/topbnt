<h3>Tomcat中更改网站根目录和默认页的配置方法</h3>
+ server.xml    
    - 如果我没有指定appBase目录，tomcat会默认读取webapps这个目录里的项目 
    -  如果指定了，这个appbases相当于目录的目录。
   下面的<Context path ="/" docBase="/home/ec2-user/projects/" 当http://www.xxx.com/ 中的最后一个'/'
   进行去访问时，会指向实际目录/home/ec2-user/projects/
   我这样做其实是想把项目内容直接通过http://www.xxx.com来访问
   
   ``   <Host name="www.xxx.com" appBase="webapps"
               unpackWARs="true" autoDeploy="true">
        	   <Context path="/" docBase="/home/ec2-user/projects/" />
         </Host>``
  
 + 第二种办法
    - 也可以在tomcat 里的conf\Catalina\www.xxx.com
    ()因为我的域名是www.xxx.com)下创建一个xml文件。
    也就是context的文件。
    ``<Context path="/TestPro" docBase="home/javaProject/TestPro/WebContent" debug="0" privileged="true">
      </Context>``
    <br>
    如上的，http://www.xxx.com/TestPro访问就会指向到/home/javaProject/TestPro/WebContent里去了  <br/>
   **特别注意**
   这个xml命名必须和发布路径一致如上面的，xml文件名必须为TestPro
 + 常用的可能就是我上面说的了，但实际上很多时候可能需要把项目发布到tomcat根目录中去
    - 为了保险，删除掉tomcat服务器中的webapps目录下的ROOT文件夹
    - 将xml中的path设置为空path="" .
    - 将xml文件中名改为ROOT.xml
    
        