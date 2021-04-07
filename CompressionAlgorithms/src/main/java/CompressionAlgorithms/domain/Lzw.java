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
            String wc = w + character;
            if (dictionary.containsKey(wc)) {
                w = wc;
            } else {
                result.add(dictionary.get(w));
                dictionary.put(wc, dictionary.size() + 1);
                w = "" + character;
            }
        }
        
        if (!w.isEmpty()) {
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
        if (compressedContent == null) return null;
        if (compressedContent.size() == 0) return null;
        
        String w = "" + (char)(int)compressedContent.remove(0);
        StringBuffer result = new StringBuffer(w);
        
        for (int i = 0; i < compressedContent.size(); i++) {
            int entry = compressedContent.get(i);
            String text = "";
            
            if (dictionary.containsKey(entry)) {
                text = dictionary.get(entry);
            } else if (entry == dictionary.size()) {
                text = w + w.charAt(0);
            } 
            
            if (!text.isEmpty()) {
                dictionary.put(dictionary.size() + 1, w + text.charAt(0));
            }
            result.append(text);
            w = text;
            
        }
        
        return result.toString();
    }
}
