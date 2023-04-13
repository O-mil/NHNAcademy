package com.nhnacademy.springcore.annotation;

import com.nhnacademy.springcore.User;
import com.nhnacademy.springcore.sender.DoorayMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(TimeAnnotation) && args(user, ..)")
    public Object loggingExecutionTime(ProceedingJoinPoint pjp, User user) throws Throwable {

        Logger logger = LoggerFactory.getLogger(DoorayMessageSender.class);

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Object rt = null;

        try {
            System.out.println("timeAnnotation 실행");
            rt = pjp.proceed();
            System.out.println("timeAnnotation 종료");
        } catch (Throwable e) {
            System.out.println("에러다!!!!!!!!");
            throw e;
        } finally {
            stopWatch.stop();
            System.out.println();
            logger.info("{}ms", stopWatch.getTotalTimeMillis());
        }
        return rt;
    }
}
