package org.smartstruts.config;

import java.util.HashMap;
import java.util.Map;

public class MoudleConfig {
	// 用于存放form-beans
	private Map<String, FormBeanConfig> beans = new HashMap<String, FormBeanConfig>();
	// 用于存放action-mapping
	private Map<String, ActionConfig> actions = new HashMap<String, ActionConfig>();
	private Map<String,ForwordConfig> globalForwords = new HashMap<String,ForwordConfig>();
	public void addForwardConfig(ForwordConfig config){
		globalForwords.put(config.getName(), config);
	}
	public ForwordConfig findGlobalForword(String name){
		if(globalForwords.containsKey(name))
			return globalForwords.get(name);
		return null;
	}
	public void addFormBeanConfig(FormBeanConfig form) {
		beans.put(form.getName(), form);
	}



	public void addActionConfig(ActionConfig action) {
		actions.put(action.getPath(), action);
	}

	public ActionConfig findActionConfig(String path) {
		if (actions.containsKey(path)) {
			return actions.get(path);
		}
		return null;
	}

	public FormBeanConfig findFormBeanConfig(String name) {
		if (beans.containsKey(name)) {
			return beans.get(name);
		}
		return null;
	}
}
