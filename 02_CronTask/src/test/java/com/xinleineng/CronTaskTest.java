package com.xinleineng;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
//@SpringJUnitConfig(locations="classpath:applicationContext.xml")

@DisplayName("测试CronTask")
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:spring.xml")
//@SpringJUnitConfig(locations="classpath:spring.xml")
public class CronTaskTest {
    @Autowired
    CronTask cronTask;

    @DisplayName("测试XML格式....")
    @Test
    @RepeatedTest(3)
    void testXmlSchema(){
        cronTask.job1();
    }
}
