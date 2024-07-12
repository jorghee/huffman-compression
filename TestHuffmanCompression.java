import huffman.*;
import java.util.Map;
import java.util.HashMap;

public class TestHuffmanCompression {
  public static void main(String[] args) {
    String text = "ata la jaca a la estaca";
    Map<Character, Integer> frequencyMap = new HashMap<>();

    // Paso 1: Calcular las frecuencias de los caracteres
    frequencyMap = HuffmanCompression.buildFrequencyMap(text);

    // Imprimir frecuencias de los caracteres
    HuffmanCompression.printCharacterFrequencies(frequencyMap);

    // Imprimir nodos ordenados por su frecuencia de menor a mayor
    HuffmanCompression.printSortedNodes(frequencyMap);

    // Paso 2: Construir el árbol de Huffman
    Node root = HuffmanCompression.buildHuffmanTree(frequencyMap);

    // Imprimir el árbol de Huffman en formato gráfico
    System.out.println("\nHuffman Tree:");
    HuffmanCompression.printHuffmanTree(root);

    // Paso 3: Generar los códigos de Huffman
    Map<Character, String> huffmanCodes = HuffmanCompression.generateHuffmanCodes(root);
    System.out.println("\nHuffman Codes:");
    for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
    }

    // Paso 4: Comprimir el texto
    String compressedText = HuffmanCompression.compress(text, huffmanCodes);
    System.out.println("\nCompressed Text:");
    System.out.println(compressedText);

    // Paso 5: Descomprimir el texto
    String decompressedText = HuffmanCompression.decompress(compressedText, root);
    System.out.println("\nDecompressed Text:");
    System.out.println(decompressedText + "\n");
  }
}
