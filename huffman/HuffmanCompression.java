package huffman;

import priorityqueue.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

  public static Map<Character, Integer> buildFrequencyMap(String text) {
    Map<Character, Integer> frequencyMap = new HashMap<>();
    for (char c : text.toCharArray()) {
      frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
    }
    return frequencyMap;
  }

  // Method to print the Huffman tree in a graphical format
  public static void printHuffmanTree(Node node) {
    printHuffmanTreeHelper(node, "", true);
  }

  private static void printHuffmanTreeHelper(Node node, String prefix, boolean isTail) {
    if (node != null) {
      System.out.println(prefix + (isTail ? "└── " : "├── ") + (node.left == null && node.right == null 
        ? "Leaf: '" + node.character + "' -> Freq: " + node.frequency  
        : "Freq: " + node.frequency));

      if (node.left != null || node.right != null) {
        printHuffmanTreeHelper(node.right, prefix + (isTail ? "    " : "│   "), false);
        printHuffmanTreeHelper(node.left, prefix + (isTail ? "    " : "│   "), true);
      }
    }
  }

  // Method to display character frequencies
  public static void printCharacterFrequencies(Map<Character, Integer> frequencyMap) {
    System.out.println("Character Frequencies:");
    for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }
    System.out.println(" ");
  }

  // Method to get and print nodes sorted by frequency
  public static void printSortedNodes(Map<Character, Integer> frequencyMap) {
    List<Node> nodes = new ArrayList<>();
    for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
      nodes.add(new Node(entry.getKey(), entry.getValue()));
    }
    Collections.sort(nodes, (n1, n2) -> Integer.compare(n1.frequency, n2.frequency));
    
    System.out.println("Nodes sorted by frequency:");
    for (Node node : nodes) {
      System.out.println("Character: " + node.character + ", Frequency: " + node.frequency);
    }
  }
}


