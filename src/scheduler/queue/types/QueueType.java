package scheduler.queue.types;
import java.util.LinkedList;

import scheduler.Process;

public interface QueueType {
  public void add(LinkedList<Process> list, Process process);

  public final QueueType FCFS = new FCFSQueueType();
}
