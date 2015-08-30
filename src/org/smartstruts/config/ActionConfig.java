package org.smartstruts.config;

import java.util.HashMap;
import java.util.Map;

public class ActionConfig {
	private String path;
	private String name;
	private String scope = "session";
	private String attribute;
	private String type;
	private String method = "execute";
	
	private Map<String,ForwordConfig> forwords = new HashMap<String,ForwordConfig>();
	
	public void addForwordConfig(ForwordConfig config){
		forwords.put(config.getName(), config);
	}
	public ForwordConfig findForwordConfig(String name){
		
		if(forwords.containsKey(name)){
			return forwords.get(name);
		}
		return null;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getAttribute() {
		if(attribute != null)
			return attribute;
		return name;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	

}
