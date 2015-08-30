package org.smartstruts;
import java.net.URL;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.smartstruts.config.ActionConfig;
import org.smartstruts.config.MoudleConfig;


public class TestParseXML {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			URL url = TestParseXML.class.getClassLoader().getResource(
					"org/tarena/smartstruts/rule.xml");
			Digester digester = DigesterLoader.createDigester(url);
			MoudleConfig moudleConfig = new MoudleConfig();
			digester.push(moudleConfig);
			digester.parse(TestParseXML.class.getClassLoader()
					.getResourceAsStream("smart-struts.xml"));
			System.out.println(moudleConfig.findFormBeanConfig("loginform"));
			ActionConfig action = moudleConfig.findActionConfig("/login");
			System.out.println(action);
			System.out.println(action.getName());
			System.out.println(action.getAttribute());
			System.out.println(action.getMethod());
			System.out.println(action.getPath());
			System.out.println(action.getScope());
			System.out.println(action);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}