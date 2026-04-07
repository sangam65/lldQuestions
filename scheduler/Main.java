package scheduler;

import java.time.LocalDateTime;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String []args){
        JobScheduler jobScheduler=new JobScheduler();
        jobScheduler.addJob(new Job(LocalDateTime.of(2026, 3, 26, 17, 29), 1));
        jobScheduler.addJob(new Job(LocalDateTime.of(2026, 3, 26, 17, 29), 3));
        jobScheduler.addJob(new Job(LocalDateTime.of(2026, 3, 26, 17, 48), 2));
        ScheduledExecutorService executorService=new ScheduledThreadPoolExecutor(1);
        try{
             executorService.submit(()->{
             jobScheduler.scheduleJob();
        });
         
        }
        finally{
            //  executorService.awaitTermination(2000, TimeUnit.MILLISECONDS);
        }
       
        jobScheduler.addJob(new Job(LocalDateTime.of(2026, 3, 26,20, 38), 4));
        // executorService.s
        



    

    }
}
