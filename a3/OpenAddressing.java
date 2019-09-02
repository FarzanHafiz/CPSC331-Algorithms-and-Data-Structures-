public class OpenAddressing<V, H extends HashFunction> {

	public OpenAddressing(int tableSize, H hashFunction) {
	}

	public void insert(int key, V value) throws DuplicateKeyException{
	}

	public void delete(int key) throws KeyNotFoundException{
	}

	public V search(int key) throws KeyNotFoundException{
		return null;
	}
}
