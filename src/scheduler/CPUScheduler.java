package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import scheduler.queue.Queue;
import scheduler.queue.types.QueueType;

public class CPUScheduler {
  private Queue queue;
  private ArrayList<Insertion> insertions;
  private ArrayList<Execution> executionList;

  public CPUScheduler(QueueType queueType) {
    queue = new Queue(queueType);
    insertions = new ArrayList<>();
    executionList = new ArrayList<>();
  }

  public void makeInsertion(Process process, int timeOfInsertion) {
    insertions.add(new Insertion(process, timeOfInsertion));
  }

  public void startExecution() {
    Collections.sort(insertions);

    Execution execution = null;
    for (int t = 0; !insertions.isEmpty() || !queue.isEmpty(); t++) {
      System.out.println("<<<<  Time #" + t + "  >>>>");

      // Add processes
      while (!insertions.isEmpty() && t == insertions.get(0).getTimeOfInsertion()) {
        queue.add(insertions.get(0).getProcess());
        insertions.remove(0);
      }

      printQueue();

      // Execute unit time of a process
      if (!queue.isEmpty()) {
        if (execution == null) {
          execution = new Execution(queue.getFirst(), t, t);
        }

        if (execution != null && queue.getFirst() != execution.getProcess()) {
          executionList.add(execution);
          execution = new Execution(queue.getFirst(), t, t);
        }

        queue.getFirst().decrementRemiainingTime();
        execution.setEndTime(execution.getEndTime() + 1);

        // Remove process from queue and add an execution entry
        if (queue.getFirst().isFinished()) {
          queue.remove();
          executionList.add(execution);
          execution = null;
        }
      }
    }

    printTimeline();
  }

  public void printQueue() {
    boolean first = true;

    for (Process process : queue) {
      System.out.println((first ? " -> " : "    ") + process);
      first = false;
    }
  }

  public void printTimeline() {
    System.out.println("\n\nTimeline ([From, To]: Process Name)");
    System.out.println("==================");

    System.out.printf("\n");

    for (Execution execution : executionList) {
      System.out.printf("[%d, %d]: %s\n", execution.getStartTime(), execution.getEndTime(), execution.getProcess().getProcessName());
    }
  }
}

class Insertion implements Comparable<Insertion> {
  private Process process;
  private int timeOfInsertion;

  public Insertion(Process process, int timeOfInsertion) {
    this.process = process;
    this.timeOfInsertion = timeOfInsertion;
  }

  @Override
  public int compareTo(Insertion o) {
    return timeOfInsertion - o.timeOfInsertion;
  }

  public Process getProcess() {
    return process;
  }

  public void setProcess(Process process) {
    this.process = process;
  }

  public int getTimeOfInsertion() {
    return timeOfInsertion;
  }

  public void setTimeOfInsertion(int timeOfInsertion) {
    this.timeOfInsertion = timeOfInsertion;
  }

}

class Execution implements Comparable<Execution> {
  private Process process;
  private int startTime;
  private int endTime;

  public Execution(Process process, int startTime, int endTime) {
    this.process = process;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public int compareTo(Execution o) {
    return startTime - o.startTime;
  }

  public Process getProcess() {
    return process;
  }

  public void setProcess(Process process) {
    this.process = process;
  }

  public int getStartTime() {
    return startTime;
  }

  public void setStartTime(int startTime) {
    this.startTime = startTime;
  }

  public int getEndTime() {
    return endTime;
  }

  public void setEndTime(int endTime) {
    this.endTime = endTime;
  }

}