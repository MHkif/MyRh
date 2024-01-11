package com.example.myrh.controller;

import com.example.myrh.dto.responses.JobApplicantRes;
import com.example.myrh.service.JobApplicationChangesManager;
import com.example.myrh.service.JobSeekerSubscriber;
import com.example.myrh.service.WebSocketService;
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
    private final WebSocketService webSocketService;


    public JobSeekerSocketController(JobApplicationChangesManager jobApplicationChangesManager, WebSocketService webSocketService) {
        this.jobApplicationChangesManager = jobApplicationChangesManager;
        this.webSocketService = webSocketService;
        this.jobApplicationChangesManager.addJobApplication(this);
    }



//    @MessageMapping("/queue/new-job-application/notification")
//    @SendToUser("/topic/job_seeker")
//    public String sendNotificationToJobApplicant() throws Exception{
//        //: ALL THE SOCKET AND SEND TO THE
//
////        if(jobApplicantRes.getJobSeeker().getId() != Integer.parseInt(id)){
////            throw new Exception("Job Seeker not found");
////        }
////        log.info("Job Seeker has been notified"+jobApplicantRes.toString());
////        messagingTemplate.convertAndSendToUser(id,"/topic/job_seeker/"+id,jobApplicantRes);
////        return jobApplicantRes;
//        return "Hell hello";
//    }

    @Override
    public void handleNotification(Object object) {
            JobApplicantRes jobApplicantRes = (JobApplicantRes) object;
            int id = jobApplicantRes.getJobSeeker().getId();
            log.info("Job Seeker is about to notify job applicant with id :  "+jobApplicantRes.getJobSeeker().getId());
            //each jobSeeker has a topic with his id
            //  /topic/job_seeker/1
            this.webSocketService.sendNotificationToJobApplicant("job_seeker/"+id,jobApplicantRes);
            log.info("Job Seeker has been notified"+jobApplicantRes.toString());


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
