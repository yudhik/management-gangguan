package com.iconplus.gangguan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
  private static final String[] IGNORED_AVAILABLE_MENU_FIELDS = new String[] {"childMenu"};

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public static final String BASE_PATH = "/view/administration/menu";
  public static final String SUMMARY_PATH = "/administration/menu" + SUMMARY;
  public static final String FORM_PATH = "/administration/menu" + FORM;
  public static final String DETAIL_PATH = "/administration/menu" + DETAIL;
  public static final String UPDATE_PATH = "/administration/menu" + UPDATE;

  @Inject
  private ApplicationMenuService applicationMenuService;

  @RequestMapping(value = FORM, method = RequestMethod.GET)
  public String getMenuForm(Model model) throws Exception {
    model.addAttribute("availableMenu", new AvailableMenu());
    model.addAttribute("parents", applicationMenuService.getApplicationMenu());
    return FORM_PATH;
  }

  @RequestMapping(value = SUMMARY, method = RequestMethod.GET)
  public String getMenuSummary(@RequestParam(defaultValue = "0") Integer page, @RequestParam(
      defaultValue = "10") Integer size, Model model) throws Exception {
    List<AvailableMenu> availableMenus = new ArrayList<AvailableMenu>();
    // TODO : change getAvailableMenu using pagination when database server is decided
    for (ApplicationMenu menu : applicationMenuService.getApplicationMenu()) {
      availableMenus.add(new AvailableMenu(menu.getId(), menu.getLabel(), menu.getRelativeUrl(),
          null, null));
    }
    model.addAttribute("availableMenus", availableMenus);
    model.addAttribute("availableMenuJson", MAPPER.writeValueAsString(availableMenus));
    return SUMMARY_PATH;
  }

  @RequestMapping(value = FORM, method = RequestMethod.POST)
  public String submitMenuForm(@ModelAttribute AvailableMenu availableMenu, Model model)
      throws Exception {
    try {
      applicationMenuService.saveApplicationMenu(new ApplicationMenu(DomainHelper
          .generateUUIDString(), StringUtils.isEmpty(availableMenu.getParent()) ? null
          : availableMenu.getParent(), availableMenu.getLabel(), availableMenu.getRelativeUrl()));
    } catch (Exception e) {
      model.addAttribute("errorMessage", e.getMessage());
      return FORM_PATH;
    }
    return "redirect:" + SUMMARY.replace("/", "");
  }

  @RequestMapping(value = DETAIL, method = RequestMethod.GET)
  public String viewMenuDetail(@PathVariable("id") String id, Model model) throws Exception {
    ApplicationMenu applicationMenu = applicationMenuService.getApplicationMenuById(id);
    AvailableMenu availableMenu = new AvailableMenu();
    BeanUtils.copyProperties(applicationMenu, availableMenu, IGNORED_AVAILABLE_MENU_FIELDS);
    List<ApplicationMenu> applicationMenuChildren =
        applicationMenuService.getApplicationMenuChildrenFor(applicationMenu);
    if (applicationMenuChildren != null) {
      List<AvailableMenu> childMenus = new ArrayList<AvailableMenu>();
      for (ApplicationMenu menu : applicationMenuChildren) {
        AvailableMenu child = new AvailableMenu();
        BeanUtils.copyProperties(menu, child, IGNORED_AVAILABLE_MENU_FIELDS);
        childMenus.add(child);
      }
      availableMenu.setChildMenu(childMenus);
    }
    model.addAttribute("availableMenu", availableMenu);
    return DETAIL_PATH.replace("/{id}", "");
  }
}
