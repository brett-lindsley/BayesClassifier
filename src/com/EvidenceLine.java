package com;

import java.util.List;

public class EvidenceLine {
	
	private String classification;
	private List<String> attributeValues;
	
	public EvidenceLine(List<String> attributeValues, String classification) {
		this.attributeValues = attributeValues;
		this.classification = classification;
	}
	
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public List<String> getAttributeValues() {
		return attributeValues;
	}
	public void setAttributeValues(List<String> attributeValues) {
		this.attributeValues = attributeValues;
	}	

}
