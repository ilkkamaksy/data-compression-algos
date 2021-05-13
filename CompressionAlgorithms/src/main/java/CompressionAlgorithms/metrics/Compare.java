package CompressionAlgorithms.metrics;

import CompressionAlgorithms.domain.HuffmanCode;
import CompressionAlgorithms.domain.Lzw;
import CompressionAlgorithms.domain.List;
import CompressionAlgorithms.io.Io;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Tests for comparing performance 
 * 
 */
public class Compare {
    
    public static void run() {
        
        String fileName = "../TestData/alku.txt";
        String input = Io.readFileContent(fileName);
        
        System.out.println("\n********** Huffman code results ********** \n");
        System.out.println("| Input size \t| Compressed size \t| Compression % | Avg comp time | Avg decode time |");        
        for (int i = 1; i <= 5; i++) {
            String slice = sliceInput(input, i * 10000);
            getHuffmanMetrics(slice);    
        }
        
        for (int i = 1; i <= 4; i++) {
            String slice = sliceInput(input, i * 100000);
            getHuffmanMetrics(slice);    
        }
        
        System.out.println("\n********** LZW results ********** \n");
        System.out.println("| Input size \t| Compressed size \t| Compression % | Avg comp time | Avg decode time |");              
        for (int i = 1; i <= 5; i++) {
            String slice = sliceInput(input, i * 10000);
            getLzwMetrics(slice);    
        }
        
        for (int i = 1; i <= 4; i++) {
            String slice = sliceInput(input, i * 100000);
            getLzwMetrics(slice);    
        }
        
        
    }
    
    public static String sliceInput(String input, int limit) {
        String slice = "";
        for (int i = 0; i < limit; i++) {
            slice += input.charAt(i);
        }
        
        return slice;
    }
    
    public static void getHuffmanMetrics(String input) {
        
        long encodingTimeSum = 0;
        long decodingTimeSum = 0;
        long compressedSizeSum = 0;
        
        
        for (int i = 0; i < 10; i++) {
            
            // measure encoding time
            long start = System.nanoTime();
            List<Byte> encoded = HuffmanCode.encode(input);
            long end = System.nanoTime();
            long encodeTime = TimeUnit.NANOSECONDS.toMillis(end - start); 
            
            encodingTimeSum += encodeTime;
            compressedSizeSum += encoded.size();

            // measure decoding time
            start = System.nanoTime();
            String decoded = HuffmanCode.decode(encoded);
            
            assert(decoded.equals(input));
            
            end = System.nanoTime();
            long decodeTime = TimeUnit.NANOSECONDS.toMillis(end - start);
            
            decodingTimeSum += decodeTime;
        }
        
        double avgSize = compressedSizeSum / 10;

        printResults(input, avgSize, encodingTimeSum, decodingTimeSum);
    }
    
    public static void getLzwMetrics(String input) {
        
        long encodingTimeSum = 0;
        long decodingTimeSum = 0;
        long compressedSizeSum = 0;
        
        for (int i = 0; i < 10; i++) {
            
            // measure encoding time
            long start = System.nanoTime();
            List<Byte> encoded = Lzw.compress(input);
            long end = System.nanoTime();
            long encodeTime = TimeUnit.NANOSECONDS.toMillis(end - start); 
            
            encodingTimeSum += encodeTime;
            compressedSizeSum += encoded.size();

            // measure decoding time
            start = System.nanoTime();
            String decoded = Lzw.decompress(encoded);
            
            assert(decoded.equals(input));
            
            end = System.nanoTime();
            long decodeTime = TimeUnit.NANOSECONDS.toMillis(end - start);
            
            decodingTimeSum += decodeTime;
        }
        
        double avgSize = compressedSizeSum / 10;

        printResults(input, avgSize, encodingTimeSum, decodingTimeSum);
    }
    
    public static void printResults(String input, double avgSize, long encodingTimes, long decodingTimes) {
        System.out.println(
                "| " + (input.getBytes().length / 1000) + " kb \t" +
                "| " + (avgSize / 1000) + " kb \t\t" +
                "| " + Math.round((avgSize / input.getBytes().length) * 100) + " % \t\t" +
                "| " + (encodingTimes / 10) + " ms \t\t" +
                "| " + (decodingTimes / 10) + " ms |"
        );
    }
}
