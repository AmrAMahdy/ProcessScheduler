package scheduler.queue.types;
import java.util.LinkedList;

import scheduler.Process;
import scheduler.queue.Queue;
import scheduler.queue.Queue.QueueNode;

public interface QueueType {
  /*** return a node if you want to replace it with head node, otherwise you <b>must</b> return null ***/
  public QueueNode add(Queue.QueueNode list, Process process);

  public final QueueType FCFS = new FCFSQueueType();
  public final QueueType PRIORITY = new PriorityQueueType();
}
