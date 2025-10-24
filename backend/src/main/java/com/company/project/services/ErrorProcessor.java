package com.company.project.services;

import org.springframework.ui.Model;

import java.time.LocalDateTime;

public class ErrorProcessor {
  public void displayError(Model model, String name) {
    model.addAttribute("message", "Failed to connect to " + name);
    model.addAttribute("error", Thread.getAllStackTraces());
    model.addAttribute("timestamp", LocalDateTime.now());
  }
}