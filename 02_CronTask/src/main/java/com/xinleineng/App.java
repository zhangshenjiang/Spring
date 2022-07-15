package com.xinleineng;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("spring.xml");
        CronTask cronTask= (CronTask) applicationContext.getBean("cronTask");
//        cronTask.job1();

    }
}
