package com.tykdev.simple_leaderboard;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ProfileLogger {
    Logger logger = LoggerFactory.getLogger(ProfileLogger.class);
    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @PostConstruct
    public void logActiveProfile() {
        logger.info("*****Active Spring Profile: " + activeProfile + "*****");
    }
}