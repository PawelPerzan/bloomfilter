package bloomfilter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Evaluate the Bloom filter data structure by testing different m, n, k properties in Bloom filter 
 * and observing their characteristics
 * 
 * @author Pawel Perzan
 * Credits: Magnus Skjegstad
 *
 */
public class BloomfilterPropertiesTest {

	// m, n, k values will be analyzed (changed) 

	final static int m = 100000;											// number of bits
	final static int n = 10000; 											// Number of elements to test
	final static int k = 4; 			//(int) ((m / n) * Math.log(2)); 	// number of hash functions


	// Set 1 - arrays with {m, n, k} elements (k constant, different values of m / n in decreasing order)

	/*    static Integer [] a1 = {100000, 10000, 4};
    static Integer [] a2 = {100000, 11111, 4};
    static Integer [] a3 = {100000, 12500, 4};
    static Integer [] a4 = {100000, 14285, 4};
    static Integer [] a5 = {100000, 16666, 4};
    static Integer [] a6 = {100000, 20000, 4};
    static Integer [] a7 = {100000, 25000, 4};
    static Integer [] a8 = {100000, 33333, 4};
    static Integer [] a9 = {100000, 50000, 4};
    static Integer [] a10 = {100000, 100000, 4};
	 */
	// Set 2 - arrays with {m, n, k} elements (k constant, different values of m / n in decreasing order)
	/*    
    static Integer [] a1 = {100000, 10000, 5};
    static Integer [] a2 = {100000, 11111, 5};
    static Integer [] a3 = {100000, 12500, 5};
    static Integer [] a4 = {100000, 14285, 5};
    static Integer [] a5 = {100000, 16666, 5};
    static Integer [] a6 = {100000, 20000, 5};
    static Integer [] a7 = {100000, 25000, 5};
    static Integer [] a8 = {100000, 33333, 5};
    static Integer [] a9 = {100000, 50000, 5};
    static Integer [] a10 = {100000, 100000, 5};
	 */

	// Set 3 - arrays with {m, n, k} elements (k constant, different values of m / n in decreasing order)

	/*    static Integer [] a1 = {100000, 10000, 5};
    static Integer [] a2 = {100000, 11111, 6};
    static Integer [] a3 = {100000, 12500, 6};
    static Integer [] a4 = {100000, 14285, 6};
    static Integer [] a5 = {100000, 16666, 6};
    static Integer [] a6 = {100000, 20000, 6};
    static Integer [] a7 = {100000, 25000, 6};
    static Integer [] a8 = {100000, 33333, 6};
    static Integer [] a9 = {100000, 50000, 6};
    static Integer [] a10 = {100000, 100000, 6};*/


	// Set4 - arrays with {m, n, k} elements (k constant, different values of m / n in decreasing order)

	/*  static Integer [] a1 = {100000, 10000, 7};
    static Integer [] a2 = {100000, 11111, 7};
    static Integer [] a3 = {100000, 12500, 7};
    static Integer [] a4 = {100000, 14285, 7};
    static Integer [] a5 = {100000, 16666, 7};
    static Integer [] a6 = {100000, 20000, 7};
    static Integer [] a7 = {100000, 25000, 7};
    static Integer [] a8 = {100000, 33333, 7};
    static Integer [] a9 = {100000, 50000, 7};
    static Integer [] a10 = {100000, 100000, 7};*/

	static Integer [] a1 = {100000, 10000, 8};
	static Integer [] a2 = {100000, 11111, 8};
	static Integer [] a3 = {100000, 12500, 8};
	static Integer [] a4 = {100000, 14285, 8};
	static Integer [] a5 = {100000, 16666, 8};
	static Integer [] a6 = {100000, 20000, 8};
	static Integer [] a7 = {100000, 25000, 8};
	static Integer [] a8 = {100000, 33333, 8};
	static Integer [] a9 = {100000, 50000, 8};
	static Integer [] a10 = {100000, 100000, 8};


