package bloomfilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BloomfilterBenchmarkTest {

	// final static int m = 100000;											// number of bits
	// final static int n = 50000; 											// Number of elements to test
	// final static int k = 4; 			//(int) ((m / n) * Math.log(2)); 	// number of hash functions

	// set 1 - k varies
	static Integer [] a1 = {100000, 50000, 2};
	static Integer [] a2 = {100000, 50000, 8};
	static Integer [] a3 = {100000, 50000, 16};
	static Integer [] a4 = {100000, 50000, 24};
	static Integer [] a5 = {100000, 50000, 30};
	static Integer [] a6 = {100000, 50000, 34};
	static Integer [] a7 = {100000, 50000, 40};
	static Integer [] a8 = {100000, 50000, 44};
	static Integer [] a9 = {100000, 50000, 50};
	static Integer [] a10 = {100000, 50000, 54};

	/*static Integer [] b1 = {100000, 50000, 2};
    static Integer [] b2 = {100000, 50000, 8};
    static Integer [] b3 = {100000, 50000, 16};
    static Integer [] b4 = {100000, 50000, 24};
    static Integer [] b5 = {100000, 50000, 30};
    static Integer [] b6 = {100000, 50000, 34};
    static Integer [] b7 = {100000, 50000, 40};
    static Integer [] b8 = {100000, 50000, 44};
    static Integer [] b9 = {100000, 50000, 50};
    static Integer [] b10 = {100000, 50000, 54};*/


	// list that will hold array elements
	static List<Integer[]> lista = new ArrayList<Integer[]>();
	static List<Integer[]> listb = new ArrayList<Integer[]>();

	public BloomfilterBenchmarkTest() {
		// TODO Auto-generated constructor stub
	}
	/*
	 public static void printStat(long start, long end) {
	        double diff = (end - start) / 1000.0;
	        System.out.println(diff + "s, " + (n / diff) + " elements/s");
	    }*/

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

			final Random r = new Random();

			// Generate elements first
			List<String> existingElements = new ArrayList<>(n);
			for (int i = 0; i < n; i++) {
				byte[] b = new byte[200];
				r.nextBytes(b);
				existingElements.add(new String(b));
			}

			BloomFilter<String> bf = new BloomFilter<String>(m, n, k);	

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
			//printStat(start_contains, end_contains);
			double diff2 = (end_contains - start_contains) / 1000.0;
			System.out.println(diff + "s, " + (n / diff2) + " elements/s");


			// Check for existing elements with containsAll()
			System.out.print("containsAll(), existing: ");
			long start_containsAll = System.currentTimeMillis();
			for (int i = 0; i < n; i++) {
				bf.contains(existingElements.get(i));
			}
			long end_containsAll = System.currentTimeMillis();
			//printStat(start_containsAll, end_containsAll);
			double diff3 = (end_containsAll - start_containsAll) / 1000.0;
			System.out.println(diff + "s, " + (n / diff3) + " elements/s");
		}
	}

}
