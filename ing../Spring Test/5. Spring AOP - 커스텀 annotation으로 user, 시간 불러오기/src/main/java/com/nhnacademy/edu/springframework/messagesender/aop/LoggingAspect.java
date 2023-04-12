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

    //execution(public * *.sendMessage(..)) && args(user, ..)
    @Around("@annotation(TestAnnotation) && args(user, ..)")        // arg를 부르려면 argument가 있는 메서드에 커스텀 annotation을 해줘야 함.
    public Object loggingExecutionTime(ProceedingJoinPoint pjp, User user) throws Throwable {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        Object rt = null;
        try {
            System.out.println("testAnnotation 실행");
            rt = pjp.proceed();
            System.out.println("testAnnotation 종료");
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
