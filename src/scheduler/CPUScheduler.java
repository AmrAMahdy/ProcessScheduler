package scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

import scheduler.queue.Queue;
import scheduler.queue.types.QueueType;
import scheduler.queue.types.RounRobinQueueType;

public class CPUScheduler {
  private boolean preemptive;
  private Queue queue;
  private ArrayList<Insertion> insertions;
  private ArrayList<Execution> executionList;
  private ArrayList<Process> processes;

  public CPUScheduler(QueueType queueType, boolean preemptive) {
    queue = new Queue(queueType);
    this.preemptive = preemptive;
    insertions = new ArrayList<>();
    executionList = new ArrayList<>();
    processes = new ArrayList<>();
  }

  public void makeInsertion(Process process, int timeOfInsertion) {
    insertions.add(new Insertion(process, timeOfInsertion));
  }

  public void startExecution() {
    Collections.sort(insertions);

    Process runningProcess = null;
    Execution execution = null;
    for (int t = 0; !insertions.isEmpty() || !queue.isEmpty() || runningProcess != null; t++) {
      System.out.println("<<<<  Time #" + t + "  >>>>");

      // Add processes
      while (!insertions.isEmpty() && t == insertions.get(0).getTimeOfInsertion()) {
        processes.add(insertions.get(0).getProcess());
        queue.add(insertions.get(0).getProcess());
        insertions.remove(0);
      }

      // Select Process
      if (!queue.isEmpty()) {
        if (runningProcess == null) {
          runningProcess = queue.getFirst();
          queue.remove();
          execution = new Execution(runningProcess, t, t);
        } else if (preemptive) {
          boolean switchProcess = false;

          if (queue.getQueueType() == QueueType.FCFS) {
            switchProcess = false;
          } else if (queue.getQueueType() == QueueType.SHORTEST_REMAINING_TIME) {
            switchProcess = (runningProcess.getRemainingTime() > queue.getFirst().getRemainingTime());
          } else if (queue.getQueueType() == QueueType.PRIORITY) {
            switchProcess = (runningProcess.getPriority() > queue.getFirst().getPriority());
          } else if (queue.getQueueType() instanceof RounRobinQueueType) {
            switchProcess = (execution.getEndTime() - execution.getStartTime()) >= ((RounRobinQueueType) queue.getQueueType()).getPeriod();
          }

          if (switchProcess) {
            executionList.add(execution);
            queue.add(runningProcess);
            runningProcess = queue.getFirst();
            queue.remove();
            execution = new Execution(runningProcess, t, t);
          }
        }
      }

      System.out.println(" -> " + runningProcess);
      printQueue();

      // Execute unit time of a process
      if (runningProcess != null) {
        runningProcess.decrementRemiainingTime();
        execution.setEndTime(execution.getEndTime() + 1);

        // Remove process from queue and add an execution entry
        if (runningProcess.isFinished()) {
          executionList.add(execution);
          runningProcess = null;
          execution = null;
        }
      }
    }
    
    (new Viewer(processes, executionList)).view();
  }

  public void printQueue() {
    for (Process process : queue) {
      System.out.println("    " + process);
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