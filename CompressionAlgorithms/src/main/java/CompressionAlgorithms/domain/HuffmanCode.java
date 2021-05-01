package CompressionAlgorithms.domain;

import java.util.PriorityQueue;

/**
 * The Huffman code algorithm
 */
public class HuffmanCode {
    
    public static List<Character> encodedContent;
    public static char sep = '#';
    
    /**
     * Encode a given string with Huffman Code
     * @param inputStr String
     * @return List<Character> encoded content
     */
    public static List<Character> encode(String inputStr) {
       
        encodedContent = new List<>();
        HuffmanNode root = null;

        MinHeap queue = initializeQueue(inputStr);

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll(); 
            HuffmanNode parent = new HuffmanNode('\0', left.freq + right.freq, left, right);
            queue.add(parent);
        }

        root = queue.poll();
    
        String[] code = new String[256];
        buildHuffmanCode(code, root, "");

        encodeHuffmanNode(root);
        encodedContent.add(sep);

        encodeInputByHuffmanCode(code, inputStr);
   
        return encodedContent;
    }
    
   
    /**
     * Initialize the priority queue for encoding
     * @param inputStr String 
     * @return MinHeap
     */
    private static MinHeap initializeQueue(String inputStr) {
        
        MinHeap queue = new MinHeap();
        
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
     * @param code String[] array of chars as string
     * @param node HuffmanNode
     * @param str String result
     */
    private static void buildHuffmanCode(String[] code, HuffmanNode node, String str) {
        if (!node.isLeaf()) {            
            buildHuffmanCode(code, node.left,  str + '0');
            buildHuffmanCode(code, node.right, str + '1');
        } else {
            code[node.value] = str;
        }
    }
    
    
    /**
     * Use Huffman code to encode input
     * @param codes String[] array of string
     * @param input String input string
     */
    private static void encodeInputByHuffmanCode(String[] codes, String input) {
        for (int i = 0; i < input.length(); i++) {
            String code = codes[input.charAt(i)];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    encodedContent.add('0');
                } else if (code.charAt(j) == '1') {
                    encodedContent.add('1');
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
            encodedContent.add(node.value);
            return;
        } 
        
        encodedContent.add('0');
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
        String inputBody = encodedStr.substring(encodedStr.indexOf(sep) + 1);
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
