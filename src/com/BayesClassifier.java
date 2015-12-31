package com;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BayesClassifier {
	
	private Evidence evidence = new Evidence();
	private Model model = new Model();
	
	@SuppressWarnings("serial")
	private void initializeEvidence() {
		
		// Initialize the names of the classes.
		List<String> classNames = new LinkedList<String>() {{
			add("no");
			add("yes");
		}};		
		evidence.setClasses(classNames);
		
		// Initialize attribute names.
		List<String> attributeNames = new LinkedList<String>() {{
			add("outlook"); add("temperature"); add("humidity"); add("windy");
		}};		
		evidence.setAttributeNames(attributeNames);
		
		// Set evidence.
		evidence.getEvidence().add(getEvidenceLine1());
		evidence.getEvidence().add(getEvidenceLine2());
		evidence.getEvidence().add(getEvidenceLine3());
		evidence.getEvidence().add(getEvidenceLine4());
		evidence.getEvidence().add(getEvidenceLine5());
		evidence.getEvidence().add(getEvidenceLine6());
		evidence.getEvidence().add(getEvidenceLine7());
		evidence.getEvidence().add(getEvidenceLine8());
		evidence.getEvidence().add(getEvidenceLine9());
		evidence.getEvidence().add(getEvidenceLine10());
		evidence.getEvidence().add(getEvidenceLine11());
		evidence.getEvidence().add(getEvidenceLine12());
		evidence.getEvidence().add(getEvidenceLine13());
		evidence.getEvidence().add(getEvidenceLine14());
	}
	
	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine1() {
		List<String> line1 = new LinkedList<String>() {{
			add("sunny"); add("hot"); add("high"); add("false");
		}};		
		return new EvidenceLine(line1, "no");
	}
	
	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine2() {
		List<String> line1 = new LinkedList<String>() {{
			add("sunny"); add("hot"); add("high"); add("true");
		}};		
		return new EvidenceLine(line1, "no");
	}
	
	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine3() {
		List<String> line1 = new LinkedList<String>() {{
			add("overcast"); add("hot"); add("high"); add("false");
		}};		
		return new EvidenceLine(line1, "yes");
	}

	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine4() {
		List<String> line1 = new LinkedList<String>() {{
			add("rainy"); add("mild"); add("high"); add("false");
		}};		
		return new EvidenceLine(line1, "yes");
	}

	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine5() {
		List<String> line1 = new LinkedList<String>() {{
			add("rainy"); add("cool"); add("normal"); add("false");
		}};		
		return new EvidenceLine(line1, "yes");
	}

	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine6() {
		List<String> line1 = new LinkedList<String>() {{
			add("rainy"); add("cool"); add("normal"); add("true");
		}};		
		return new EvidenceLine(line1, "no");
	}
	
	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine7() {
		List<String> line1 = new LinkedList<String>() {{
			add("overcast"); add("cool"); add("normal"); add("true");
		}};		
		return new EvidenceLine(line1, "yes");
	}
	
	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine8() {
		List<String> line1 = new LinkedList<String>() {{
			add("sunny"); add("mild"); add("high"); add("false");
		}};		
		return new EvidenceLine(line1, "no");
	}
	
	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine9() {
		List<String> line1 = new LinkedList<String>() {{
			add("sunny"); add("cool"); add("normal"); add("false");
		}};		
		return new EvidenceLine(line1, "yes");
	}
	
	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine10() {
		List<String> line1 = new LinkedList<String>() {{
			add("rainy"); add("mild"); add("normal"); add("false");
		}};		
		return new EvidenceLine(line1, "yes");
	}
	
	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine11() {
		List<String> line1 = new LinkedList<String>() {{
			add("sunny"); add("mild"); add("normal"); add("true");
		}};		
		return new EvidenceLine(line1, "yes");
	}
	
	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine12() {
		List<String> line1 = new LinkedList<String>() {{
			add("overcast"); add("mild"); add("high"); add("true");
		}};		
		return new EvidenceLine(line1, "yes");
	}
	
	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine13() {
		List<String> line1 = new LinkedList<String>() {{
			add("overcast"); add("hot"); add("normal"); add("false");
		}};		
		return new EvidenceLine(line1, "yes");
	}
	
	@SuppressWarnings("serial")
	private EvidenceLine getEvidenceLine14() {
		List<String> line1 = new LinkedList<String>() {{
			add("rainy"); add("mild"); add("high"); add("true");
		}};		
		return new EvidenceLine(line1, "no");
	}
	
	private void printEvidence() {
		for (int i = 0; i < evidence.getEvidence().size(); i++) {
			EvidenceLine e = evidence.getEvidence().get(i);
			
			System.out.printf("%2d  ", i + 1);
			
			for (int j = 0; j < e.getAttributeValues().size(); j++) {
				System.out.printf("%10s  ", e.getAttributeValues().get(j));
			}
			
			System.out.print(" --- ");
			System.out.println(e.getClassification());
		}
	}
	
	
	private void buildModel() {
				
		addAttributesToModel();		
		calculateClassificationsPriorProbabilities();		
		createCounterMapsForEachClassification();					
		createAttributeCounts();		
	}
	
	
	@SuppressWarnings("serial")
	private void addAttributesToModel() {
		// Outlook.
		model.getAttributes().add(
				new Attribute("outlook", new LinkedList<String>() {{ add("sunny"); add("overcast"); add("rainy");}})
		);
		// Temperature.
		model.getAttributes().add(
				new Attribute("temperature", new LinkedList<String>() {{ add("hot"); add("mild"); add("cool");}})
		);
		// Humidity.
		model.getAttributes().add(
				new Attribute("humidity", new LinkedList<String>() {{ add("high"); add("normal");}})
		);
		// Windy.
		model.getAttributes().add(
				new Attribute("windy", new LinkedList<String>() {{ add("false"); add("true");}})
		);
	}
	
	private void calculateClassificationsPriorProbabilities() {
		// Calculate the probability of each class.
		// Create map to hold counts.
		Map<String, AtomicInteger> classCounts = new HashMap<>();
		for (String className : evidence.getClasses()) {
			classCounts.put(className, new AtomicInteger(0));
		}
		// Count number of events in each classification.
		for (int e = 0; e < evidence.getEvidence().size(); e++) {
			EvidenceLine eLine = evidence.getEvidence().get(e);
			String className = eLine.getClassification();
			classCounts.get(className).incrementAndGet();	
		}
		
		// Put in model.
		model.setNumberOfEvents(evidence.getEvidence().size());
		model.setClassCounts(classCounts);
	}
	
	private void printClassificationCounts() {
		for (String key : model.getClassCounts().keySet()) {
			System.out.println(key + ": " + model.getClassCounts().get(key));
		}
		System.out.println();
	}
	
	private void printTotalNumberOfEvents() {
		System.out.println("Total number of events: " + model.getNumberOfEvents());
	}
	
	private void printAttributeCounts() {
		for (String className : model.getClassCounts().keySet()) {
			System.out.println(className + ": " + model.getClassCounts().get(className));
			
			// Get the list for this class.
			List<Map<String, AtomicInteger>> attributeList = model.getModelCounts().get(className);

			for (int i = 0; i < attributeList.size(); i++) {
				System.out.println(i + ", attribute: " + evidence.getAttributeNames().get(i));
				Map<String, AtomicInteger> attributeMap = attributeList.get(i);

				for (String key : attributeMap.keySet()) {
					System.out.println(key + ": " + attributeMap.get(key));
				}

			}
			
			System.out.println();
		}
	}

	
	private void createCounterMapsForEachClassification() {
		// Create counter maps for each classification.
		for (String classificationName : evidence.getClasses()) {
			// Create an attribute list for this classification.
			List<Map<String, AtomicInteger>> attributeList = new LinkedList<Map<String, AtomicInteger>>();
			
			// Add each attribute and its count map.
			for (int attrIndex = 0; attrIndex < model.getAttributes().size(); attrIndex++) {
				
				// Create attribute count map.
				Map<String, AtomicInteger> countMap = new HashMap<>();
				
				// Initialize counts.
				for (String attributeValueName : model.getAttributes().get(attrIndex).getValues()) {
					countMap.put(attributeValueName, new AtomicInteger(0));
				}
				
				// Put count map in list.
				attributeList.add(countMap);
			}
			
			// Save list of count maps in model.
			model.getModelCounts().put(classificationName,  attributeList);
		}
	}
	
	public void createAttributeCounts() {
		// For each attribute.
		for (int a = 0; a < evidence.getAttributeNames().size(); a++) {
			
			// For each line of evidence.
			for (int e = 0; e < evidence.getEvidence().size(); e++) {
				// Get evidence line.
				EvidenceLine eLine = evidence.getEvidence().get(e);
				
				// Get the classification for this line of evidence.
				String className = eLine.getClassification();
				
				// Get the list for this class.
				List<Map<String, AtomicInteger>> attributeList = model.getModelCounts().get(className);
									
				// Get the value map for this attribute.
				Map<String, AtomicInteger> valueMap = attributeList.get(a);
				
				// Get the value for this attribute.
				String attributeValue = eLine.getAttributeValues().get(a);
				AtomicInteger attributeValueCount = valueMap.get(attributeValue);
				// Inc count.
				attributeValueCount.incrementAndGet();
			}				
		}
	}
	
	private List<ClassificationResult> classify(List<String> values, boolean useLaplacianSmoothing) {
		
		List<ClassificationResult> classificationList = new LinkedList<>();
		
		double totalLikelihood = 0.0;
		
		// Calculate the likelihood for each classification.
		for (String className : model.getClassCounts().keySet()) {
			
			// Initialize likelihood to prior probability of the classification.
			double likelihood = (double) model.getClassCounts().get(className).intValue() / (double) model.getNumberOfEvents();
			
			// Get the attribute list for this classification.
			List<Map<String, AtomicInteger>> attributeList = model.getModelCounts().get(className);

			// Multiply the probability of each attribute.
			for (int i = 0; i < attributeList.size(); i++) {
				Map<String, AtomicInteger> attributeMap = attributeList.get(i);

				// Get the number of events for this attribute.
				int numEventsForAttribute = attributeMap.get(values.get(i)).intValue();
				
				// Find total number of events for this attribute.
				int totalEvents = 0;
				for (String attributeValue : attributeMap.keySet()) {
					totalEvents += attributeMap.get(attributeValue).intValue();
				}
				
				if (useLaplacianSmoothing) {
					numEventsForAttribute++;
					totalEvents += attributeMap.size();
				}
				
				// Calculate contribution of this attribute to likelihood.
				likelihood *= (double) numEventsForAttribute / (double) totalEvents;
			}
			
			// Total the likelihood.
			totalLikelihood += likelihood;
			
			// Add the classification result.
			ClassificationResult cr = new ClassificationResult();
			cr.setClassificationName(className);
			cr.setLikelihood(likelihood);
			classificationList.add(cr);
		}
		
		// Scale all likelihoods to create probability.
		for (ClassificationResult cr : classificationList) {
			cr.setProbability(cr.getLikelihood() / totalLikelihood);
		}
		
		// Sort by likelihood.
		Collections.sort(classificationList, new Comparator<ClassificationResult>() {
			@Override
			public int compare(ClassificationResult o1, ClassificationResult o2) {
				if (o1.getLikelihood() > o2.getLikelihood()) return -1;
				else return 1;
			}});
		
		return classificationList;
	}
	
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		BayesClassifier bc = new BayesClassifier();
		
		bc.initializeEvidence();
		bc.buildModel();

//		bc.printEvidence();
//		bc.printTotalNumberOfEvents();
//		bc.printClassificationCounts();
//		bc.printAttributeCounts();
		
		System.out.println("***** Classify *****");
		List<ClassificationResult> classificationResults =
				bc.classify(new LinkedList<String>() {{ add("sunny"); add("cool"); add("high"); add("true");}}, false);
		for (ClassificationResult cr : classificationResults) {
			System.out.println(cr.getClassificationName() + " " + cr.getLikelihood() + " " + cr.getProbability());
		}
		List<ClassificationResult> classificationResultsSmoothed =
				bc.classify(new LinkedList<String>() {{ add("sunny"); add("cool"); add("high"); add("true");}}, true);
		for (ClassificationResult cr : classificationResultsSmoothed) {
			System.out.println(cr.getClassificationName() + " " + cr.getLikelihood() + " " + cr.getProbability());
		}
	}

}
