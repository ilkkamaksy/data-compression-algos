/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompressionAlgorithms.domain;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilkka
 */
public class HashTableTest {
    
    public HashTableTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getNonPresentKeyReturnsNull() {
        HashTable instance = new HashTable();
        Object result = instance.get(0);
        assertEquals(null, result);
    }
    
    @Test
    public void getValidKeyReturnsValue() {
        HashTable<String, String> instance = new HashTable<>();
        instance.put("a", "foo");
        instance.put("b", "bar");
        Object result = instance.get("a");
        assertEquals("foo", result);
    }

    @Test
    public void putValidKeyAdded() {
        HashTable<String, String> instance = new HashTable<>();
        instance.put("foo", "bar");
        assertEquals(1, instance.size());
        assertEquals("bar", instance.get("foo"));
    }
    
    @Test
    public void removeNonPresentReturnsNull() {
        HashTable<String, String> instance = new HashTable();
        assertNull(instance.remove("foo"));
    }
    
    @Test
    public void removeValidKeyRemovesPairFromTable() {
        HashTable<String, String> instance = new HashTable();
        instance.put("foo", "bar");
        String result = instance.remove("foo");
        assertEquals("bar", result);
        assertFalse(instance.containsKey("foo"));
        assertNull(instance.get("foo"));
    }

    @Test
    public void containsNonPresentKeyReturnsFalse() {
        HashTable<String, String> instance = new HashTable();
        instance.put("bar", "baz");
        assertFalse(instance.containsKey("foo"));
    }
    
    @Test
    public void containsPresentKeyReturnsTrue() {
        HashTable<String, String> instance = new HashTable();
        instance.put("bar", "baz");
        assertTrue(instance.containsKey("bar"));
    }

    @Test
    public void emptyTableSizeIsZero() {
        HashTable instance = new HashTable();
        int result = instance.size();
        assertEquals(0, result);
    }
    
    @Test
    public void tableSizeIsCorrect() {
        Random rand = new Random();
        
        HashTable<Integer, Integer> instance = new HashTable();
        
        for (int i = 0; i < 100; i++) {
            instance.put(i, rand.nextInt(1000));
        }
        int result = instance.size();
        assertEquals(100, result);
    }
    
}
