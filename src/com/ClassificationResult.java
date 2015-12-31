package com;

public class ClassificationResult {
	
	private String classificationName;
	private double likelihood;
	private double probability;
	
	public String getClassificationName() {
		return classificationName;
	}
	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}
	public double getLikelihood() {
		return likelihood;
	}
	public void setLikelihood(double likelihood) {
		this.likelihood = likelihood;
	}
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
}
