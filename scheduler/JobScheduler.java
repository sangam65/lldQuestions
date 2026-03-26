package scheduler;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.PriorityQueue;


public class JobScheduler {
    private final PriorityQueue<Job> jobList;

    public JobScheduler() {
        this.jobList = new PriorityQueue<>(new JobComparator());

    }

    public synchronized void addJob(Job a) throws RuntimeException {
        if (jobList.contains(a)) {
            throw new RuntimeException("Job exists");
        }

        jobList.add(a);
        
        // System.out.println(jobList.size());

    }

    public  void scheduleJob() {
        while (!jobList.isEmpty()) {
            synchronized(this){
                Job job = jobList.peek();
                if (job.getStartTime().isBefore(LocalDateTime.now())) {
                    // System.out.println("jobId " + job.getJobId());

                    jobList.poll();
                    job.jobExecutionProcess();
                } 
                
            }

        }
    }
}

class JobComparator implements Comparator<Job> {

    @Override
    public int compare(Job o1, Job o2) {
        if (o1.getStartTime().isBefore(o2.getStartTime())) {
            return -1;
        }
        else if (o1.getStartTime().isEqual(o2.getStartTime())) {
            return Integer.compare(o1.getJobId(), o2.getJobId());
        }
        else
        return 1;
    }

}
