package CompressionAlgorithms.domain;

/**
 * A node of the Huffman tree
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
 
    public int freq;
    public char value;
 
    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode(int freq, char value) {
        this.freq = freq;
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        this.freq = left.freq + right.freq;
        this.left = left;
        this.right = right;   
    }

    public int getFreq() {
        return freq;
    }
   

    @Override
    public int compareTo(HuffmanNode t) {
        return this.freq - t.freq;
    }
    
    
}
