package scheduler;

public class Process {
  private String processName;
  private int pid;
  private int duration;
  private int remainingTime;
  private int priority;

  public Process(String processName, int pid, int time, int priority) {
    this.processName = processName;
    this.pid = pid;
    this.duration = time;
    this.priority = priority;
    remainingTime = duration;
  }

  public Process(int pid) {
    this(null, pid, 0, -1);
  }

  public void decrementRemiainingTime() {
    if (!isFinished())
      remainingTime--;
  }

  public int getRemainingTime() {
    return remainingTime;
  }

  public boolean isFinished() {
    return remainingTime == 0;
  }

  public void setDuration(int time) {
    this.duration = time;
  }

  public int getduration() {
    return duration;
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
    return String.format("Process: { name: %s, PID: %d, duration: %d, remaining_time: %d, priority: %d }", processName, pid, duration, remainingTime,priority);
  }
}