	//Set5 - k not constant , m, n constant (optimal k is 7)
	/*
    static Integer [] a1 = {100000, 10000, 1};
    static Integer [] a2 = {100000, 10000, 2};
    static Integer [] a3 = {100000, 10000, 3};
    static Integer [] a4 = {100000, 10000, 4};
    static Integer [] a5 = {100000, 10000, 5};
    static Integer [] a6 = {100000, 10000, 6};
    static Integer [] a7 = {100000, 10000, 7};
    static Integer [] a8 = {100000, 10000, 8};
    static Integer [] a9 = {100000, 10000, 9};
    static Integer [] a10 = {100000, 10000, 10};
    static Integer [] a11 = {100000, 10000, 11};
    static Integer [] a12 = {100000, 10000, 12};
    static Integer [] a13 = {100000, 10000, 13};
    static Integer [] a14 = {100000, 10000, 14};
    static Integer [] a15 = {100000, 10000, 15};
	 */

	// list that will hold array elements
	static List<Integer[]> lista = new ArrayList<Integer[]>();

	/**
	 * Print statistics 
	 * @param start
	 * @param end
	 */
	public static void printStat(long start, long end) {
		double diff = (end - start) / 1000.0;
		System.out.println(diff + "s, " + (n / diff) + " elements/s");
	}

	/**
	 * Main function to test bloom filter
	 * @param args
	 */
	public static void main(String[] args) {


		lista.add(a1);
		lista.add(a2);
		lista.add(a3);
		lista.add(a4);
		lista.add(a5);
		lista.add(a6);
		lista.add(a7);
		lista.add(a8);
		lista.add(a9);
		lista.add(a10);
		/*lista.add(a11);
    	lista.add(a12);
    	lista.add(a13);
    	lista.add(a14);
    	lista.add(a15);*/


		//Set2 - k not constant , m, n constant 

		// iterate over the list of arrays and get each value (m, n, k)
		for (Integer[] arrayElement : lista) {

			Integer[] a = arrayElement;
			int m = a[0];
			int n = a[1];
			int k = a[2];

			System.out.println("m: " + m);
			System.out.println("n: " + n);
			System.out.println("k: " + k);
			System.out.println("c = m / n: " + (m / n));

			/*
    		for (Integer element: arrayElement) {
    			System.out.println("element: " + element);
    		}
			 */

			final Random r = new Random();
			// Generate random elements first
			List<String> existingElements = new ArrayList<String>(n);
			for (int i = 0; i < n; i++) {
				byte[] b = new byte[200];
				r.nextBytes(b);
				existingElements.add(new String(b));
			}

			// create bloom filter: BloomFilter(int m, int n, int k)
			BloomFilter<String> bf = new BloomFilter<String>(m, n, k);	//					; BloomFilter(int m, int n, int k)
			//BloomFilter<String> bf = new BloomFilter<String>(10000000000, 100000000, 10);


			// verify provided n, m, k values
			System.out.println("#################### Bloom Filter - n, m, k Analysis ############");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("| n number of bits/bit size (space): 	" + bf.size());				
			System.out.println("-----------------------------------------------------------------");
			System.out.println("| m number of elements in bloom filter: " + bf.getExpectedNumberOfElements());
			System.out.println("-----------------------------------------------------------------");
			System.out.println("| k number of hash functions:           " + bf.getK());												
			System.out.println("-----------------------------------------------------------------");

			// false positive probability

			System.out.println("| false positive probability p         	" + bf.getFalsePositiveProbability(n));
			System.out.println("-----------------------------------------------------------------");
			DecimalFormat df = new DecimalFormat("#.####");
			//System.out.println("| Rounded false positive probability p 	" + df.format(bf.getFalsePositiveProbability(n)));
			System.out.println("| Rounded false positive probability p 	" + df.format(bf.getFalsePositiveProbability(n)));
			System.out.println("-----------------------------------------------------------------");

			// calculate the optimal number of hash functions
			System.out.println("| Optimal number k of hash functions:   " + ((m / n) * Math.log(2)));
			System.out.println("-----------------------------------------------------------------");
			df = new DecimalFormat("#");
			System.out.println("| Rounded optimal number k              " + df.format(((m / n) * Math.log(2))));
			System.out.println("-----------------------------------------------------------------");

			System.out.println("#################### p false positive probability Analysis ############");

		} 
	}
}
