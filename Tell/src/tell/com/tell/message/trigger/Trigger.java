package com.tell.message.trigger;

public abstract class Trigger {

	private String triggerInfo = "";
	
	abstract public boolean isTriggered();

	/**
	 * return the trigger message. you can delivery the message to message queue. 
	 * the default message is  "". this message depends on the subClass of Triggerable 
	 * @return
	 * 		trigger message
	 * 
	 */
	public String getTriggerInfo(){
		return triggerInfo;
	}
	
	protected void setTriggerInfo(String info) {
		triggerInfo = info;
	}
}
