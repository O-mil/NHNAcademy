package com.nhnacademy.edu.springframework.messagesender.aop;

import com.nhnacademy.edu.springframework.messagesender.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class LoggingAspect {

    @Around("execution(public * *.sendMessage(..)) && args(user, ..)")
    public Object loggingExecutionTime(ProceedingJoinPoint pjp, User user) throws Throwable {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Object rt = null;
        try {
            rt = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
//            System.out.println("에러다!!!!!!!!!!!!");
        } finally {
            stopWatch.stop();
            System.out.println();
            System.out.println(user.toString());
            System.out.println(stopWatch.prettyPrint());
        }

        return rt;
    }
}
