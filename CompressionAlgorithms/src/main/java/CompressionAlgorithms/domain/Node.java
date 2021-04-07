package CompressionAlgorithms.domain;

/**
 * Class for key value nodes
 */
public class Node<K, V> {
    private K key;
    private V value;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Get the key of the node
     * @return key K
     */
    public K getKey() {
        return key;
    }

    /**
     * Get the value of the node
     * @return value V
     */
    public V getValue() {
        return value;
    }

    /**
     * Set the value of the node
     * @param value to be set
     */
    public void setValue(V value) {
        this.value = value;
    }
}
