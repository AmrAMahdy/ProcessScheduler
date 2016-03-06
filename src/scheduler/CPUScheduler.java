package scheduler;

import scheduler.queue.Queue;
import scheduler.queue.types.QueueType;

public class CPUScheduler {
  private Queue queue;

  public CPUScheduler(QueueType queueType) {
    queue = new Queue(queueType);
  }

  public void addProcess(Process process) {
    queue.add(process);
  }

  public void printQueue() {
    for (Process process : queue) {
      System.out.println(process);
    }
  }
}
