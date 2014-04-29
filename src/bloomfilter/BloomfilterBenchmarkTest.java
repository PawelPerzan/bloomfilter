package bloomfilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Bloom filter benchmark for testing performance in respect to value k (number of hash functions)
 * @author pawel
 *
 */
public class BloomfilterBenchmarkTest {

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

	// list that will hold array elements
	static List<Integer[]> lista = new ArrayList<Integer[]>();
	static List<Integer[]> listb = new ArrayList<Integer[]>();

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
