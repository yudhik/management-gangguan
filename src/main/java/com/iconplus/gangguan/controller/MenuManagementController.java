package com.iconplus.gangguan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iconplus.gangguan.domain.ApplicationMenu;
import com.iconplus.gangguan.helper.DomainHelper;
import com.iconplus.gangguan.service.ApplicationMenuService;
import com.iconplus.gangguan.viewobject.AvailableMenu;

@Controller
@RequestMapping(value = MenuManagementController.BASE_PATH)
public class MenuManagementController {

  private static final String ROOT = "/";
  private static final String DETAIL = "/detail/{id}";
  private static final String SUMMARY = "/summary";
  private static final String FORM = "/form";
  private static final String UPDATE = "/update/{id}";

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public static final String BASE_PATH = "/view/administration/menu";
  public static final String SUMMARY_PATH = BASE_PATH + SUMMARY;
  public static final String FORM_PATH = BASE_PATH + FORM;
  public static final String DETAIL_PATH = BASE_PATH + DETAIL;
  public static final String UPDATE_PATH = BASE_PATH + UPDATE;

  @Inject
  private ApplicationMenuService applicationMenuService;

  @RequestMapping(value = FORM, method = RequestMethod.GET)
  public String getMenuForm(Model model) throws Exception {
    model.addAttribute("availableMenu", new AvailableMenu());
    return FORM_PATH;
  }

  @RequestMapping(value = SUMMARY, method = RequestMethod.GET)
  public String getMenuSummary(@RequestParam(defaultValue = "0") Integer page, @RequestParam(
      defaultValue = "10") Integer size, Model model) throws Exception {
    List<AvailableMenu> availableMenus = new ArrayList<AvailableMenu>();
    // TODO : change getAvailableMenu using pagination when database server is decided
    for (ApplicationMenu menu : applicationMenuService.getApplicationMenu()) {
      availableMenus.add(new AvailableMenu(menu.getId(), menu.getLabel(), menu.getRelativeUrl(),
          null));
    }
    model.addAttribute("availableMenus", MAPPER.writeValueAsString(availableMenus));
    return SUMMARY_PATH;
  }

  @RequestMapping(value = FORM, method = RequestMethod.POST)
  public String submitMenuForm(@ModelAttribute AvailableMenu availableMenu, Model model)
      throws Exception {
    applicationMenuService.saveApplicationMenu(new ApplicationMenu(DomainHelper
        .generateUUIDString(), null, availableMenu.getLabel(), availableMenu.getUrl()));
    return SUMMARY_PATH;
  }
}
