package com.nhnacademy.springcore.sender;

import com.nhnacademy.springcore.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.assertj.core.api.Assertions;
import org.mockito.Spy;

import static org.mockito.Mockito.when;

@Slf4j
class DoorayMessageSenderTest {

    @Spy
    @InjectMocks
    private DoorayMessageSender doorayMessageSender;

    @Mock
    private DoorayHookSender doorayHookSender;

    private static final Logger logger = LoggerFactory.getLogger(DoorayMessageSender.class);

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void TestDooraySender() {


        User user = new User("hey");
        when(doorayMessageSender.sendMessage(user,"hi")).thenReturn(true);
        boolean actural = doorayMessageSender.sendMessage(user, "hi");
        Assertions.assertThat(actural).isEqualTo(true);


    }
}