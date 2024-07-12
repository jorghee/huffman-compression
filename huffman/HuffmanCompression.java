package huffman;

import priorityqueue.*;
import java.util.Map;
import java.util.HashMap;

public class HuffmanCompression {

  public static Node buildHuffmanTree(Map<Character, Integer> frequencyMap) {
    PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

    // Create a leaf node for each character and add it to the priority queue
    for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet())
      priorityQueue.add(new Node(entry.getKey(), entry.getValue()), entry.getValue());

    /* While there are more than one nodes in the queue, merge the two nodes with the
     * lowest frequency.
     */
    Node left, right, mergedNode;
    while (priorityQueue.size() > 1) {
      left = priorityQueue.poll();
      right = priorityQueue.poll();
      mergedNode = new Node(left.frequency + right.frequency, left, right);
      priorityQueue.add(mergedNode, mergedNode.frequency);
    }

    // The remaining node is the root of the Huffman tree
    return priorityQueue.poll();
  }
  
  public static Map<Character, String> generateHuffmanCodes(Node root) {
    Map<Character, String> huffmanCodes = new HashMap<>();
    generateCodesHelper(root, "", huffmanCodes);
    return huffmanCodes;
  }

  private static void generateCodesHelper(Node node, String code, Map<Character, String> huffmanCodes) {
    if (node == null) return;

    // If it's a leaf node, store the code
    if (node.left == null && node.right == null)
      huffmanCodes.put(node.character, code);

    generateCodesHelper(node.left, code + "0", huffmanCodes);
    generateCodesHelper(node.right, code + "1", huffmanCodes);
  }

  public static String compress(String text, Map<Character, String> huffmanCodes) {
    StringBuilder compressedText = new StringBuilder();
    for (char c : text.toCharArray())
      compressedText.append(huffmanCodes.get(c));

    return compressedText.toString();
  }

  public static String decompress(String compressedText, Node root) {
    StringBuilder decompressedText = new StringBuilder();
    Node currentNode = root;
    for (char bit : compressedText.toCharArray()) {
      currentNode = (bit == '0') ? currentNode.left : currentNode.right;

      // If it's a leaf node, append the character to the decompressed text
      if (currentNode.left == null && currentNode.right == null) {
        decompressedText.append(currentNode.character);
        currentNode = root;
      }
    }
    return decompressedText.toString();
  }
}
