package CompressionAlgorithms.utils;

/**
 * Class handling data type conversions: byte to int and int to byte.
 */
public class DataUtils {

    /**
     * Convert byte to integer
     * @param bits byte to convert
     * @return int
     */
    public static int convertByteToInt(byte bits) {
        return ((bits & 255) << 0); 
    }
    
    /**
     * Convert integer to byte
     * @param value int to convert
     * @return byte
     */
    public static byte convertIntToByte(int value) {
        return (byte) value;
    }
    
    /**
     * Convert byte array to integer
     * @param bytes to convert
     * @return int
     */
    public static int convert2BytesToInt(byte[] bytes) {
        return ((bytes[0] & 255) << 8) | 
               ((bytes[1] & 255) << 0);
    }

    /**
     * Convert integer to byte array
     * @param value int to convert
     * @return byte[]
     */
    public static byte[] convertIntTo2Bytes(int value) {
        return new byte[] {
            (byte) (value >> 8), 
            (byte) value
        };
    }
    
}
