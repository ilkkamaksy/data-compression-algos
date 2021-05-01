package CompressionAlgorithms.domain;

/**
 * Class for a hash table
 */
public class HashTable<K, V> {
    private List<Node<K, V>>[] values;
    private int size;

    public HashTable() {
        this.values = new List[32];
        this.size = 0;
    }
    
    /**
     * Get a value by key
     * @param key the key 
     * @return value if found, else null
     */
    public V get(K key) {
        int hash = Math.abs(key.hashCode() % this.values.length);
        if (this.values[hash] == null) {
            return null;
        }

        List<Node<K, V>> nodeListAtIndex = this.values[hash];

        for (int i = 0; i < nodeListAtIndex.size(); i++) {
            if (nodeListAtIndex.get(i).getKey().equals(key)) {
                return nodeListAtIndex.get(i).getValue();
            }
        }

        return null;
    }
    
    /**
     * Put a key value pair into the hashtable 
     * @param key the key
     * @param value the value
     */
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        List<Node<K, V>> nodeListAtIndex = getNodeListAssociatedToKey(key);
        int index = getKeyIndexFromNodeList(nodeListAtIndex, key);

        if (index < 0) {
            nodeListAtIndex.add(new Node<>(key, value));
            this.size++;
        } else {
            nodeListAtIndex.get(index).setValue(value);
        }
        
        if (1.0 * this.size / this.values.length > 0.75) {
            this.grow();
        }
    }
    
    /**
     * Remove a key value pair from hash table by key
     * @param key the key of key value pair
     * @return removed key value pair
     */
    public V remove(K key) {
        List<Node<K, V>> nodeListAtIndex = this.getNodeListAssociatedToKey(key);
        if (nodeListAtIndex.size() == 0) {
            return null;
        }

        int index = this.getKeyIndexFromNodeList(nodeListAtIndex, key);
        if (index < 0) {
            return null;
        }

        Node<K, V> node = nodeListAtIndex.get(index);
        nodeListAtIndex.remove(index);
        return node.getValue();
    }
    
    /**
     * Check if the hash table contains a key
     * @param key to check
     * @return boolean
     */
    public boolean containsKey(K key) {
        List<Node<K, V>> nodeListAtIndex = this.getNodeListAssociatedToKey(key);
        if (nodeListAtIndex.size() == 0) {
            return false;
        }
        
        int index = this.getKeyIndexFromNodeList(nodeListAtIndex, key);
        if (index < 0) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Returns the size of the hash table
     * @return int
     */
    public int size() {
        return this.size;
    }
    
    /**
     * Get the node list associated to a key
     * @param key the key
     * @return List<Node<K, V>> 
     */
    private List<Node<K, V>> getNodeListAssociatedToKey(K key) {
        int hash = Math.abs(key.hashCode() % values.length);
        if (values[hash] == null) {
            values[hash] = new List<>();
        }

        return values[hash];
    }

    /**
     * Get the index of a key in a node list
     * @param nodeList the list of nodes
     * @param key the key
     * @return index if found, else -1
     */
    private int getKeyIndexFromNodeList(List<Node<K, V>> nodeList, K key) {
        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Increase the size of the hash table
     */
    private void grow() {
        List<Node<K, V>>[] newValues = new List[this.values.length * 2];

        for (int i = 0; i < this.values.length; i++) {            
            this.copyListAtIndex(newValues, i);
        }

        this.values = newValues;
    }
    
    /**
     * Copy node list values at given index
     * @param newValuesList the new node list
     * @param listIndex the index of the node list
     */
    private void copyListAtIndex(List<Node<K, V>>[] newValuesList, int listIndex) {
        if (this.values[listIndex] == null) {
            return;
        }
        for (int i = 0; i < this.values[listIndex].size(); i++) {
            Node<K, V> node = this.values[listIndex].get(i);

            int hash = Math.abs(node.getKey().hashCode() % newValuesList.length);
            if (newValuesList[hash] == null) {
                newValuesList[hash] = new List<>();
            }

            newValuesList[hash].add(node);
        }
    }
    
}
