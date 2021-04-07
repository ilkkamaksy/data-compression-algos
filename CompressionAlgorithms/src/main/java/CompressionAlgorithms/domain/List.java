package CompressionAlgorithms.domain;

import java.io.Serializable;

/**
 * Class for a simple list 
 */
public class List<T> implements Serializable {
    
    private T[] values;
    private int size;

    public List() {
        this.values = (T[]) new Object[32];
        this.size = 0;
    }
    
    /**
     * Add a value to the list
     * @param value the value to be added
     */
    public void add(T value) {
        if (this.size == this.values.length) {
            this.grow();
        }
        this.values[this.size] = value;
        this.size++;
    }
   
    /**
     * Get a value from index
     * @param index the index to get the value from
     * @return found value
     */
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds of [0, " + this.size + "].");
        }

        return this.values[index];
    }
    
    /**
     * Remove a value from the list
     * @param value to be removed
     * @return the removed value
     */
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds of [0, " + this.size + "].");
        }
        
        T value = this.values[index];

        this.moveValuesLeft(index);
        this.size--;
        return value;
    }
    
    /**
     * Check if the list contains a value
     * @param value the value to check
     * @return boolean
     */
    public boolean contains(T value) {
        for (int i = 0; i < this.size; i++) {
            if (this.values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get the size of the list
     * @return integer
     */
    public int size() {
        return this.size;
    }
    
    /**
     * Increases the size of the list by 1.5
     */
    private void grow() {
        int newSize = this.values.length + this.values.length / 2;
        T[] newList = (T[]) new Object[newSize];
        for (int i = 0; i < this.values.length; i++) {
            newList[i] = this.values[i];
        }

        this.values = newList;
    }
   
    /**
     * Move values to left starting from index
     * @param startIndex int the index to start from
     */
    private void moveValuesLeft(int startIndex) {
        for (int i = startIndex; i < this.size - 1; i++) {
            this.values[i] = this.values[i + 1];
        }
    }

    @Override
    public String toString() {
        String out = "[";
        for (int i = 0; i < this.size; i++) {
            out += "" + this.values[i];
            if (i < this.size - 1) {
                out += ", ";
            }
        }
        out += "]";
        return out;
    }
    
    
}
