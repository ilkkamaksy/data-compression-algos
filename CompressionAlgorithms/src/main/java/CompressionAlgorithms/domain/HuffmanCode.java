package CompressionAlgorithms.domain;

import java.util.PriorityQueue;
import CompressionAlgorithms.domain.List;

/**
 * The Huffman code algorithm
 */
public class HuffmanCode {
    
    public static String encodedBody;
    public static String header;
    public static String sep = "###";
    
    /**
     * Encode a given string with Huffman Code
     * @param inputStr String
     * @return String encoded string
     */
    public static String encode(String inputStr) {
       
        encodedBody = "";
        header = "";
        HuffmanNode root = null;
    
        PriorityQueue<HuffmanNode> queue = initializeQueue(inputStr);
       
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll(); 
            HuffmanNode parent = new HuffmanNode('\0', left.freq + right.freq, left, right);
            queue.add(parent);
        }
        
        root = queue.poll();
    
        String[] strArray = new String[256];
        buildHuffmanCode(strArray, root, "");
        
        encodeHuffmanNode(root);
        encodeInputByHuffmanCode(strArray, inputStr);
        
        return header + sep + encodedBody;
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
    private static void buildHuffmanCode(String[] strArray, HuffmanNode node, String str) {
        if (!node.isLeaf()) {            
            buildHuffmanCode(strArray, node.left,  str + '0');
            buildHuffmanCode(strArray, node.right, str + '1');
        } else {
            strArray[node.value] = str;
        }
    }
   
    /**
     * Use Huffman code to encode input
     * @param strArray array of string
     * @param str String input string
     */
    private static void encodeInputByHuffmanCode(String[] strArray, String str) {
        for (int i = 0; i < str.length(); i++) {
            String code = strArray[str.charAt(i)];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    encodedBody += "0";
                } else if (code.charAt(j) == '1') {
                    encodedBody += "1";
                }
            }
        }
    }
    
    /**
     * Encode the Huffman tree
     * @param node HuffmanNode the tree to decode
     */
    private static void encodeHuffmanNode(HuffmanNode node) {

        if (node.isLeaf()) {
            header += "" + node.value;
            return;
        } 
        
        header +=  "0";
        encodeHuffmanNode(node.left);
        encodeHuffmanNode(node.right);
    }
    
    /**
     * Decode Huffman encoded data
     * @param encodedStr String encoded string
     * @param inputNode HuffmanNode the decoded Huffman tree
     * @return String decoded string
     */
    public static String decode(String encodedStr) {
        
        String result = "";
        String inputHeader = encodedStr.substring(0, encodedStr.indexOf(sep));
        String inputBody = encodedStr.substring(encodedStr.indexOf(sep) + 3);
        HuffmanNode root = readHeader(inputHeader);
        HuffmanNode current = root;
                
        for (int i = 0; i < inputBody.length(); i++) {
    
            if (current == null) {
                break;
            }
            
            if (inputBody.charAt(i) == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            
            if (current.isLeaf()) {
                result += current.value;
                current = root;
            }
            
            if (current == null) {
                break;
            }
            
        }
        
        return result;
    }    

    /**
     * Utility method to decode the header
     * @param header String 
     * @return HuffmanNode
     */
    private static HuffmanNode readHeader(String header) {
        List<Character> chars = new List<>();
        for (int i = header.length() - 1; i >= 0; i--) {
            chars.add(header.charAt(i));
        }
        return decodeTree(chars);
    }
    
    /**
     * Decode the Huffman Tree from given list of characters
     * @param chars List<Character> input list containing characters
     * @return HuffmanNode
     */
    private static HuffmanNode decodeTree(List<Character> chars) {
        
        if (chars.size() == 0) {
            return null;
        }
        
        char c = chars.remove(chars.size() - 1);
        
        if (c != '0') {
            return new HuffmanNode(c, 1, null, null);
        } 
                
        HuffmanNode left = decodeTree(chars);
        HuffmanNode right = decodeTree(chars);
        
        return new HuffmanNode('\0', 1, left, right); 
        
    }
    
}
