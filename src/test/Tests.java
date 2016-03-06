package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import scheduler.queue.Queue;
import scheduler.queue.types.QueueType;
import scheduler.Process;

public class Tests {
  static Queue queue;

  @BeforeClass
  public static void beforeClass() {
    queue = new Queue(QueueType.FCFS);
  }

  @Test
  public void testAdd() {
    queue.add(new Process(1));
    assertNotNull(queue.getFirst());
    assertEquals(1, queue.getFirst().getPid());
    queue.remove();
    assertTrue(queue.isEmpty());
  }

  @Test
  public void testAdd10() {
    for (int i = 1; i <= 10; i++) {
      queue.add(new Process(i));
    }

    assertNotNull(queue.getFirst());
    assertEquals(1, queue.getFirst().getPid());

    for (int i = 1; i <= 10; i++) {
      assertNotNull(queue.getFirst());
      assertEquals(i, queue.getFirst().getPid());
      queue.remove();
    }

    assertTrue(queue.isEmpty());
  }

}
