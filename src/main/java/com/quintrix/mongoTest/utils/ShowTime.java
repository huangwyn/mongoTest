package com.quintrix.mongoTest.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ShowTime {
  @Scheduled(cron = "* /10 * * * * *")
  public void printTime(){
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    System.out.print(dtf.format(now));
  }
}
