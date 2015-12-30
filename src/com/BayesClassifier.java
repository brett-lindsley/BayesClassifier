package com;

import java.util.Enumeration;
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
		
		
		for (String key : classCounts.keySet()) {
			System.out.println(key + ": " + classCounts.get(key));
		}
		System.out.println();
		
		
		// Mapping of each classification to a list of attribute maps. Each attribute map
		// holds the count of the number of events.
		Map<String, List<Map<String, AtomicInteger>>> model = new HashMap<>();
		for (String classificationName : evidence.getClasses()) {
			List<Map<String, AtomicInteger>> attributeList = new LinkedList<Map<String, AtomicInteger>>();
			
			for (int i = 0; i < evidence.getAttributeNames().size(); i++) {
				attributeList.add(new HashMap<String, AtomicInteger>());
			}
			
			model.put(classificationName,  attributeList);
		}
		
			
		// For each attribute.
		for (int a = 0; a < evidence.getAttributeNames().size(); a++) {
			
			// For each line of evidence.
			for (int e = 0; e < evidence.getEvidence().size(); e++) {
				// Get evidence line.
				EvidenceLine eLine = evidence.getEvidence().get(e);
				
				// Get the classification for this line of evidence.
				String className = eLine.getClassification();
				
				// Get the list for this class.
				List<Map<String, AtomicInteger>> attributeList = model.get(className);
									
				// Get the value map for this attribute.
				Map<String, AtomicInteger> valueMap = attributeList.get(a);
				
				// Get the value for this attribute.
				String attributeValue = eLine.getAttributeValues().get(a);
				AtomicInteger attributeValueCount = valueMap.get(attributeValue);
				// If value not in map, add it.
				if (attributeValueCount == null) {
					attributeValueCount = new AtomicInteger(0);
					valueMap.put(attributeValue, attributeValueCount);
				}
				// Inc count.
				attributeValueCount.incrementAndGet();
			}				
		}
		
		System.out.println("Model built");
		
		for (String className : classCounts.keySet()) {
			System.out.println(className + ": " + classCounts.get(className));
			
			// Get the list for this class.
			List<Map<String, AtomicInteger>> attributeList = model.get(className);

			for (int i = 0; i < attributeList.size(); i++) {
				System.out.println(i + ", attribute: " + evidence.getAttributeNames().get(i));
				Map<String, AtomicInteger> attributeMap = attributeList.get(i);

				for (String key : attributeMap.keySet()) {
					System.out.println(key + ": " + attributeMap.get(key));
				}

			}
			
			System.out.println();
		}


		/*
		// For each attribute.
		for (int a = 0; a < evidence.getAttributeNames().size(); a++) {
			
			List<Map<String, AtomicInteger>> attributeList = new LinkedList<>();
			
			// for each classification.
			for (int c = 0; c < evidence.getClasses().size(); c++) {
				
				// Go through evidence and get counts.
				String selectedClassification = evidence.getClasses().get(c);
				System.out.println("Selected classification: " + selectedClassification);
				
				String selectedAttribute = evidence.getAttributeNames().get(a);
				System.out.println("Selected attribute: " + selectedAttribute);
				
				Map<String, AtomicInteger> attributeCounts = new HashMap<>();
				attributeList.add(attributeCounts);
				for (int e = 0; e < evidence.getEvidence().size(); e++) {
					EvidenceLine eLine = evidence.getEvidence().get(e);
					// If not the classification we are scanning for, skip.
					if (!eLine.getClassification().equals(selectedClassification)) continue;
					
					// Get the value for this attribute.
					String attributeValue = eLine.getAttributeValues().get(a);
					AtomicInteger attributeValueCount = attributeCounts.get(attributeValue);
					if (attributeValueCount == null) {
						attributeValueCount = new AtomicInteger(0);
						attributeCounts.put(attributeValue, attributeValueCount);
					}
					// Inc count.
					attributeValueCount.incrementAndGet();
					
//					System.out.println("  value: " + eLine.getAttributeValues().get(a));
				}
				
				for (String key : attributeCounts.keySet()) {
					System.out.println(key + ": " + attributeCounts.get(key));
				}
				
			}
		}
		System.out.println();
		*/
	}
	
	

	
	public static void main(String[] args) {
		BayesClassifier bc = new BayesClassifier();
		
		bc.initializeEvidence();
		bc.printEvidence();
		bc.buildModel();
		
	}

}
