package org.smartstruts.action;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.smartstruts.config.ActionConfig;
import org.smartstruts.config.FormBeanConfig;
import org.smartstruts.config.ForwordConfig;
import org.smartstruts.config.MoudleConfig;

public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * smartstruts框架启动读取配置文件smart-struts根据rule.xml中的规则 解析smart-struts.xml文件
	 * 将解析到的dom对象封装到MoudleConfig对象工厂当中、 到servlet方法当中 moudleConfig对象中已经存在了
	 * smart-struts.xml配置中的信息、将配置当中的 <form-beans> <form-bean name="loginform"
	 * type="org.tarena.smartstruts.form.LoginForm"></form-bean> </form-beans>
	 * 封装成一个FormBeanConfig对象 将<action path="/login" name="loginform"
	 * type="org.tarena.struts.action.LoginAction" attribute="loginform"
	 * scope="session" method="login"> <forward name="success"
	 * path="/success.jsp"></forward> <forward name="fail"
	 * path="/fail.jsp"></forward> </action> 封装成一个action对象
	 * 其中这个使用的xml解析器相当于一个容器、容器的堆栈的最顶层存放的是moudleConfig对象
	 * 
	 */
	private MoudleConfig moudleConfig = new MoudleConfig();
	private static final String RULE = "org/smartstruts/config/rule.xml";
	// smart-struts配置文件所在位置
	private static final String CONFIG = "smart-struts.xml";
	public void init() {
		/**
		 * 初始化的时候讲xml变成moudleConfig对象
		 */
		try{
		// 解析xml文件 commons-digester-1.8.jar
			Digester digester = DigesterLoader.createDigester(ActionServlet.class.getClassLoader().getResource(RULE));
			digester.push(moudleConfig);
			digester.parse(ActionServlet.class.getClassLoader().getResource(CONFIG));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1、从请求的URL中获取请求名：工程名到.do之间的部分
			String path = processPath(request);
			// (2).根据请求获取对应的Action配置.
			System.out.println("path:"+path);
			ActionConfig actionConfig = moudleConfig.findActionConfig(path);
			System.out.println("ActionConfig:"+actionConfig.getName());
			if (actionConfig != null) {
				// (3)根据<action>配置中的name属性创建ActionForm对象,
				// 并将请求携带的信息封装到对象属性中 利用commons-beanutils-1.8.0.jar
				ActionForm form = processActionForm(actionConfig, request,
						response);// 将请求参数封装到ActionForm当中
				System.out.println("form:"+form.getClass().getName());
//				HttpSession session = request.getSession();
//				session.setAttribute(form.getClass().getSimpleName(), form);
				if (form != null) {
					// 执行Action中的execute方法、并返回ForwordConfig
					String forwordName = processAction(actionConfig, form,
							request, response);
					System.out.println("fordwordName:"+forwordName);
					processForward(forwordName, actionConfig, request, response);
				}
			}
			// 4、根据Action配置当中type属性创建Action对象、并执行execute方法
			// 返回一个ActionForword
		} catch (Exception e) {
			throw new ServletException();
		}
	}

	private void processForward(String forwordName, ActionConfig actionConfig,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, IOException {
		ForwordConfig forwordConfig = actionConfig
				.findForwordConfig(forwordName);
//		if (forwordConfig == null) {
//			forwordConfig = moudleConfig.findGlobalForword(forwordName);
//		}
		if (forwordConfig != null) {
			String forwordPath = forwordConfig.getPath();
			if (forwordPath != null) {
				if (!forwordConfig.isRedirect()) {
					request.setAttribute("test", "cz");
					request.getRequestDispatcher(forwordPath).forward(request,
							response);
					
				} else {
					String contextPath = request.getContextPath();
					response.sendRedirect(contextPath + forwordPath);
				}
			}
		}

	}

	/**
	 * 实例化Action业务逻辑控制器
	 * 使用反射机制调用Action中的方法
	 * 进行业务逻辑处理
	 * 其中Action中的方法接受form(里面封装着请求参数)
	 * 返回一个forward对象、表示返回的东西
	 */
	private String processAction(ActionConfig actionConfig, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String actionType = actionConfig.getType();
		// 用户所有的action处理都实现Action抽象类
		Action action = (Action) Class.forName(actionType).newInstance();
		System.out.println("Action:"+action.getClass().getName());
		String methodName = actionConfig.getMethod();
		// 获取method对象
		Method method = action.getClass().getMethod(
				methodName,
				new Class[] { ActionForm.class, HttpServletRequest.class,
						HttpServletResponse.class });
		String forword = (String) method.invoke(action, new Object[] { form,
				request, response });
		return forword;
	}

	/**
	 * 实例化form对象、接受请求参数
	 * 封装请求参数的对象
	 * 先从request或者response中读取、如果没有form对象
	 * 就从ActionConfig中找到form的类名使用反射机制动态创建form对象
	 * 最后使用commons-beanutils工具将request中的请求
	 * 参数封装到form当中、其中这个工具会进行自动类型转换
	 */
	private ActionForm processActionForm(ActionConfig actionConfig,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForm form = null;
		String formName = actionConfig.getName();
		if (formName != null) {
			String scope = actionConfig.getScope();
			if ("request".equals(scope)) {
				form = (ActionForm) request.getAttribute(formName);
			} else {
				form = (ActionForm) request.getSession().getAttribute(formName);
			}
			// session或者request没有ActionForm对象、需要动态的创建
			if (form == null) {
				FormBeanConfig formBeanCfg = moudleConfig
						.findFormBeanConfig(formName);
				String formBeanType = formBeanCfg.getType();
				// 利用反射机制创建
				Class formBeanClass = Class.forName(formBeanType);
				form = (ActionForm) formBeanClass.newInstance();
				if ("request".equals(scope)) {
					request.setAttribute(actionConfig.getAttribute(), form);
				} else {
					request.getSession().setAttribute(
							actionConfig.getAttribute(), form);
				}
			}
		}
		//利用commons-beanutils工具将request中的参数传递给form(接受请求参数的表单)
		BeanUtils.populate(form, request.getParameterMap());
		return form;
	}

	private String processPath(HttpServletRequest request) {
		String uri = request.getServletPath();
		uri = uri.substring(0, uri.indexOf('.'));
		return uri;
	}

}
