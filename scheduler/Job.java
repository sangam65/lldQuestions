package scheduler;

import java.time.LocalDateTime;

public class Job{
    private final LocalDateTime startTime;
    private final Integer jobId;

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public Integer getJobId() {
        return jobId;
    }
    public Job(LocalDateTime startTime, Integer jobId ) {
        this.startTime = startTime;
        this.jobId = jobId;
    }
    public void jobExecutionProcess(){
        System.out.print ("job scheduled ");
        System.out.println(this.jobId+" "+this.startTime);
    }

}

