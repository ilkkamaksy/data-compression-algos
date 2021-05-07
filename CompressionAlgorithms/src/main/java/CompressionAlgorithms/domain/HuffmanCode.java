package CompressionAlgorithms.domain;

import CompressionAlgorithms.utils.DataUtils;

/**
 * The Huffman code algorithm
 */
public class HuffmanCode {
    
    public static List<Byte> encodedContent;
    public static char endOfHeader = '#';
    private static int buffer;
    private static int n;
    
    /**
     * Encode a given string with Huffman Code
     * @param inputStr String
     * @return List<Byte> encoded content
     */
    public static List<Byte> encode(String inputStr) {
       
        encodedContent = new List<>();

        HuffmanNode root = buildHuffmanTree(inputStr);
    
        String[] codes = new String[256];
        buildHuffmanCode(codes, root, "");

        buildHeader(root);
        encodeInputByHuffmanCode(codes, inputStr);
        
        for (int i = 0; i < encodedContent.size(); i++) {
            String foo = Integer.toBinaryString(encodedContent.get(i));
        }
        
        return encodedContent;
    }

    /**
     * Build the Huffman tree
     * @param inputStr String the input string
     * @return HuffmanNode the Huffman tree
     */
    private static HuffmanNode buildHuffmanTree(String inputStr) {
        
        MinHeap queue = initializeQueue(inputStr);
        
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll(); 
            HuffmanNode parent = new HuffmanNode('\0', left.freq + right.freq, left, right);
            queue.add(parent);
        }
        return queue.poll();
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
     * Utility method to build the header
     * @param node HuffmanNode the Huffman tree
     */
    private static void buildHeader(HuffmanNode node) {
        encodeHuffmanHeader(node);
        encodedContent.add((byte) endOfHeader);     // add the end of header 
    }
    
    /**
     * Encode the Huffman tree
     * @param node HuffmanNode the tree to decode
     */
    public static void encodeHuffmanHeader(HuffmanNode node) {
        if (node.isLeaf()) {
            encodedContent.add((byte) node.value);
            return;
        } 
        
        encodedContent.add((byte) '0');
        encodeHuffmanHeader(node.left);
        encodeHuffmanHeader(node.right);
    }
    
    /**
     * Use Huffman code to encode input
     * @param codes String[] array of Huffman codes
     * @param input String the input string
     */
    private static void encodeInputByHuffmanCode(String[] codes, String input) {
        for (int i = 0; i < input.length(); i++) {
            String code = codes[input.charAt(i)];
            for (int j = 0; j < code.length(); j++) {
               
                if (code.charAt(j) == '0') {
                    addBitToBuffer(0);
                } else if (code.charAt(j) == '1') {
                    addBitToBuffer(1);
                }
                
                // If the bitcount of the last item is 0 < x < 8, add 0 to the end, else 1. Value 1 = 8 significant bits
                if (i == input.length() - 1 && j == code.length() - 1) {
                    if (n > 0 && n < 8) {
                        clearBuffer();
                        encodedContent.add((byte) 0);
                    } else {
                        encodedContent.add((byte) 1);
                    }
                    
                }
            }
        }
    }
    
    /**
     * Add a bit to the buffer
     * @param bit int 0 or 1
     */
    private static void addBitToBuffer(int bit) {     
        buffer <<= 1;
        if (bit == 1) {
            buffer |= 1;
        }
       
        n++;
        if (n == 8) {
            clearBuffer();
        }
    } 
    
    /**
     * Add the buffer to result list and clear
     */
    private static void clearBuffer() {
        if (n == 0) {
            return;
        }
        encodedContent.add(DataUtils.convertIntToByte(buffer));
        
        n = 0;
        buffer = 0;
    }
    

    /**
     * Decode Huffman encoded data
     * @param byteList List<Byte> encoded input
     * @return String decoded string
     */
    public static String decode(List<Byte> byteList) {
        
        String result = "";
        
        int endOfHeaderIndex = getEndOfHeaderIndex(byteList);
        
        HuffmanNode root = readHeader(byteList, endOfHeaderIndex);
        HuffmanNode current = root;
                
        for (int i = endOfHeaderIndex + 1; i < byteList.size() - 1; i++) {

            int entry = DataUtils.convertByteToInt(byteList.get(i));
            String bits = intToBinaryString(entry);
            
            // If the bitcount of the last entry is not 8, ignore the extra bits
            if (i == byteList.size() - 2 && byteList.get(byteList.size() - 1) == 0) {
                bits = Integer.toBinaryString(entry);
            }
            
            for (int j = 0; j < bits.length(); j++) {
                
                if (current == null) {
                    break;
                }

                if (bits.charAt(j) == '0') {
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

        }
        
        return result;
    }   
    
    /**
     * Get the end of header index
     * @param byteList List<Byte> list of bytes
     * @return int index
     */
    private static int getEndOfHeaderIndex(List<Byte> byteList) {
        int index = -1;
        for (int i = 0; i < byteList.size(); i++) {
            if ((char) DataUtils.convertByteToInt(byteList.get(i)) == '#') {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Utility method to decode the header
     * @param header String 
     * @return HuffmanNode
     */
    private static HuffmanNode readHeader(List<Byte> byteList, int endIndex) {
        List<Character> chars = new List<>();
        for (int i = endIndex - 1; i >= 0; i--) {
            chars.add((char) (int) byteList.get(i));
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
   
   /**
     * Integer to 8 bit string 
     * @param value int to translate
     * @return String s
     */
    private static String intToBinaryString(int value) {
        String s = Integer.toBinaryString(value);
        if (s.length() < 8) {
            String prefix = "";
            for (int j = 0; j < (8 - s.length()); j++) {
                prefix += "0";
            }
            s = prefix + s;
        }
        return s;
    }
}