package CompressionAlgorithms.domain;

/**
 * Min Heap for Huffman Code
 */
public class MinHeap {
    private HuffmanNode[] heap;
    private int size;
    
    public MinHeap() {
        this.size = 0;
        heap = new HuffmanNode[257];
    }
    
    /**
     * Add new element to heap
     * @param element HuffmanNode
     */
    public void add(HuffmanNode element) {
        if (size + 1 >= heap.length) {
            return;
        }
        heap[++size] = element;
        int currentIndex = size;
  
        if (currentIndex > 1) {
            int parentIndex = parent(currentIndex);
            while (heap[currentIndex].freq < heap[parentIndex].freq) {
                swap(currentIndex, parentIndex);
                currentIndex = parent(currentIndex);
            }    
        }
        
    }
    
    /**
     * Returns the HuffmanNode with smallest freq in the Heap
     * @return HuffmanNode
     */
    public HuffmanNode poll() {
        if (size == 0) {
            return null;
        }
        
        HuffmanNode root = heap[1];
        heap[1] = heap[size--];
        heapify(1);
        return root;
    }
    
    /**
     * Returns the size of the heap
     * @return int
     */
    public int size() {
        return size;
    }
    
    /**
     * Rearrange the heap to min heap
     * @param index int starting index
     */
    private void heapify(int index) {
   
        if (isLeaf(index)) {
            return;
        }

        int left = leftChild(index);
        int right = rightChild(index);
        int min = index;

        if (left <= size && heap[left].freq < heap[min].freq) {
            min = left;
        }

        if (right <= size && heap[right].freq < heap[min].freq) {
            min = right;
        }

        if (index != min) {
            swap(index, min);
            heapify(min);
        }
    }
    
    /**
     * Swap two elements
     * @param indexA int first index 
     * @param indexB int second index
     */
    private void swap(int indexA, int indexB) {
        if (indexA == indexB) {
            return;
        }
        HuffmanNode temp = heap[indexA];
        heap[indexA] = heap[indexB];
        heap[indexB] = temp;
    }
    
    /**
     * Returns the parent element index
     * @param index int index of element
     * @return int
     */
    private int parent(int index) {
        return index / 2;
    }
    
    /**
     * Returns the left child index
     * @param index int index of element
     * @return int 
     */
    private int leftChild(int index) {
        return (2 * index);
    }
    
    /**
     * Returns the right child index
     * @param index int the index of element
     * @return int
     */
    private int rightChild(int index) {
        return (2 * index) + 1;
    }
    
    /**
     * Checks if the element in given index is leaf
     * @param index int the index of given element
     * @return boolean
     */
    private boolean isLeaf(int index) {
        if (index >= (size / 2) && index <= size) {
            return true;
        }
        return false;
    }
    
    
}
