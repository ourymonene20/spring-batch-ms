package sn.isi.batchms.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
public class AppUserController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    public AppUserController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @RequestMapping("/test")
    public BatchStatus load() throws Exception{
        Map<String, JobParameter> params = new HashMap<>();
        params.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(params);
        JobExecution jobExecution = jobLauncher.run(job,jobParameters);
        while (jobExecution.isRunning()){
            System.out.println("############");
        }

        return jobExecution.getStatus();
    }

}
