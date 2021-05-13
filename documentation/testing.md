# Performance testing

The performance of the algorithms was tested with strings of text scaling up in size from 10 kb to 500 kb. These texts were extracted from Homer's Odyssey, which is roughly 600 kb in total size. The encoding and decoding operations were repeated 10 times for each chunk of text and the results were averaged for each set of operations. These average performance metrics are displayed below.

As we can see in the tables below, LZW compression is more efficient when the file size increases above 50 kb. Huffman does a better job with relatively small data (less than 30 kb). It is however worth noting that compression involves some overhead with both algorithms, making the compressed data larger than the source with very small inputs.

Overall, the size of Huffman encoded data is consistently about 60 % of the input size, but LZW gains increasingly better results as the input size grows. With 600 kb, LZW compressed data is only 38 % of input size. 

As to the speed of execution, my goal was to reach O(n log n) time complexity with both Huffman encoding and Lempel Ziv Welch compression. 

We can see in the tables below that the performance of Huffman encoding is significantly and consistently superior to LZW. With 500 kb Huffman is almost 5 times faster than LZW. 

For decoding, my implementation of Huffman decoding clearly does not perform well. LZW decoding is consistently much, much faster.

**Table 1. Huffman Code performance**


| Input size    | Compressed size       | Comp. size %  | Avg comp time | Avg decode time |
|---------------|-----------------------|---------------|---------------|-----------------|
| 1 kb          | 0.696 kb              | 70 %          | 1 ms          | 1 ms |
| 2 kb          | 1.306 kb              | 65 %          | 1 ms          | < 1 ms |
| 3 kb          | 1.906 kb              | 64 %          | < 1 ms        | 1 ms |
| 4 kb          | 2.509 kb              | 63 %          | < 1 ms        | 2 ms |
| 5 kb          | 3.126 kb              | 63 %          | < 1 ms        | 1 ms |
| 10 kb         | 6.039 kb              | 60 %          | 2 ms          | 8 ms |
| 20 kb         | 11.913 kb             | 60 %          | 1 ms          | 21 ms |
| 30 kb         | 17.51 kb              | 58 %          | 1 ms          | 41 ms |
| 40 kb         | 23.875 kb             | 60 %          | 2 ms          | 58 ms |
| 50 kb         | 29.839 kb             | 60 %          | 2 ms          | 92 ms |
| 100 kb        | 59.58 kb              | 60 %          | 5 ms          | 373 ms |
| 200 kb        | 117.967 kb            | 59 %          | 11 ms         | 1709 ms |
| 300 kb        | 179.504 kb            | 60 %          | 19 ms         | 3745 ms |
| 400 kb        | 236.752 kb            | 59 %          | 25 ms         | 7110 ms |
| 600 kb        | 353.793 kb            | 59 %          | 39 ms         | 15045 ms |

**Table 2. LZW performance.**


| Input size    | Compressed size       | Comp. size %  | Avg comp time | Avg decode time |
|---------------|-----------------------|---------------|---------------|-----------------|
| 1 kb          | 1.154 kb              | 115 %         | < 1 ms        | < 1 ms |
| 2 kb          | 2.014 kb              | 101 %         | < 1 ms        | < 1 ms |
| 3 kb          | 2.798 kb              | 93 %          | < 1 ms        | 1 ms |
| 4 kb          | 3.544 kb              | 89 %          | 1 ms          | 1 ms |
| 5 kb          | 4.262 kb              | 85 %          | 1 ms          | 1 ms |
| 10 kb         | 7.454 kb              | 75 %          | 3 ms          | 4 ms |
| 20 kb         | 13.122 kb             | 66 %          | 2 ms          | 9 ms |
| 30 kb         | 18.328 kb             | 61 %          | 3 ms          | 13 ms |
| 40 kb         | 23.192 kb             | 58 %          | 4 ms          | 20 ms |
| 50 kb         | 27.954 kb             | 56 %          | 6 ms          | 33 ms |
| 100 kb        | 50.19 kb              | 50 %          | 15 ms         | 112 ms |
| 200 kb        | 90.454 kb             | 45 %          | 28 ms         | 378 ms |
| 300 kb        | 128.268 kb            | 43 %          | 56 ms         | 963 ms |
| 400 kb        | 163.332 kb            | 41 %          | 74 ms         | 1495 ms |
| 500 kb        | 197.242 kb            | 39 %          | 116 ms        | 2302 ms |
| 600 kb        | 229.994 kb            | 38 %          | 153 ms        | 3041 ms |
