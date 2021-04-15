package CompressionAlgorithms.domain;

import java.util.Comparator;
import java.util.PriorityQueue;
import CompressionAlgorithms.domain.HashTable;

/**
 * The Huffman code algorithm
 */
public class HuffmanCode {
    
    static HashTable<Character, String> huffCodes; 
    
    /**
     * Encode a given string with Huffman Code
     * @param inputStr String
     * @return String encoded string
     */
    public static HashTable<Character, String> encode(String inputStr) {
       
        huffCodes = new HashTable<>();
    
        PriorityQueue<HuffmanNode> queue = initializeQueue(inputStr);
        
        HuffmanNode root = null;
        
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
        
            HuffmanNode temp = new HuffmanNode(left, right);
            root = temp;
            queue.add(temp);
        }
        
        generateCodes(root, "");
        return huffCodes;
    }
    
    /**
     * Initialize the priority queue 
     * @param inputStr String 
     * @return PriorityQueue<HuffmanNode>
     */
    private static PriorityQueue<HuffmanNode> initializeQueue(String inputStr) {
        Comparator<HuffmanNode> comparator = Comparator.comparing(HuffmanNode::getData);
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(10, comparator);
        
        int[] charFreqs = initializeCharFreqs(inputStr);
                
        for (int i = 0; i < charFreqs.length; i++) {
            if (charFreqs[i] > 0) {
                queue.add(new HuffmanNode(charFreqs[i], (char) i));
            }
        }
        return queue;
    }
    
    /**
     * Count character frequencies in a given string
     * @param inputStr String
     * @return int[]
     */
    private static int[] initializeCharFreqs(String inputStr) {
        int[] charFreqs = new int[256];
        for (char c : inputStr.toCharArray()) {
            charFreqs[c]++;
        }
        
        return charFreqs;
    }
    
    /**
     * Build the encoded string
     * @param node HuffmanNode 
     * @param prefix String
     */
    public static void generateCodes(HuffmanNode node, String prefix) {
 
        if (node.left == null && node.right == null) {
            huffCodes.put(node.value, prefix);
            return;
        }
 
        generateCodes(node.left, prefix + "0");
        generateCodes(node.right, prefix + "1");
    }
}
