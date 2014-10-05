package com.iconplus.gangguan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = HomeController.BASE_PATH)
public class HomeController {

  public static final String BASE_PATH = "/view/home";
  private static final String ROOT = "/";

  @RequestMapping(value = ROOT, method = RequestMethod.GET)
  public String showHomepage(Model model) {
    return "home";
  }
}
