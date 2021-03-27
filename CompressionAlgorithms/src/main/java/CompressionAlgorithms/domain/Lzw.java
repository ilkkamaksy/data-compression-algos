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
    public static String compress(String source) {
        
        Map<String, Integer> dictionary = initializeDictionary();
        
        List<Integer> compressed = compressStringByDictionary(source, dictionary);
        
        return compressed.toString();
    }
    
    /**
     * Initialize the dictionary with 256 characters
     * 
     * @return dictionary Map<String, Integer>
     */
    private static Map<String, Integer> initializeDictionary() {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char)i, i);
        }
        return dictionary;
    }
    
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
     * Extracts a given compressed string 
     * 
     * @param content String of compressed content
     * @return extracted String
     */
    public static String extract(String content) {
        return "extracted";
    }
}
