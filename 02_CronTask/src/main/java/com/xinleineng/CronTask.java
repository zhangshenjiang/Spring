package com.xinleineng;

import org.springframework.stereotype.Component;

@Component
public class CronTask {

    public void job1(){
        System.out.println("Job1 run..");
    }
    public void job2(){
        System.out.println("Job2 run..");
    }
}

