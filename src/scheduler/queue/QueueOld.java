package scheduler.queue;

import java.util.Iterator;
import java.util.LinkedList;

import scheduler.Process;
import scheduler.queue.types.QueueType;

public class QueueOld implements Iterable<Process> {
  // private LinkedList<Process> list;
  // private QueueType queueType;
  //
  // public Queue(QueueType queueType) {
  // list = new LinkedList<>();
  // this.queueType = queueType;
  // }
  //
  // public void remove() {
  // list.removeFirst();
  // }
  //
  // public Process getFirst() {
  // return list.getFirst();
  // }
  //
  // public boolean isEmpty() {
  // return list.isEmpty();
  // }
  //
  // public void add(Process process) {
  // queueType.add(list, process);
  // }
  //
  // public void setQueueType(QueueType queueType) {
  // this.queueType = queueType;
  // }
  //
  // @Override
  public Iterator<Process> iterator() {
    return null;
    // return list.iterator();
  }
}
