package org.smartstruts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {
	
		public String execute(
				ActionForm form,
				HttpServletRequest request,  
				HttpServletResponse response) throws Exception{
			return "success";
		};
	

}
