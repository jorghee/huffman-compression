package priorityqueue;

public class PriorityQueueNode<T> {
  public T data;
  public int priority;

  public PriorityQueueNode(T data, int priority) {
    this.data = data;
    this.priority = priority;
  }
}
