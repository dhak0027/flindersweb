package com.flinders.poc.common.constants;

/**
 * Action Mode for CRUD operation
 * @author mswahithali
 *
 */

public enum ActionMode {
	CREATE("CREATE"),
	MODIFY("MODIFY"),
	DELETE("DELETE"),
	VALIDATE("VALIDATE"),
	VIEW("VIEW");
	
	private String actionMode;
	
	private ActionMode(String actionMode) {
		this.actionMode = actionMode;
	}
	
	public String getAction(){
		return actionMode;
	}

}
