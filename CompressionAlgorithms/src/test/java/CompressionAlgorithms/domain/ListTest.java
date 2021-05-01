package CompressionAlgorithms.domain;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListTest {
    
    @Test
    public void stringCanBeAddedToList() {
        List<String> list = new List();
        list.add("foo");
        assertEquals(1, list.size());
        assertEquals("foo", list.get(0));
    }
    
    @Test
    public void intCanBeAddedToList() {
        int val = 10;
        List<Integer> list = new List();
        list.add(val);
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(val), list.get(0));
    }
    
    @Test
    public void nullCanNotBeAddedToList() {
        List<String> list = new List();
        list.add(null);
        assertEquals(0, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getFromEmptyListThrowsException() {
        List list = new List();
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeFromEmptyListThrowsException() {
        List list = new List();
        Object result = list.remove(0);
    }
    
    @Test
    public void removeFromListReturnsTheValue() {
        List<String> list = new List();
        list.add("foo");
        list.add("bar");
        String result = list.remove(0);
        assertEquals("foo", result);
        assertEquals(1, list.size());
    }
    
    @Test
    public void removeFromListRemovesTheValue() {
        List<String> list = new List();
        list.add("foo");
        String result = list.remove(0);
        assertFalse("foo", list.contains("foo"));
        assertEquals(0, list.size());
    }

    @Test
    public void containsReturnsTrueWhenFound() {
        List<String> list = new List();
        list.add("foo");
        boolean result = list.contains("foo");
        assertEquals(true, result);
    }
    
    @Test
    public void containsReturnsFalseWhenNotFound() {
        List<String> list = new List();
        list.add("foo");
        boolean result = list.contains("bar");
        assertEquals(false, result);
    }
    
    @Test
    public void getSizeReturnsCorrectSize() {
        
        Random rand = new Random();
        List<Integer> list = new List();
        for (int i = 0; i < 100; i++) {
            list.add(rand.nextInt(1000));
        }
        
        int result = list.size();
        assertEquals(100, result);
    }

    
}
