# Implementation

## Huffman code

The algorithm is based on creating and traversing a binary tree where code words are arranged by their weights. For this I have implemented a binary tree which is is constructed using a custom min heap implementation. The tree is built in the following procedure:

1. first we calculate frequencies for each symbol in the input.
2. Next, the symbols with their respective frequencies are placed as nodes into the min heap. 
3. Finally, the root of the min heap is taken as the Huffman tree.

The Huffman code is then constructed using the tree by iterating through each item in the input and fetching the corresponding code word from the tree, the code word being either zero or one. These bits are then saved in one byte sized chunks to the target file. Because the bit length of the last chunk can vary, we save one additional byte to the end of the code to indicate whether or not the last byte in the code has 8 significant bits. This is later used in the decoding process to determine the number of significant bits in the last byte.

In order to decode the data later, the binary tree is saved to the beginning of the target file as a header where symbol # represents the end of header. To ease the decoding, I am using one byte per symbol when saving the header, which creates some extra overhead. 

When decoding, the Huffman tree is first recreated from the header of the input. This is done by locating the end of header and adding all symbols in the  header segment to a list in reverse order, so that the first symbol of the header is the last item on the list. The header is then decoded by removing items one by one from the end of the list. Because the list is in reverse order, removing items from the list can be done in constant time. 

Finally, the header is used to decode rest of the input. When encoding, the algorithm reads the input code one bit at a time and traverses the reconstructed tree starting from the root. If the current input bit is zero, we choose the left child node and if the current input bit is one, we choose the right child node. When a leaf node is reached, the value of that node is concatenated to the decoded result. 

The time complexity for my Huffman implementation is O(n log n), as searching the min heap requires O(log n) and there are O(n) iterations in total (one for each character in the input).

## Lempel Ziv Welch

LZW starts by building a dictionary of 256 characters into a sequential hash table. Then data is read one character (8 bits) at a time and every item is encoded using as the number that represents the index of the character in the dictionary. When a new substring is encountered, it is added to the dictionary. When a substring is found in the dictionary, we read in a new character and concatenate it with the current string in order to create a new substring. The next time LZW revisits a substring, it is encoded using a single number. The encoded data is added to a simple list and stored as byte array in a given target file.

The LZW decoding is done by reading the numeric values in the encoded input and using those values as index keys to the dictionary. The values in matching indexes are then concatenated to the current working string which is also added to the dictionary. The current working string is taken as the decoded end result and the process repeats. 

The time complexity for my LZW decoding should be in fact O(n), as each search in a fixed size dictionary is technically speaking O(1) (max 256 comparisons). However, I am storing the dictionary in a hash table, where the size of the dictionary and the size of the strings it contains may have negative effects on memory usage and speed of execution. 
