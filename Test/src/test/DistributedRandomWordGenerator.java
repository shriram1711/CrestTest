package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DistributedRandomWordGenerator {

	private Map<String, Double> distribution;
	private double distSum;

	public DistributedRandomWordGenerator() {
		distribution = new HashMap<>();
	}

	public void addWord(String value, double distribution) {
		if (this.distribution.get(value) != null) {
			distSum -= this.distribution.get(value);
		}
		this.distribution.put(value, distribution);
		distSum += distribution;
	}

	public String getDistributedRandomWord() {
		double rand = Math.random();
		double ratio = 1.0f / distSum;
		double tempDist = 0;
		for (String i : distribution.keySet()) {
			tempDist += distribution.get(i);
			if (rand / ratio <= tempDist) {
				return i;
			}
		}
		return generateRandomWord(3);
	}

	public String generateRandomWord(int wordLength) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(wordLength);
		for (int i = 0; i < wordLength; i++) {
			char tmp = (char) ('a' + r.nextInt('z' - 'a'));
			sb.append(tmp);
		}
		return sb.toString();
	}

}
