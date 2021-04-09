package CompressionAlgorithms.utils;

/**
 * Class handling data type conversions: byte to int and int to byte.
 */
public class DataUtils {

    /**
     * Convert byte array to integer
     * @param bytes to convert
     * @return integer
     */
    public static int convertByteArrayToInt(byte[] bytes) {
        return ((bytes[0] & 255) << 24) | 
               ((bytes[1] & 255) << 16) | 
               ((bytes[2] & 255) << 8) | 
               ((bytes[3] & 255) << 0);
    }

    /**
     * Convert integer to byte array
     * @param value int to convert
     * @return byte[]
     */
    public static byte[] convertIntToByteArray(int value) {
        return new byte[] {
            (byte) (value >> 24), 
            (byte) (value >> 16), 
            (byte) (value >> 8), 
            (byte) value
        };
    }
    
}
