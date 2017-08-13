<h3>**这里主要讲tomcat配置上的一些坑2**</h3>
首先看server.xml
我的域名是topbnt.com 在godadyy上买的，在域名管理里做的了IP的指向
指向到了我这个云主机
配置
一个Engine 就是一个Tomcat 实例可以配置多个Engine
``<Engine name="Catalina" defaultHost="www.topbnt.com">``




