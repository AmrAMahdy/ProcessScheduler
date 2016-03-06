package scheduler.queue.types;

import java.util.LinkedList;

import scheduler.Process;

public class FCFSQueueType implements QueueType {
  @Override
  public void add(LinkedList<Process> list, Process process) {
    list.addLast(process);
  };
}
