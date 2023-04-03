package com.mfdev.redditcloneapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RedditCloneAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedditCloneAppApplication.class, args);
    }

}
