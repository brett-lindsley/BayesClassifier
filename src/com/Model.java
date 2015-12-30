package com;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Model {
	
	private int numberOfEvents;
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
}
