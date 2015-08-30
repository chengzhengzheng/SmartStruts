使用技术：
a、smartstrusts框架  大型框架 加快软件开发速度 稳定性
b、jQuery.validate.js
c、dbcp连接池
d、工厂模式：同一类对象的创建  解决问题：对象创建  隐藏了对象的创建过程 封装了对象的创建  降低耦合
改善程序结构  MVC模式    jsp+javabean(小程序)    
MVC（大的项目）
--------技术架构---------
分层开发：
表现层+控制器+数据访问层
通用层
表现层使用jsp、jstl、jQuery技术
控制层使用smartstruts框架的控制器
业务层使用javabean
数据访问层使用DAO+工厂、dbcp连接池
通用层：共同的JavaBean  共同的工具类

--------Struts1-------- MVC是一种思想
1、Struts1与MVC
Struts1是一种MVC实现
C部分：ActionServlet组件、RequestProcessor组件
V部分：JSP组件、标签
M部分：采用JavaBean组件  ActionForm\Action


2、Struts1工作流程
http://localhost:8080/Struts1/login.do
1、从请求的URL中获取请求名：工程名到.do之间的部分
2、根据请求获取Action中配置的name属性创建ActionForm对象、
并将请求携带的信息封装到对象的属性中
（a）根据scope属性去指定范围内寻找ActionForm对象、寻找的key为attribute属相
（b）如果找到使用它封装请求信息
  	如果没有找到、新创建一个ActionForm对象、使用它封装表单信息
  	并且存入scope范围
3）根据Action配置name属性、创建ActionForm对象 	
common-beautif.jar 
4、根据Action配置当中type属性创建Action对象、并执行execute方法
返回一个ActionForword
5、根据返回的ActionForword对象获取视图、跳转进行页面提跳转
  a、根据forword定义中的redirect属性判断跳转方式
  b、如果属性为true则使用redirect
  c、如果属性为false则使用forword方式
  d、寻找< forword>元素配置信息时候、先去action元素配置中去找、如果找不到、再去<global-forword>元素中去找
  
  
  
  ===========smartStruts1=========
  1、控制器流程与Struts1相似
  2、不同点有一下几个细节
  
  1、Action组件的业务方法有所改变
  2、Sturst-config.xml配置有所改变
  为Action配置一个Method属性
  
  
  借助common-digester-1.8.jar  解析xml