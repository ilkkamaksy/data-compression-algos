# Testing

This document describes what has been tested and how, what kind of inputs have been used and how the tests can be repeated. 

## Lzw.java 

1. **Content is succesfully compressed 1.**

Input: `TOBEORNOTTOBEORTOBEORNOT`

Expected result: following array is returned `[84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263]`

2. **Content is succesfully compressed 2.**

Input: `YESNOHUFTHUFTHUFYHUFYHUFYHK`

Expected result: following array is returned `[89, 69, 83, 78, 79, 72, 85, 70, 84, 261, 263, 265, 89, 267, 269, 75]`

3. **Compressed content is succesfully decompressed.**

Input: `TOBEORNOTTOBEORTOBEORNOT` compressed.

Expected result: String `TOBEORNOTTOBEORTOBEORNOT`

## Io.java

1. **A text file content is read correctly.** 

Input file name: `testfile.txt` with content: `"TOBEORNOTTOBEORTOBEORNOT"`

Expected result: the content of the saved file is `TOBEORNOTTOBEORTOBEORNOT`

2. **Reading Invalid file content returns null.** 

Input file name: `""`.

Expected result: reading the file content returns null.

3. **A valid file is successfully saved.** 

Input file name: `tempFile.txt` with content: `"temp content"`

Expected result: the path of the saved file checks out.

4. **Saving a file without a file name returns false.** 

Input file name: `""` with content: `"temp content"`

Expected result: saving method returns false.