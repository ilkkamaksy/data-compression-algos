package CompressionAlgorithms.domain;

import java.util.HashMap;
import java.util.Map;

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
        HashTable<String, Integer> dictionary = initializeCompressionDictionary();
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
        HashTable<Integer, String> dictionary = initializeDecompressionDictionary();
        String result = decompressByDictionary(dictionary, compressedContent);
        return result;
    }
    
    /**
     * Initialize the compression dictionary
     * 
     * @return dictionary HashTable<String, Integer>
     */
    private static HashTable<String, Integer> initializeCompressionDictionary() {
        HashTable<String, Integer> dictionary = new HashTable<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char) i, i);
        }
        return dictionary;
    }
    
    /**
     * Initialize the decompression dictionary
     * 
     * @return dictionary HashTable<Integer, String>
     */
    private static HashTable<Integer, String> initializeDecompressionDictionary() {
        HashTable<Integer, String> dictionary = new HashTable<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, "" + (char) i);
        }
        return dictionary;
    }
    
    /**
     * Compress string using a given dictionary
     * @param uncompressedString String to compress
     * @param dictionary HashTable<String, Integer> dictionary
     * @return List<Integer>
     */
    private static List<Integer> compressStringByDictionary(String uncompressedString, HashTable<String, Integer> dictionary) {
        int dictSize = dictionary.size();
        String w = "";
        List<Integer> result = new List<>();
        for (char character: uncompressedString.toCharArray()) {
            String wc = w + character;
            if (dictionary.containsKey(wc)) {
                w = wc;
            } else {
                result.add(dictionary.get(w));
                dictionary.put(wc, dictSize++);
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
     * @param dictionary HashTable<Integer, String> 
     * @param compressedContent List<Integer> 
     * @return String
     */
    private static String decompressByDictionary(HashTable<Integer, String> dictionary, List<Integer> compressedContent) {
        if (compressedContent == null) {
            return null;
        }
        if (compressedContent.size() == 0) {
            return null;
        }
        
        int dictSize = dictionary.size();
        String w = "" + (char) (int) compressedContent.remove(0);
        StringBuffer result = new StringBuffer(w);
        
        for (int i = 0; i < compressedContent.size(); i++) {
            int entry = compressedContent.get(i);
            String text = "";
            
            if (dictionary.containsKey(entry)) {
                text = dictionary.get(entry);
            } else if (entry == dictSize) {
                text = w + w.charAt(0);
            } 
            
            if (!text.isEmpty()) {
                dictionary.put(dictSize++, w + text.charAt(0));
            }
            result.append(text);
            w = text;
            
        }
        
        return result.toString();
    }
}
