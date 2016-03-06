package scheduler.queue.types;

import scheduler.Process;
import scheduler.queue.Queue;
import scheduler.queue.Queue.QueueNode;

public class PriorityQueueType implements QueueType {
  @Override
  public QueueNode add(Queue.QueueNode head, Process process) {
    QueueNode newNode = new QueueNode(process);

    // Before head
    if (process.getPriority() < head.getProcess().getPriority()) {
      newNode.setNext(head);
      return newNode;
    }

    // Search for the node before the inserted one
    while (head.getNext() != null) {
      if (process.getPriority() < head.getNext().getProcess().getPriority()) {
        newNode.setNext(head.getNext());
        head.setNext(newNode);
        return null;
      }

      head = head.getNext();
    }

    head.setNext(newNode);

    return null;
  }
}
