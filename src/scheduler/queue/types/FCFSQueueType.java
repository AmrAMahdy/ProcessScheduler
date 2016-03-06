package scheduler.queue.types;

import java.util.LinkedList;

import scheduler.Process;
import scheduler.queue.Queue;
import scheduler.queue.Queue.QueueNode;

public class FCFSQueueType implements QueueType {
  @Override
  public QueueNode add(Queue.QueueNode head, Process process) {
    while (head.getNext() != null)
      head = head.getNext();
    head.setNext(new Queue.QueueNode(process));
    return null;
  };
}
