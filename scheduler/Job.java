package scheduler;


// import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Job implements Delayed{
    private final long startTime;
    private final Integer jobId;

    public long getStartTime() {
        return startTime;
    }
    public Integer getJobId() {
        return jobId;
    }
    public Job(LocalDateTime time, Integer jobId ) {
        this.startTime =time.atZone(ZoneId.systemDefault()) 
                 .toInstant()
                 .toEpochMilli();
        this.jobId = jobId;
    }
    public void jobExecutionProcess(){
        System.out.print ("job scheduled ");
        System.out.println(this.jobId+" "+this.startTime);
    }
    @Override
    public int compareTo(Delayed delayed) {
        return Long.compare(startTime, ((Job)delayed).getStartTime());
    }
    @Override
    public long getDelay(TimeUnit unit) {
        long diff=startTime-System.currentTimeMillis();
       return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

}

