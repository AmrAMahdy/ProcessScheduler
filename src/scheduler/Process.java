package scheduler;

public class Process {
  private String processName;
  private int pid;
  private int time;
  private int priority;

  public Process(String processName, int pid, int time, int priority) {
    this.processName = processName;
    this.pid = pid;
    this.time = time;
    this.priority = priority;
  }

  public Process(int pid) {
    this(null, pid, 0, -1);
  }

  public void setTime(int time) {
    this.time = time;
  }

  public int getTime() {
    return time;
  }

  public String getProcessName() {
    return processName;
  }

  public void setProcessName(String processName) {
    this.processName = processName;
  }

  public int getPid() {
    return pid;
  }

  public void setPid(int pid) {
    this.pid = pid;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  @Override
  public String toString() {
    return String.format("Process: { Name: %s, PID: %d }", processName, pid);
  }
}
