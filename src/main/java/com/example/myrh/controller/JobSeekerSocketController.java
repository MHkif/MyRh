package com.example.myrh.controller;

import com.example.myrh.service.JobApplicationChangesManager;
import com.example.myrh.service.JobSeekerSubscriber;
import org.springframework.stereotype.Controller;

@Controller
public class JobSeekerSocketController implements JobSeekerSubscriber {

    private final JobApplicationChangesManager jobApplicationChangesManager ;

    public JobSeekerSocketController(JobApplicationChangesManager jobApplicationChangesManager) {
        this.jobApplicationChangesManager = jobApplicationChangesManager;
        this.jobApplicationChangesManager.addJobApplication(this);
    }

    @Override
    public void update(Object object) {
        //TODO: ALL THE SOCKET AND SEND TO THE

    }
}
