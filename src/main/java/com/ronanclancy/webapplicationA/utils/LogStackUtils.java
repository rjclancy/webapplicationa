package com.ronanclancy.webapplicationA.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class LogStackUtils {

  public static List<String> logStack = new ArrayList<>();

  public static void logError(String message, Throwable throwable) {
    logStack.add(message + throwable);
    printLogStack();
    clearLogStack();
    throw new RuntimeException(message, throwable);
  }

  public static void logInfo(String message) {
    logStack.add(message);
  }

  public static void printLogStack() {
    for(String s : logStack){
      log.atInfo().log(s);
    }
  }

  public static void clearLogStack() {
    logStack = new ArrayList<>();
  }
}