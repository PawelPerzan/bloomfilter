package bloomfilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Bloom filter benchmark for testing performance in respect to probability p (false positive probability)
 * @author pawel
 *
 */
public class BloomfilterBenchmarkTest2 {

	// set 1 - test affect of number of elements on performance
		static Object [] a1 = {0.0005, 10000};
	    static Object [] a2 = {0.0005, 20000};
	    static Object [] a3 = {0.0005, 30000};
	    static Object [] a4 = {0.0005, 40000};
	    static Object [] a5 = {0.0005, 50000};
	    static Object [] a6 = {0.0005, 60000};
	    static Object [] a7 = {0.0005, 70000};
	    static Object [] a8 = {0.0005, 80000};
	    static Object [] a9 = {0.0005, 90000};
	    static Object [] a10 = {0.0005, 100000};


	// set 2 - test affect of probability on performance
/*	static Object [] a1 = {0.8, 100000};
	static Object [] a2 = {0.5, 100000};
	static Object [] a3 = {0.4, 100000};
	static Object [] a4 = {0.3, 100000};
	static Object [] a5 = {0.2, 1000000};
	static Object [] a6 = {0.15, 100000};
	static Object [] a7 = {0.05, 100000};
	static Object [] a8 = {0.01, 100000};
	static Object [] a9 = {0.001, 100000};
	static Object [] a10 = {0.0001, 100000};*/

	// list that will hold array elements
	static List<Object[]> lista = new ArrayList<Object[]>();

	public static void main(String[] args) {

		// set 1 - k varies

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

		// iterate over the list of arrays and get each value (p, n)
		for (Object[] arrayElement : lista) {

			Object[] a = arrayElement;

			Double p = (Double)a[0];			// false positive probability
			Integer n = (Integer)a[1];			// number of elements

			System.out.println("p: " + p);
			System.out.println("n: " + n);

			final Random r = new Random();

			// Generate elements first
			List<String> existingElements = new ArrayList<>(n);
			for (int i = 0; i < n; i++) {
				byte[] b = new byte[200];
				r.nextBytes(b);
				existingElements.add(new String(b));
			}

			BloomFilter<String> bf = new BloomFilter<String>(p, n);	

			// Add n elements
			System.out.println("################ Bloom Filter Performance Analysis ##############");
			System.out.println("-----------------------------------------------------------------");
			System.out.print("add(): ");
			long start_add = System.currentTimeMillis();
			for (int i = 0; i < n; i++) {
				bf.add(existingElements.get(i));
			}

			long end_add = System.currentTimeMillis();
			//printStat(start_add, end_add);
			double diff = (end_add - start_add) / 1000.0;
			System.out.println(diff + "s, " + (n / diff) + " elements/s");

			// Check for existing elements with contains()
			System.out.print("contains(), existing: ");
			long start_contains = System.currentTimeMillis();
			for (int i = 0; i < n; i++) {
				bf.contains(existingElements.get(i));
			}
			long end_contains = System.currentTimeMillis();
			diff = (end_contains - start_contains) / 1000.0;
			System.out.println(diff + "s, " + (n / diff) + " elements/s");
		}
	}
}
