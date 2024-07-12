import huffman.*;
import java.util.Map;
import java.util.HashMap;

public class TestHuffmanCompression {
  public static void main(String[] args) {
    String text = "ata la jaca a la estaca";
    Map<Character, Integer> frequencyMap = new HashMap<>();

    // Count the frequency of each character in the text
    for (char c : text.toCharArray())
      frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);

    // Build Huffman Tree
    Node root = HuffmanCompression.buildHuffmanTree(frequencyMap);

    // Generate Huffman Codes
    Map<Character, String> huffmanCodes = HuffmanCompression.generateHuffmanCodes(root);

    // Compress the text
    String compressedText = HuffmanCompression.compress(text, huffmanCodes);
    System.out.println("Compressed Text: " + compressedText);

    // Decompress the text
    String decompressedText = HuffmanCompression.decompress(compressedText, root);
    System.out.println("Decompressed Text: " + decompressedText);
  }
}
