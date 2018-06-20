package com.muzhi.model;

public  class Type {
	/**
	 * 资源名称
	 */
	private String name;
	/**
	 * 资源数量
	 */
	private int number;
    
	public Type() {
		super();
	}

	public Type(String name, int number) {
		super();
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
