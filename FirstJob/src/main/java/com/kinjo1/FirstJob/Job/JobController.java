package com.kinjo1.FirstJob.Job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")   //     to map everything whenever /jobs is called
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
//    public List<Job> findAll(){
//        return jobService.findAll();
//    }
    //       OR
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    //to create jobs
    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.POST)
//    public String createJob(@RequestBody Job job){
//        jobService.createJob(job);
//        return "Job added successfully";
//    }
    //                 OR
    @PostMapping
    public ResponseEntity <String>createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>( "Job added successfully",HttpStatus.OK);
    }


    // to delete
    @DeleteMapping("/{id}")    //OR
    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deletJobById(id);
        if(deleted){
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // to get job by id
    @GetMapping("/{id}")    // OR
    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)

//    public Job getJobById(@PathVariable Long id){
//        Job job = jobService.getJobById(id);
//        if(job != null){
//            return job;
//        }else{
//            return new Job(1L, "TestJob", "TestJob",200.0,300.0,"no where");
//        }
//    }
//                        OR

    public ResponseEntity<Job>getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if(job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //      For updating job
    @PutMapping("/{id}")  // OR
    //@RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job updateJob){
        boolean updated = jobService.updateJob(id, updateJob);
        if(updated){
            return new ResponseEntity<>("Update successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Can't update", HttpStatus.NOT_FOUND);
        }
    }
}
