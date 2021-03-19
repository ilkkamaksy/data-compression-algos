# Specifications 

The goal of this program is to compare the performance of data compression algorithms, namely Huffman and Lempel-Ziv-Welch. More specifically, the goal is to compare the average speed of execution in relation to the data compression level of these algorithms. 

The motivation for this project is to build ground for understanding LZ77-Huffman variants [utilized for example in lossles WebP encoding](https://developers.google.com/speed/webp/docs/compression).

### Time and space complexity

For Huffman, the goal is to reach O(n log n) time complexity and O(n) space complexity. 

In the case of LZW, the goal is to reach O(log n) time complexity and O(n) space complexity.

## Data structures

Huffman algorithm involves binary tree and priority queue data structures. LZW involves a hash table.

## Input data

The program will accept text files as input data which will be compressed using the selected algorithm. Additionally, the compressed files will be decompressed with the appropriate algorithm.

## Programming language

Java.

## Language of the project

English.

## Study program

Bachelor’s in science (bSc).

## References

- [Huffman coding - Wikipedia](https://en.wikipedia.org/wiki/Huffman_coding)
- [Huffman encoding - Auckland university computer science department](https://www.cs.auckland.ac.nz/software/AlgAnim/huffman.html)
- [Lempel-Ziv-Welch - Wikipedia](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch)
- [Lempel-Ziv-Welch - Cardiff School of computer science & informatics](https://users.cs.cf.ac.uk/Dave.Marshall/Multimedia/node214.html)
- [Lempel-Ziv-Welch Compression Algorithm with Exponential Decay](https://doi.org/10.1109/SMRLO.2016.108)

