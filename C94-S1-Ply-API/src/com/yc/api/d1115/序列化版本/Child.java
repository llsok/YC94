package com.yc.api.d1115.–Ú¡–ªØ∞Ê±æ;

public class Child implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	private String name;

	public Child(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Child [name=" + name + "]";
	}
}
