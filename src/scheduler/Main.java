package scheduler;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import scheduler.queue.types.QueueType;
import test.Tests;

public class Main {
  public static void main(String[] args) {
    runTests();

    CPUScheduler cpu = new CPUScheduler(QueueType.FCFS);

    cpu.makeInsertion(new Process("P1", 1, 5, 1), 0);
    cpu.makeInsertion(new Process("P2", 2, 3, 1), 2);
    cpu.makeInsertion(new Process("P3", 3, 6, 1), 4);
    cpu.makeInsertion(new Process("P4", 4, 4, 1), 5);
    cpu.makeInsertion(new Process("P5", 5, 3, 1), 7);

    cpu.printQueue();
    cpu.startExecution();
  }

  private static void runTests() {
    Result result = JUnitCore.runClasses(Tests.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    System.out.println(result.wasSuccessful());
  }
}
