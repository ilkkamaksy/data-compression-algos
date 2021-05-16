# User manual

## Run the app

Run the app with

`./gradlew run`

### Compressing a file

Select a file to compress by first clicking "open a file...". Then choose to encode with either LZW or Huffman.

![ui-1](https://user-images.githubusercontent.com/47775837/118391770-ba9cd500-b63e-11eb-94cc-5fb1b02d21d3.png)

### Decompressing a file

Select a `.lzw` file to enable LZW decoding button: 

![ui-2](https://user-images.githubusercontent.com/47775837/118391834-f9328f80-b63e-11eb-80a2-5185f6a718f6.png)

Select a `.hff` file to enable Huffman decoding button:

![ui-3](https://user-images.githubusercontent.com/47775837/118391842-fdf74380-b63e-11eb-85be-faa74fc464a0.png)


## Performance tests

Run the performance tests with

`./gradlew run --args="compare"`
