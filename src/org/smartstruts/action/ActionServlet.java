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
	 * smartstruts���������ȡ�����ļ�smart-struts����rule.xml�еĹ��� ����smart-struts.xml�ļ�
	 * ����������dom�����װ��MoudleConfig���󹤳����С� ��servlet�������� moudleConfig�������Ѿ�������
	 * smart-struts.xml�����е���Ϣ�������õ��е� <form-beans> <form-bean name="loginform"
	 * type="org.tarena.smartstruts.form.LoginForm"></form-bean> </form-beans>
	 * ��װ��һ��FormBeanConfig���� ��<action path="/login" name="loginform"
	 * type="org.tarena.struts.action.LoginAction" attribute="loginform"
	 * scope="session" method="login"> <forward name="success"
	 * path="/success.jsp"></forward> <forward name="fail"
	 * path="/fail.jsp"></forward> </action> ��װ��һ��action����
	 * �������ʹ�õ�xml�������൱��һ�������������Ķ�ջ������ŵ���moudleConfig����
	 * 
	 */
	private MoudleConfig moudleConfig = new MoudleConfig();
	private static final String RULE = "org/smartstruts/config/rule.xml";
	// smart-struts�����ļ�����λ��
	private static final String CONFIG = "smart-struts.xml";
	public void init() {
		/**
		 * ��ʼ����ʱ��xml���moudleConfig����
		 */
		try{
		// ����xml�ļ� commons-digester-1.8.jar
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
			// 1���������URL�л�ȡ����������������.do֮��Ĳ���
			String path = processPath(request);
			// (2).���������ȡ��Ӧ��Action����.
			System.out.println("path:"+path);
			ActionConfig actionConfig = moudleConfig.findActionConfig(path);
			System.out.println("ActionConfig:"+actionConfig.getName());
			if (actionConfig != null) {
				// (3)����<action>�����е�name���Դ���ActionForm����,
				// ��������Я������Ϣ��װ������������ ����commons-beanutils-1.8.0.jar
				ActionForm form = processActionForm(actionConfig, request,
						response);// �����������װ��ActionForm����
				System.out.println("form:"+form.getClass().getName());
//				HttpSession session = request.getSession();
//				session.setAttribute(form.getClass().getSimpleName(), form);
				if (form != null) {
					// ִ��Action�е�execute������������ForwordConfig
					String forwordName = processAction(actionConfig, form,
							request, response);
					System.out.println("fordwordName:"+forwordName);
					processForward(forwordName, actionConfig, request, response);
				}
			}
			// 4������Action���õ���type���Դ���Action���󡢲�ִ��execute����
			// ����һ��ActionForword
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
	 * ʵ����Actionҵ���߼�������
	 * ʹ�÷�����Ƶ���Action�еķ���
	 * ����ҵ���߼�����
	 * ����Action�еķ�������form(�����װ���������)
	 * ����һ��forward���󡢱�ʾ���صĶ���
	 */
	private String processAction(ActionConfig actionConfig, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String actionType = actionConfig.getType();
		// �û����е�action����ʵ��Action������
		Action action = (Action) Class.forName(actionType).newInstance();
		System.out.println("Action:"+action.getClass().getName());
		String methodName = actionConfig.getMethod();
		// ��ȡmethod����
		Method method = action.getClass().getMethod(
				methodName,
				new Class[] { ActionForm.class, HttpServletRequest.class,
						HttpServletResponse.class });
		String forword = (String) method.invoke(action, new Object[] { form,
				request, response });
		return forword;
	}

	/**
	 * ʵ����form���󡢽����������
	 * ��װ��������Ķ���
	 * �ȴ�request����response�ж�ȡ�����û��form����
	 * �ʹ�ActionConfig���ҵ�form������ʹ�÷�����ƶ�̬����form����
	 * ���ʹ��commons-beanutils���߽�request�е�����
	 * ������װ��form���С�����������߻�����Զ�����ת��
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
			// session����requestû��ActionForm������Ҫ��̬�Ĵ���
			if (form == null) {
				FormBeanConfig formBeanCfg = moudleConfig
						.findFormBeanConfig(formName);
				String formBeanType = formBeanCfg.getType();
				// ���÷�����ƴ���
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
		//����commons-beanutils���߽�request�еĲ������ݸ�form(������������ı�)
		BeanUtils.populate(form, request.getParameterMap());
		return form;
	}

	private String processPath(HttpServletRequest request) {
		String uri = request.getServletPath();
		uri = uri.substring(0, uri.indexOf('.'));
		return uri;
	}

}
