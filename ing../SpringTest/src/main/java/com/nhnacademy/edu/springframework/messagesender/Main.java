package com.nhnacademy.edu.springframework.messagesender;

public class Main {
    public static void main(String[] args) {
        User user = new User("ghkwjd5343@gmail.com", "hwajeong");

        Main main = new Main();
        main.sendSmsMessage(user, "hi");
        main.sendEmailMessage(user, "hi");
    }

    private void sendSmsMessage(User user, String message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() + " : " + message);
    }

    private void sendEmailMessage(User user, String message) {
        System.out.println("Email Message Sent" + user.getEmail() + " : " + message);
    }
}