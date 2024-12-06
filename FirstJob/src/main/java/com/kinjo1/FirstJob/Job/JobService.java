package com.kinjo1.FirstJob.Job;

import java.util.List;

public interface JobService {

    List<Job> findAll();

    //to create job
    void createJob(Job job);

    Job getJobById(Long id);

    // to delete
     boolean deletJobById(Long id);

     //update
    boolean updateJob(Long id, Job updateJob);
}
