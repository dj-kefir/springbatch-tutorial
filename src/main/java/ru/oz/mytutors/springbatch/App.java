package ru.oz.mytutors.springbatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ozol on 15.03.2016.
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        String[] springConfig =
                {
                        "batch-context.xml"
                };

        ApplicationContext context =
                new ClassPathXmlApplicationContext(springConfig);

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("priceCsvToXmlConvertionJob");

        try {

            JobExecution execution = jobLauncher.run(job, new JobParameters());
            System.out.println("Exit Status : " + execution.getStatus());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        log.info("Done");
    }
}
