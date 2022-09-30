package com.quintrix.mongoTest.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ShowTime {
  @Scheduled(cron = "* /10 * * * * *")
  public void printTime(){
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss\n");
    LocalDateTime now = LocalDateTime.now();
    System.out.print("The time is now " + dtf.format(now));
  }
}
