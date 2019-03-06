package classes;

import interfaces.IRandomValueGenerator;

public class RandomValueGenerator implements IRandomValueGenerator {

	double randomValue; 
	
	@Override
	public int getNextInt() {
		randomValue = Math.random();
		return (int) randomValue;
	}

	@Override
	public boolean getTrueWithProbability(double p) {
		//new number each time, whether this method or previous
		//is called
		randomValue = Math.random();
		return randomValue <= p;
	}

}
