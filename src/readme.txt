SmartStruts项目说明：

此项目为学习完struts1之后、在学习了java高级知识之后、随性而写

其实此框架类似于struts1实际上是一个大大的servlet、在web.xml当中
将所有的http请求交个这个大大的servlet进行处理、当第一个请求过来的时候、
根据web.xml当中的配置将请求交给servlet、tomcat容器负责创建这个servlet
实例化servlet调用servlet的init方法、在这个servlet的init方法里面、我们读取了smart-struts.xml的配置文件
其中读取smart-struts.xml配置文件的时候使用 commons-digester-1.8这个第三方工具类
这个工具类根据rule.xml中的规定解析用户配置的xml文件、将用户配置的xml文件
解析、将解析到的dom对象封装到MoudleConfig对象工厂当中、这里的xml解析器相当于
一个容器、而容器的最顶层存放的是MoudleConfig对象、将xml配置当中的action构造成一个ActionConifg
对象放入MoudleConfig对象当中、根据xml配置当中的formbean构造一个formBeanConfig对象
将次对象放入action当中、实际上就是讲xml的dom树用面向对象的语言表现出来、

当一个请求到来的时候、交个框架进行处理、框架解析请求参数、根据path属性在moudleConfig
中找到对应的action、根据action配置找到对应formbean实例化formbean、这里使用的
反射Class.formName方法将请求参数封装到formbean当中
因为提交上来的请求参数都是string类型的而实际类型不一定都是string、因此在这里我们使用了一个被称为
commons-beanutils-1.8.0.jar这个第三方工具类完成将请求参数封装到formbean当中
完成类型的转化、然后根据action的type属性找到对应的action并且利用反射进行实例化
在action中传入刚才实例化的formbean以及request和response对象、将他们传递给action的execute
方法、action处理后返回一个forword对象、此时从当前的actionConfig找到对应的forword
对象根据forword的配置选择跳转到的页面