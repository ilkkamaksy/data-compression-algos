package CompressionAlgorithms.domain;

/**
 * A node of the Huffman tree
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
 
    public int freq;
    public char value;
 
    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode(char value, int freq, HuffmanNode left, HuffmanNode right) {
        this.value    = value;
        this.freq  = freq;
        this.left  = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return (left == null) && (right == null);
    }
   
    @Override
    public int compareTo(HuffmanNode t) {
        return this.freq - t.freq;
    }
    
    
}
