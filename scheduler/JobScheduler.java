package scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.DelayQueue;

public class JobScheduler {
    private final DelayQueue<Job> jobList;
    private Map<Integer, Job> jobs;

    public JobScheduler() {
        this.jobList = new DelayQueue<>();
        this.jobs = new HashMap<>();

    }

    public synchronized void addJob(Job job) throws RuntimeException {
        if (jobs.get(job.getJobId()) != null) {
            throw new RuntimeException("Job exists");
        }

        jobList.add(job);
        jobs.put(job.getJobId(), job);

        // System.out.println(jobList.size());

    }

    public synchronized void scheduleJob() {
        while (!jobList.isEmpty()) {
            try{
                Job job = jobList.take();
            jobs.remove(job.getJobId());
            job.jobExecutionProcess();
            }
            catch(InterruptedException e){
                System.err.println("error "+e.getMessage());
            }
            

        }
    }
}


