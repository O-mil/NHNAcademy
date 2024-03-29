package com.nhnacademy.springbootstudent.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class MyHealthIndicator implements HealthIndicator {

    private final AtomicBoolean health = new AtomicBoolean(true);
    @Override
    public Health health() {
        if (!health.get()) {
            return Health.down().build();
        }
        return Health.up().build();
//        return Health.down().withDetail("reason", "I am down").build();
    }

    public void healthFail() {
        health.set(false);
    }

    public void healthRecover() {
        health.set(true);
    }


}
