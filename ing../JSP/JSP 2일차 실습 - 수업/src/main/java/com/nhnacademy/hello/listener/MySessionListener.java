package com.nhnacademy.hello.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class MySessionListener implements HttpSessionListener {

    // AtomicInteger
    // : int 자료형을 가지고 있는 wrapping class
    // 멀티쓰레드 환경에서 동시성을 보장함.
    // synchronized보다 적은 비용으로 동시성을 보장할 수 있음.
    private final AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
        int sessionCounter = atomicInteger.incrementAndGet();
        log.error("session-counter: {}", sessionCounter);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
        int sessionCounter = atomicInteger.decrementAndGet();
        log.error("session-counter: {}", sessionCounter);
    }
}
