package scheduler.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import scheduler.Process;
import scheduler.queue.Queue.QueueNode;
import scheduler.queue.types.QueueType;

public class Queue implements Iterable<Process> {
  private QueueNode head;
  private QueueType queueType;

  public Queue(QueueType queueType) {
    head = null;
    this.queueType = queueType;
  }

  public void remove() {
    if (!isEmpty())
      head = head.getNext();
  }

  public Process getFirst() {
    return head.getProcess();
  }

  public boolean isEmpty() {
    return head == null;
  }

  public void add(Process process) {
    if (head == null)
      head = new QueueNode(process);
    else {
      QueueNode response = queueType.add(head, process);
      if (response != null) {
        head = response;
      }
    }
  }

  public void setQueueType(QueueType queueType) {
    this.queueType = queueType;
  }

  @Override
  public Iterator<Process> iterator() {
    return new Iterator<Process>() {
      QueueNode p = Queue.this.head;

      @Override
      public void remove() {
        p = p.getNext();
        Queue.this.remove();
      }

      @Override
      public Process next() {
        Process r = p.getProcess();
        p = p.getNext();
        return r;
      }

      @Override
      public boolean hasNext() {
        return p != null;
      }
    };
  }

  public static class QueueNode {
    private Process process;
    private QueueNode next;

    public QueueNode(Process process) {
      this.process = process;
    }

    public Process getProcess() {
      return process;
    }

    public void setProcess(Process process) {
      this.process = process;
    }

    public QueueNode getNext() {
      return next;
    }

    public void setNext(QueueNode next) {
      this.next = next;
    }

  }
}