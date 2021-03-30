package CompressionAlgorithms.domain;

import java.util.*;

/**
 * The Lempel Ziv Welch algorithm 
 */
public class Lzw {
    
    /**
     * Compresses a given string of text using LZW
     * 
     * @param source String to compress
     * @return compressed String 
     */
    public static List<Integer> compress(String source) {   
        Map<String, Integer> dictionary = initializeCompressionDictionary();
        List<Integer> compressed = compressStringByDictionary(source, dictionary);
        return compressed;
    }
    
    /**
     * Decompresses given compressed content
     * 
     * @param compressedContent List<Integer> compressed content
     * @return extracted String
     */
    public static String decompress(List<Integer> compressedContent) {
        Map<Integer, String> dictionary = initializeDecompressionDictionary();
        String result = decompressByDictionary(dictionary, compressedContent);
        return result;
    }
    
    /**
     * Initialize the compression dictionary
     * 
     * @return dictionary Map<String, Integer>
     */
    private static Map<String, Integer> initializeCompressionDictionary() {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char)i, i);
        }
        return dictionary;
    }
    
    /**
     * Initialize the decompression dictionary
     * 
     * @return dictionary Map<Integer, String>
     */
    private static Map<Integer, String> initializeDecompressionDictionary() {
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, "" + (char)i);
        }
        return dictionary;
    }
    
    /**
     * Compress string using a given dictionary
     * @param uncompressedString String to compress
     * @param dictionary Map<String, Integer> dictionary
     * @return List<Integer>
     */
    private static List<Integer> compressStringByDictionary(String uncompressedString, Map<String, Integer> dictionary) {
        String w = "";
        List<Integer> result = new ArrayList<>();
        for (char character: uncompressedString.toCharArray()) {
            String currentAndNext = w + character;
            if (dictionary.containsKey(currentAndNext)) {
                w = currentAndNext;
            } else {
                result.add(dictionary.get(w));
                dictionary.put(currentAndNext, dictionary.size() + 1);
                w = "" + character;
            }
        }
        
        if (!w.equals("")) {
            result.add(dictionary.get(w));
        }
            
        return result;
     }
    
    /**
     * Decompress compressed content by given dictionary
     * @param dictionary Map<Integer, String> 
     * @param compressedContent List<Integer> 
     * @return String
     */
    private static String decompressByDictionary(Map<Integer, String> dictionary, List<Integer> compressedContent) {
        String w = "" + (char)(int)compressedContent.remove(0);
        StringBuffer result = new StringBuffer(w);
        
        for (int i : compressedContent) {
            String text = "";
            if (dictionary.containsKey(i)) {
                text = dictionary.get(i);
            } else if (i == dictionary.size()) {
                text = w + w.charAt(0);
            } 
            
            if (!text.isEmpty()) {
                result.append(text);
                dictionary.put(dictionary.size() + 1, w + text.charAt(0));
                w = text;
            }
            
        }
        
        return result.toString();
    }
}
