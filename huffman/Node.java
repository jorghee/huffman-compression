package huffman;

public class Node implements Comparable<Node> {
  char character;
  int frequency;
  Node left;
  Node right;

  Node(char character, int frequency) {
    this.character = character;
    this.frequency = frequency;
    this.left = null;
    this.right = null;
  }

  Node(int frequency, Node left, Node right) {
    this.character = '\0'; // Null character for internal nodes
    this.frequency = frequency;
    this.left = left;
    this.right = right;
  }

  @Override
  public int compareTo(Node other) {
    return this.frequency - other.frequency;
  }

  @Override
  public String toString() {
    return character + ": " + frequency;
  }
}
