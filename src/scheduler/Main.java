package scheduler;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import scheduler.queue.types.QueueType;
import test.Tests;

public class Main {
  public static void main(String[] args) {
    runTests();
    
    CPUScheduler cpu= new CPUScheduler(QueueType.FCFS);
    
    for (int i = 0; i < 10; i++) {
      cpu.addProcess(new Process(i));
    }
    
    cpu.printQueue();
  }

  private static void runTests() {
    Result result = JUnitCore.runClasses(Tests.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }    
    System.out.println(result.wasSuccessful());
  }
}
