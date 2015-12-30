package com;

import java.util.LinkedList;
import java.util.List;

public class Evidence {
	
	private List<String> classes = new LinkedList<>();
	private List<String> attributeNames = new LinkedList<>();
	private List<EvidenceLine> evidence = new LinkedList<>();

	public List<EvidenceLine> getEvidence() {
		return evidence;
	}

	public List<String> getAttributeNames() {
		return attributeNames;
	}

	public void setAttributeNames(List<String> attributeNames) {
		this.attributeNames = attributeNames;
	}

	public List<String> getClasses() {
		return classes;
	}

	public void setClasses(List<String> classes) {
		this.classes = classes;
	}

}
