import java.util.*;

public class Main {
	private static int[] insKeys;
	private static int[] delKeys;
	private static int[] searchKeys;
	private static String[] insValues;
	private static int N, M, Q;
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		int tableSize = input.nextInt();
		N = input.nextInt(); M = input.nextInt(); Q = input.nextInt();
		insKeys = new int[N];
		delKeys = new int[M];
		searchKeys = new int[Q];
		insValues = new String[N];
		for(int i = 0; i < N; i++) {
			insKeys[i] = input.nextInt();
			insValues[i] = input.next();
		}
		for(int i = 0; i < M; i++)
			delKeys[i] = input.nextInt();
		for(int i = 0; i < M; i++)
			searchKeys[i] = input.nextInt();
		OpenAddressing<String, HashFunction> dictionary = new OpenAddressing<String, HashFunction>(tableSize, new DoubleHashing(tableSize));
		run(dictionary);

		dictionary = new OpenAddressing<String, HashFunction>(tableSize, new LinearProbing(tableSize));
		run(dictionary);

		dictionary = new OpenAddressing<String, HashFunction>(tableSize, new QuadraticProbing(tableSize));
		run(dictionary);
	}

	private static void run(OpenAddressing<String, HashFunction> dictionary) {
		for(int i = 0; i < N; i++) {
			int key = insKeys[i];
			String data = insValues[i];
			try {
				dictionary.insert(key, data);
			} catch(DuplicateKeyException dke) {
				System.out.println("Key already exists");
			}
		}
		for(int i = 0; i < M; i++) {
			int key = delKeys[i];
			try {
				dictionary.delete(key);
			} catch(KeyNotFoundException knfe) {
				System.out.println("Key not found");
			}
		}
		for(int i = 0; i < Q; i++) {
			int key = searchKeys[i];
			try {
				String data = dictionary.search(key);
				System.out.println(data);
			} catch(KeyNotFoundException knfe) {
				System.out.println("Key not found");
			}
		}

	}
}
