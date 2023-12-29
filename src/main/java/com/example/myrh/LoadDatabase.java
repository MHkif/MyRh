package com.example.myrh;


import com.example.myrh.dto.requests.*;
import com.example.myrh.enums.StudyLevel;
import com.example.myrh.model.*;
import com.example.myrh.repository.CompanyRepo;
import com.example.myrh.service.*;
import com.example.myrh.service.impl.AgentServiceImpl;
import com.example.myrh.service.impl.CompanyServiceImpl;
import com.example.myrh.service.impl.RecruiterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);


    @Bean
    CommandLineRunner initDatabase(
            ICompanyService companyService ,
            IAgentService agentService,
            IRecruiterService recruiterService,
            IActivityAreaService profileService,
            ICityService cityService,
            IOfferService offerService,
            IJobSeekerService jobSeekerService,
            IJobApplicantService jobApplicantService) {

        return args -> {

            CompanyReq c1 = new CompanyReq();
            c1.setName("Sofrecom");
            c1.setEmail("sofrecom@orange.com");
            c1.setPassword("12345678");
            c1.setImage("sofrecom.png");

            AgentReq agent = new AgentReq();
            agent.setFirst_name("Mehdi");
            agent.setLast_name("El Hajouji");
            agent.setEmail("agent@gmail.com");
            agent.setPassword("password");
            agent.setCompany(Company.builder().id(1).build());

            RecruiterReq recruiter = new RecruiterReq();
            recruiter.setFirst_name("Recruiter");
            recruiter.setLast_name("MyRH");
            recruiter.setEmail("recruiter@myrh.com");
            recruiter.setPassword("testtest");
            recruiter.setCompany(Company.builder().id(1).build());

            CityReq city = new CityReq();
            city.setName("Casablanca");

            ActivityAreaReq profile = new ActivityAreaReq();
            profile.setDescription("Information Technology");

            OfferReq offer = new OfferReq();
            offer.setTitle("Developer Java/Angular");
            offer.setDescription("Developer Java/Angular");
            offer.setRecruiter(Recruiter.builder().id(1).build());
            offer.setCity(City.builder().id(1).build());
            offer.setProfile(ActivityArea.builder().id(1).build());
            offer.setLevel(StudyLevel.BacPlus2);
            offer.setSalary(12000);

            JobSeekerReq jobSeeker = new JobSeekerReq();
            jobSeeker.setFirst_name("AbdelMalek");
            jobSeeker.setLast_name("Achkif");
            jobSeeker.setEmail("achkif@myrh.com");
            jobSeeker.setPassword("testtest");


            JobApplicantId jobApplicantId = new JobApplicantId();
            jobApplicantId.setJobSeeker_id(1);
            jobApplicantId.setOffer_id(1);

            JobApplicantReq jobApplicant = new JobApplicantReq();
            jobApplicant.setId(jobApplicantId);
            //jobApplicant.setIsViewed(true);




            log.info("Preloading Company 1: " + companyService.create(c1).toString());
            log.info("Preloading Agent 1 : " + agentService.create(agent).toString());
            log.info("Preloading Recruiter 1 : " + recruiterService.create(recruiter).toString());
            log.info("Preloading City 1 : " + cityService.create(city).toString());
            log.info("Preloading Profile 1 : " + profileService.create(profile).toString());
            log.info("Preloading Offer 1 : " + offerService.create(offer).toString());
            log.info("Preloading JobSeeker 1 : " + jobSeekerService.create(jobSeeker).toString());
            log.info("Preloading Job Applicant 1 : " + jobApplicantService.create(jobApplicant).toString());







        };
    }
}