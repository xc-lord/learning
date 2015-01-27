package com.lord.learn.algorithm;

public class PrimeNumber {

	/**
	 * 显示前40个素数
	 * @param args
	 */
	public static void main(String[] args) {
		final int numberOfPrime = 40;
		final int numberOfLine = 10;
		int count = 0;
		int number = 2;
		while(count < numberOfPrime) {
			boolean isPrime = true;
			for(int i=2; i<=number/2; i++) {
				if(number%i == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) {
				count++;
				System.out.print(number + "\t");
				if(count%numberOfLine == 0) {
					System.out.println();
				}
			}
			number++;
		}
	}

}
