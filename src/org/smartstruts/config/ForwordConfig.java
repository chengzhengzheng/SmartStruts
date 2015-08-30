package org.smartstruts.config;

public class ForwordConfig {

	private String name;
	private String path;
	private boolean redirect = false;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
