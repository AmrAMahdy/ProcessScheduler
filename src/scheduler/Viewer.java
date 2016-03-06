package scheduler;

import java.util.ArrayList;

public class Viewer {
  private ArrayList<Process> processes;
  private ArrayList<Execution> executionList;

  public Viewer(ArrayList<Process> processes, ArrayList<Execution> executionlist) {
    this.processes = processes;
    this.executionList = executionlist;
  }

  public void view() {
    printTimeline();
    printTimeline2();
    printTimeline3();
  }

  public void view(ArrayList<Process> processes, ArrayList<Execution> executionlist) {
    this.processes = processes;
    this.executionList = executionlist;

    view();
  }

  public void printTimeline() {
    System.out.println("\n\nTimeline ([From, To]: Process Name)");
    System.out.println("==================");

    System.out.printf("\n");

    for (Execution execution : executionList) {
      System.out.printf("[%d, %d]: %s\n", execution.getStartTime(), execution.getEndTime(), execution.getProcess().getProcessName());
    }
  }

  public void printTimeline2() {
    System.out.println("\n\nTimeline ([From]-----Process Name-----[To])");
    System.out.println("==================");
    ArrayList<ArrayList<Character>> processingTimes = new ArrayList<>();
    System.out.print("[0");

    int endTime = 0;
    for (Execution execution : executionList) {
      int time = execution.getEndTime() - execution.getStartTime();
      int space = time * 4;
      int leftspace = (space - execution.getProcess().getProcessName().length() + 1) / 2;

      System.out.print(']');
      for (int i = 0; i < leftspace; i++) {
        System.out.print('-');
      }
      System.out.print(execution.getProcess().getProcessName());
      for (int i = 0; i < space - leftspace - execution.getProcess().getProcessName().length(); i++) {
        System.out.print('-');
      }
      System.out.print("[" + execution.getEndTime());

    }
    System.out.println("]");
  }

  public void printTimeline3() {
    System.out.println("\n\nTimeline (Process Name: [------===Running===-------])");
    System.out.println("==================");
    ArrayList<ArrayList<Character>> processingTimes = new ArrayList<>();

    int scale = 3;
    int endTime = 0;
    for (Execution execution : executionList) {
      endTime = Math.max(endTime, execution.getEndTime());
    }

    for (int i = 0; i < processes.size(); i++) {
      ArrayList<Character> line = new ArrayList<>();
      for (int j = 0; j < endTime; j++) {
        line.add('-');
      }
      processingTimes.add(line);
    }

    for (Execution execution : executionList) {
      int i = execution.getProcess().getPid();
      for (int j = execution.getStartTime(); j < execution.getEndTime(); j++) {
        processingTimes.get(i).set(j, '=');
      }
    }

    for (int i = 0; i < processes.size(); i++) {
      String name = processes.get(i).getProcessName();

      System.out.print(name);
      for (int j = 0; j < 5 - name.length(); j++) {
        System.out.print(' ');
      }
      System.out.print(" [");
      for (int j = 0; j < endTime; j++) {
        for (int j2 = 0; j2 < scale; j2++) {
          System.out.print(processingTimes.get(i).get(j));
        }
      }
      System.out.println("]");
    }
  }
}
