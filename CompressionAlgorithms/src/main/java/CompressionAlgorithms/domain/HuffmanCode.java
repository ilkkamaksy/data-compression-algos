package CompressionAlgorithms.domain;

import java.util.Comparator;
import java.util.PriorityQueue;
import CompressionAlgorithms.domain.HashTable;
import CompressionAlgorithms.domain.HuffmanNode;

/**
 * The Huffman code algorithm
 */
public class HuffmanCode {
    
    public static String encodedResult; 
    public static HuffmanNode root;
    
    /**
     * Encode a given string with Huffman Code
     * @param inputStr String
     * @return String encoded string
     */
    public static String encode(String inputStr) {
       
        encodedResult = "";
        root = null;
    
        PriorityQueue<HuffmanNode> queue = initializeQueue(inputStr);
       
        while (queue.size() > 1) {
            
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
        
            HuffmanNode parent = new HuffmanNode(left, right);
            queue.add(parent);
        }
        
        root = queue.poll();
        
        generateHuffCode(root, "");
        return encodedResult;
    }
    
    /**
     * Initialize the priority queue for encoding
     * @param inputStr String 
     * @return PriorityQueue<HuffmanNode>
     */
    private static PriorityQueue<HuffmanNode> initializeQueue(String inputStr) {
        Comparator<HuffmanNode> comparator = Comparator.comparing(HuffmanNode::getFreq);
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(comparator);
        
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
     * Generate the Huffman code
     * @param node HuffmanNode 
     * @param prefix String
     */
    private static void generateHuffCode(HuffmanNode node, String prefix) {
        
        if (node.left == null && node.right == null) {
            System.out.println("prefix " + prefix + "\t" + node.value);
            encodedResult += prefix;
            return;
        }

        generateHuffCode(node.left, prefix + "0");
        generateHuffCode(node.right, prefix + "1");
    }
    
    /**
     * Decode Huffman encoded data
     * @param encodedStr String encoded string
     * @param inputNode HuffmanNode the decoded Huffman tree
     * @return String decoded string
     */
    public static String decode(String encodedStr, HuffmanNode inputNode) {
        
        String result = "";
        HuffmanNode current = inputNode;
        
        for (int i = 0; i < encodedStr.length(); i++) {
            
            if ("0".equals(Character.toString(encodedStr.charAt(i)))) {
                current = current.left;
            } else {
                current = current.right;
            }
            
            if (current.left == null && current.right == null) {
                result += current.value;
                current = inputNode;
            }
        }
        return result;
    }
    
}
