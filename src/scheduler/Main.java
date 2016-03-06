package scheduler;

import java.io.File;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import scheduler.queue.Queue;
import scheduler.queue.types.QueueType;
import scheduler.queue.types.RounRobinQueueType;
import test.Tests;

public class Main {
  public static void main(String[] args) {
    runTests();
    
    Reader reader = new Reader(new File("input.txt"));
reader.read();
  }

  private static void runTests() {
    Result result = JUnitCore.runClasses(Tests.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    System.out.println(result.wasSuccessful());
  }
}
