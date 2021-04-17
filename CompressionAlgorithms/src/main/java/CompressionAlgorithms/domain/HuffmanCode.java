package CompressionAlgorithms.domain;

import java.util.PriorityQueue;

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
            HuffmanNode parent = new HuffmanNode('\0', left.freq + right.freq, left, right);
            queue.add(parent);
        }
        
        root = queue.poll();
    
        String[] strArray = new String[256];
        buildCode(strArray, root, "");
        
        encodeInput(strArray, inputStr);
        
        writeCode(root, "");
        return encodedResult;
    }
    
   
    /**
     * Initialize the priority queue for encoding
     * @param inputStr String 
     * @return PriorityQueue<HuffmanNode>
     */
    private static PriorityQueue<HuffmanNode> initializeQueue(String inputStr) {
        
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        
        int[] charFreqs = initializeCharFreqs(inputStr);
                
        for (int i = 0; i < charFreqs.length; i++) {
            if (charFreqs[i] > 0) {
                queue.add(new HuffmanNode((char) i, charFreqs[i], null, null));
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
     * Build the Huffman code
     * @param strArray String[] array of chars as string
     * @param node HuffmanNode
     * @param str String result
     */
    private static void buildCode(String[] strArray, HuffmanNode node, String str) {
        if (!node.isLeaf()) {
            buildCode(strArray, node.left,  str + '0');
            buildCode(strArray, node.right, str + '1');
        } else {
            strArray[node.value] = str;
        }
    }
    
    /**
     * Write the Huffman code
     * @param node HuffmanNode 
     * @param str String
     */
    private static void writeCode(HuffmanNode node, String str) {
        
        if (!node.isLeaf()) {
            writeCode(node.left, str + "0");    
            writeCode(node.right, str + "1");    
        } else {
            str += str;
        }
    }
    
    /**
     * Use Huffman code to encode input
     * @param strArray array of string
     * @param str String input string
     */
    private static void encodeInput(String[] strArray, String str) {
        for (int i = 0; i < str.length(); i++) {
            String code = strArray[str.charAt(i)];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    encodedResult += "0";
                } else if (code.charAt(j) == '1') {
                    encodedResult += "1";
                }
            }
        }
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
    
            if (encodedStr.charAt(i) == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            
            if (current.isLeaf()) {
                result += current.value;
                current = inputNode;
            }
            
        }
        
        return result;
    }
    
}
