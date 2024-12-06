package com.kinjo1.FirstJob.Job.Implement;

import com.kinjo1.FirstJob.Job.Job;
import com.kinjo1.FirstJob.Job.JobRepository;
import com.kinjo1.FirstJob.Job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    //private List<Job> jobs = new ArrayList<>(); // Comments because it need to be change for jpa configuration

    JobRepository jobRepository;


    // To generate  increment id//
    // private Long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        //return jobs;
        return jobRepository.findAll();
    }

    // to create job
    @Override
    public void createJob(Job job) {
       // job.setId(nextId++); // parse the variable here for generating id increment
        //jobs.add(job);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id){

        return jobRepository.findById(id).orElse(null);

//        for(Job job: jobs){
//            if(job.getId().equals(id)){
//                return job;
//            }
//
//        }
//        return null;
    }

    // To delete
    @Override
    public boolean deletJobById(Long id) {

        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

//        Iterator<Job> iterator = jobs.iterator();
//        while (iterator.hasNext()){
//            Job job = iterator.next();
//            if(job.getId().equals(id)){
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
    }

    // to update
    @Override
    public boolean updateJob(Long id, Job updateJob) {

        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setTitle(updateJob.getTitle());
            job.setDescription(updateJob.getDescription());
            job.setMinSalary(updateJob.getMinSalary());
            job.setMaxSalary(updateJob.getMaxSalary());
            job.setLocation(updateJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;


//        for(Job job: jobs){
//            if(job.getId().equals(id)){
//                job.setTitle(updateJob.getTitle());
//                job.setDescription(updateJob.getDescription());
//                job.setMinSalary(updateJob.getMinSalary());
//                job.setMaxSalary(updateJob.getMaxSalary());
//                job.setLocation(updateJob.getLocation());
//                return true;
//            }
//        }
//        return false;
    }


}
