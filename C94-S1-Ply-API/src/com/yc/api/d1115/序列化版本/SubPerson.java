package com.yc.api.d1115.–Ú¡–ªØ∞Ê±æ;

public class SubPerson extends Person{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;

	public SubPerson(String name, int age, char sex, String type) {
		super(name, age, sex);
		this.type = type;
	}

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SubPerson [type=" + type + "]";
	}

}
