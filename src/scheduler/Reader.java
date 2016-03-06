package scheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import scheduler.queue.types.QueueType;
import scheduler.queue.types.RounRobinQueueType;

public class Reader {
  File file;

  public Reader(File file) {
    this.file = file;
  }

  public void read() {
    try {
      Scanner in = new Scanner(file);

      CPUScheduler cpu = null;
      int pid = 0;

      while (in.hasNextLine()) {
        String line = in.nextLine();
        if (line.startsWith("#"))
          continue;

        String[] segments = line.split(" ");

        if (segments[0].equals("QueueType")) {
          boolean preemptive = segments[2].equals("preemptive");
          if (segments[1].equals("FCFS")) {
            cpu = new CPUScheduler(QueueType.FCFS, preemptive);
          } else if (segments[1].equals("SJF")) {
            cpu = new CPUScheduler(QueueType.SHORTEST_REMAINING_TIME, preemptive);
          } else if (segments[1].equals("Priority")) {
            cpu = new CPUScheduler(QueueType.PRIORITY, preemptive);
          } else if (segments[1].startsWith("RoundRobin")) {
            int period = Integer.valueOf(segments[1].split("\\:")[1]);
            cpu = new CPUScheduler(new RounRobinQueueType(period), preemptive);
          }
        } else if (segments[0].equals("Insert")) {
          int insertionTime, duration, priority = -1;
          String processName;
          insertionTime = Integer.valueOf(segments[1]);
          processName = segments[2];
          duration = Integer.valueOf(segments[3]);
          if (segments.length > 4)
            priority = Integer.valueOf(segments[4]);

          cpu.makeInsertion(new Process(processName, pid, duration, priority), insertionTime);
          pid++;
        }
      }

      cpu.printQueue();
      cpu.startExecution();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    // cpu.makeInsertion(new Process("P1", 0, 5, 5), 0);
    // cpu.makeInsertion(new Process("P2", 1, 3, 3), 2);
    // cpu.makeInsertion(new Process("P3", 2, 6, 2), 4);
    // cpu.makeInsertion(new Process("P4", 3, 4, 1), 5);
    // cpu.makeInsertion(new Process("P5", 4, 3, 1), 7);
    // cpu.makeInsertion(new Process("P6", 5, 4, 1), 8);
    // cpu.makeInsertion(new Process("P7", 6, 3, 1), 11);

  }

}
