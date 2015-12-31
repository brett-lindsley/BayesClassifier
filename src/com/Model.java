package com;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Model {
	
	private List<Attribute> attributes = new LinkedList<>();
	private int numberOfEvents;
	private Map<String, List<Map<String, AtomicInteger>>> modelCounts = new HashMap<>();
	
	
	private Map<String, AtomicInteger> classCounts;
	
	public int getNumberOfEvents() {
		return numberOfEvents;
	}
	public void setNumberOfEvents(int numberOfEvents) {
		this.numberOfEvents = numberOfEvents;
	}
	public Map<String, AtomicInteger> getClassCounts() {
		return classCounts;
	}
	public void setClassCounts(Map<String, AtomicInteger> classCounts) {
		this.classCounts = classCounts;
	}
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public Map<String, List<Map<String, AtomicInteger>>> getModelCounts() {
		return modelCounts;
	}
}
