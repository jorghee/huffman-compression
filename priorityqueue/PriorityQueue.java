package priorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<T> {
  private ArrayList<PriorityQueueNode<T>> heap;
  private Comparator<PriorityQueueNode<T>> comparator;

  public PriorityQueue() {
    this.heap = new ArrayList<>();
    this.comparator = Comparator.comparingInt(node -> node.priority);
  }

  public void add(T data, int priority) {
    PriorityQueueNode<T> newNode = new PriorityQueueNode<>(data, priority);
    heap.add(newNode);
    heapifyUp(heap.size() - 1);
  }

  public T poll() {
    if (heap.isEmpty()) throw new NoSuchElementException("Priority queue is empty");

    T result = heap.get(0).data;
    PriorityQueueNode<T> lastNode = heap.remove(heap.size() - 1);
    if (!heap.isEmpty()) {
      heap.set(0, lastNode);
      heapifyDown(0);
    }

    return result;
  }

  public boolean isEmpty() {
    return heap.isEmpty();
  }

  public int size() {
    return heap.size();
  }

  private void heapifyUp(int index) {
    while (index > 0) {
      int parentIndex = (index - 1) / 2;
      if (comparator.compare(heap.get(index), heap.get(parentIndex)) >= 0) break;

      swap(index, parentIndex);
      index = parentIndex;
    }
  }

  private void heapifyDown(int index) {
    int leftChild, rightChild, smallest;

    while ((leftChild = 2 * index + 1) < heap.size()) {
      rightChild = leftChild + 1;
      smallest = index;

      if (comparator.compare(heap.get(leftChild), heap.get(smallest)) < 0)
        smallest = leftChild;

      if (rightChild < heap.size() &&
          comparator.compare(heap.get(rightChild), heap.get(smallest)) < 0)
        smallest = rightChild;

      if (smallest == index) break;

      swap(index, smallest);
      index = smallest;
    }
  }

  private void swap(int i, int j) {
    PriorityQueueNode<T> temp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, temp);
  }
}
