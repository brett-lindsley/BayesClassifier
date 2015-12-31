package com;

import java.util.List;

public class Attribute {
	
	private String name;
	private List<String> values;
	
	public Attribute(String name, List<String> values) {
		this.name = name;
		this.values = values;
	}
	
	public String getName() {
		return name;
	}
	public List<String> getValues() {
		return values;
	}
}
