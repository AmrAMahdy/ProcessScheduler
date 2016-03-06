package scheduler.queue.types;

import java.util.LinkedList;

import scheduler.Process;
import scheduler.queue.Queue;
import scheduler.queue.Queue.QueueNode;

public class RounRobinQueueType implements QueueType {
  int period;

  public RounRobinQueueType(int period) {
    this.period = period;
  }

  public int getPeriod() {
    return period;
  }

  @Override
  public QueueNode add(Queue.QueueNode head, Process process) {
    while (head.getNext() != null)
      head = head.getNext();
    head.setNext(new Queue.QueueNode(process));
    return null;
  };
}
