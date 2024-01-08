package com.example.myrh;


import com.example.myrh.dto.requests.*;
import com.example.myrh.enums.JobApplicationStatus;
import com.example.myrh.enums.StudyLevel;
import com.example.myrh.enums.UserStatus;
import com.example.myrh.model.*;
import com.example.myrh.repository.JobApplicantRepo;
import com.example.myrh.repository.JobSeekerRepo;
import com.example.myrh.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final JobApplicantRepo jobApplicantRepo;

    public LoadDatabase(JobApplicantRepo jobApplicantRepo) {
        this.jobApplicantRepo = jobApplicantRepo;
    }


    @Bean
    CommandLineRunner initDatabase(
            ICompanyService companyService,
            IAgentService agentService,
            IActivityAreaService profileService,
            ICityService cityService,
            IOfferService offerService,
            JobSeekerRepo jobSeekerRepo,
            IJobSeekerService jobSeekerService,
            IJobApplicantService jobApplicantService) {

        return args -> {

            saveJobSeeker(jobSeekerRepo);

            CompanyReq c1 = new CompanyReq();
            c1.setName("Sofrecom");
            c1.setEmail("sofrecom@orange.com");
            c1.setPassword("12345678");
            c1.setImage("sofrecom.png");

            AgentReq agent = new AgentReq();
            agent.setFirst_name("Abdelmalek");
            agent.setLast_name("Achkif");
            agent.setEmail("agent@gmail.com");
            agent.setPassword("aqwzsxedc");
            agent.setCompany(Company.builder().id(1).build());



            CityReq city = new CityReq();
            city.setName("Casablanca");

            CityReq city2 = new CityReq();
            city2.setName("Nador");

            CityReq city3 = new CityReq();
            city3.setName("Rabat");

            ActivityAreaReq profile = new ActivityAreaReq();
            profile.setDescription("Information Technology");

            OfferReq offer = new OfferReq();
            offer.setTitle("Developpeur / Developpeuse Full stack");
            offer.setDescription("EYSI, entreprise de développement informatique, cherche un stagiaire à" +
                    "partir de BAC +2 pour une durée variant entre 2 à 6 mois.");
            offer.setCompany(Company.builder().id(1).build());
            offer.setCity(City.builder().id(1).build());
            offer.setProfile(ActivityArea.builder().id(1).build());
            offer.setLevel(StudyLevel.BacPlus2);
            offer.setSalary(12000);

            JobSeekerReq jobSeeker = new JobSeekerReq();
            jobSeeker.setFirst_name("El Mehdi");
            jobSeeker.setLast_name("El Hajoujy");
            jobSeeker.setEmail("elmehdi@myrh.com");

            jobSeeker.setPassword("testtest");

/*
            JobApplicantId jobApplicantId = new JobApplicantId();
            jobApplicantId.setJobSeeker_id(10);
            jobApplicantId.setOffer_id(1);

            JobApplicantReq jobApplicant = new JobApplicantReq();
            jobApplicant.setId(jobApplicantId);
            jobApplicant.setJobSeeker(jobSeeker);
            //jobApplicant.setIsViewed(true);

 */


            log.info("Preloading Company 1: " + companyService.create(c1).toString());
            log.info("Preloading Agent 1 : " + agentService.create(agent).toString());
            log.info("Preloading City 1 : " + cityService.create(city).toString());
            log.info("Preloading City 2 : " + cityService.create(city2).toString());
            log.info("Preloading City 3 : " + cityService.create(city3).toString());

            log.info("Preloading Profile 1 : " + profileService.create(profile).toString());
            log.info("Preloading Offer 1 : " + offerService.create(offer).toString());
            log.info("Preloading JobSeeker 1 : " + jobSeekerService.create(jobSeeker).toString());
            //log.info("Preloading Job Applicant 1 : " + jobApplicantService.create(jobApplicant).toString());
            saveFakeJobApplication();

        };
    }

    private void saveJobSeeker(JobSeekerRepo jobSeekerRepo) {
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setFirst_name("El Mehdi");
        jobSeeker.setLast_name("El Hajoujy");
        jobSeeker.setEmail("elmahdi311@gmail.com");
        jobSeeker.setPassword("testtest");
        jobSeeker.setStatus(UserStatus.ONLINE);
        jobSeeker = jobSeekerRepo.save(jobSeeker);
        log.info("Preloading JobSeeker  : " + jobSeeker.getId() + " " + jobSeeker.getFirst_name() + " " + jobSeeker.getLast_name());

    }

    private void saveFakeJobApplication(){
        JobApplicantId jobApplicantId = new JobApplicantId();
        jobApplicantId.setJobSeeker_id(1);
        jobApplicantId.setOffer_id(1);
        JobApplicant jobApplicant = new JobApplicant();
        jobApplicant.setId(jobApplicantId);
        jobApplicant.setResume("path/to/resume");
        jobApplicant.setStatus(JobApplicationStatus.ACCEPTED);
        jobApplicant.setIsViewed(true);



        //jobApplicant.setJobSeeker(jobSeeker);
        //jobApplicant.setIsViewed(true);
        this.jobApplicantRepo.save(jobApplicant);
        jobApplicant.getId().setJobSeeker_id(2);
        this.jobApplicantRepo.save(jobApplicant);


    }
}