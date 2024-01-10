package com.example.myrh.controller;

import com.example.myrh.dto.responses.JobApplicantRes;
import com.example.myrh.service.JobApplicationChangesManager;
import com.example.myrh.service.JobSeekerSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class JobSeekerSocketController implements JobSeekerSubscriber {

    private final JobApplicationChangesManager jobApplicationChangesManager ;
    private final SimpMessagingTemplate simpMessagingTemplate;

//    private SimpMessagingTemplate messagingTemplate;


    public JobSeekerSocketController(JobApplicationChangesManager jobApplicationChangesManager, SimpMessagingTemplate simpMessagingTemplate) {
        this.jobApplicationChangesManager = jobApplicationChangesManager;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.jobApplicationChangesManager.addJobApplication(this);
    }


    //TODO:SEND TO THE TOPIC BUT I NEED TO CREATE UNIQUE TOPIC FOR EACH USER

    //MESSAGE SENT TO THE /job_seeker/{id}  and processed by the /app/job_seeker/{id}
    //picked by the /topic/job_seeker/{id}
    // /app/private/notification/ and send to the /topic/job_seeker
//    @MessageMapping("/private/notification/")
//    @SendToUser("/topic/job_seeker")
//    public JobApplicantRes sendNotificationToJobApplicant(JobApplicantRes jobApplicantRes, @DestinationVariable String id) throws Exception{
//        //TODO: ALL THE SOCKET AND SEND TO THE
//
//        if(jobApplicantRes.getJobSeeker().getId() != Integer.parseInt(id)){
//            throw new Exception("Job Seeker not found");
//        }
//        log.info("Job Seeker has been notified"+jobApplicantRes.toString());
////        messagingTemplate.convertAndSendToUser(id,"/topic/job_seeker/"+id,jobApplicantRes);
//        return jobApplicantRes;
//    }

    @Override
    public void handleNotification(Object object) {
//        log.info("Job Seeker has been notified"+object.toString());
        //: ALL THE SOCKET AND SEND TO THE
            JobApplicantRes jobApplicantRes = (JobApplicantRes) object;
            int id = jobApplicantRes.getJobSeeker().getId();
            log.info("Job Seeker is about to notify job applicant with id :  "+jobApplicantRes.getJobSeeker().getId());
            this.simpMessagingTemplate.convertAndSend("/queue/new-job-application/notification",jobApplicantRes);





//        if(object instanceof JobApplicantRes){
//            JobApplicantRes jobApplicantRes = (JobApplicantRes) object;
//            int id = jobApplicantRes.getJobSeeker().getId();
//            try{
//                sendNotificationToJobApplicant(jobApplicantRes,String.valueOf(id));
//            }catch (Exception e){
//                throw new RuntimeException(e.getMessage());
//            }
//        }
    }
}
