package com.nhnacademy.edu.springframework.messagesender.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class LoggingAspect {

    @Around("execution(public * *.sendMessage(..))")
    public Object loggingExecutionTime(ProceedingJoinPoint pjp) {

        StopWatch stopWatch = new StopWatch();
        long time;

        stopWatch.start();

        Object rt = null;
        try {
            rt = pjp.proceed();
        } catch (Throwable e) {
            System.out.println("에러다!!!!!!!!!!!!");
        } finally {
            stopWatch.stop();
            time = stopWatch.getTotalTimeMillis();
            System.out.println("time: " + time + "ms");
        }

        return rt;
    }
}
