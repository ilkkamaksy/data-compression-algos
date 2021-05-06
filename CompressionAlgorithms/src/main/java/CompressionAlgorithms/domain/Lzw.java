package CompressionAlgorithms.domain;

import CompressionAlgorithms.utils.DataUtils;

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
    public static List<Byte> compress(String source) {   
        HashTable<String, Integer> dictionary = initializeCompressionDictionary();
        List<Byte> compressed = compressStringByDictionary(source, dictionary);
        return compressed;
    }
    
    /**
     * Decompresses given compressed content
     * 
     * @param compressedContent List<Integer> compressed content
     * @return extracted String
     */
    public static String decompress(List<Byte> compressedContent) {
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
    private static List<Byte> compressStringByDictionary(String uncompressedString, HashTable<String, Integer> dictionary) {
        int dictSize = dictionary.size();
        String w = "";
        List<Byte> byteList = new List<>();
        for (char character: uncompressedString.toCharArray()) {
            String wc = w + character;
            if (dictionary.containsKey(wc)) {
                w = wc;
            } else {
                addToByteList(dictionary.get(w), byteList);
                dictionary.put(wc, dictSize++);
                w = "" + character;
            }
        }
        
        if (!w.isEmpty()) {
            addToByteList(dictionary.get(w), byteList);
        }
            
        return byteList;
    }
    
    private static void addToByteList(Integer value, List<Byte> byteList) {
        byte[] bytes = DataUtils.convertIntTo2Bytes(value);
        byteList.add(bytes[0]);
        byteList.add(bytes[1]);
    }
    
    /**
     * Decompress compressed content by given dictionary
     * @param dictionary HashTable<Integer, String> 
     * @param byteList List<Integer> 
     * @return String
     */
    private static String decompressByDictionary(HashTable<Integer, String> dictionary, List<Byte> byteList) {
        if (byteList == null) {
            return null;
        }
        if (byteList.size() == 0) {
            return null;
        }
        
        int dictSize = dictionary.size();
        byte[] firstBytes = {
            byteList.remove(0),
            byteList.remove(0)
        };
        
        String w = "" + (char) DataUtils.convert2BytesToInt(firstBytes);
        String result = w;
        
        for (int i = 0; i < byteList.size() - 1; i = i + 2) {
            byte[] bytes = {
                byteList.get(i),
                byteList.get(i + 1)
            };
            int entry = DataUtils.convert2BytesToInt(bytes);
            String text = "";

            if (dictionary.containsKey(entry)) {
                text = dictionary.get(entry);
            } else if (entry == dictSize) {
                text = w + w.charAt(0);
            } 
            
            if (!text.isEmpty()) {
                dictionary.put(dictSize++, w + text.charAt(0));
            }
            result += text;
            w = text;
            
        }
        
        return result;
    }    
}
