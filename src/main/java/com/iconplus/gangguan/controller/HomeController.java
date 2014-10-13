package com.iconplus.gangguan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iconplus.gangguan.domain.ApplicationMenu;
import com.iconplus.gangguan.service.ApplicationMenuService;
import com.iconplus.gangguan.viewobject.AvailableMenu;

@Controller
@RequestMapping(value = HomeController.BASE_PATH)
public class HomeController {

  private static final String ROOT = "/";
  private static final String AVAILABLE_MENU = "/availableMenu";

  public static final String BASE_PATH = "/view/home";
  public static final String AVAILABLE_MENU_PATH = BASE_PATH + AVAILABLE_MENU;


  @Inject
  private ApplicationMenuService applicationMenuService;

  @RequestMapping(value = AVAILABLE_MENU, method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseBody
  public List<AvailableMenu> getAvailableMenu(Model model, Authentication authentication)
      throws Exception {
    List<String> roles = new ArrayList<String>();
    for (GrantedAuthority authority : authentication.getAuthorities()) {
      roles.add(authority.getAuthority());
    }
    List<ApplicationMenu> applicationMenus = applicationMenuService.getAvailableMenuForRole(roles);
    List<AvailableMenu> availableMenus = new ArrayList<AvailableMenu>();
    Map<String, List<AvailableMenu>> applicationMenuChilds =
        new HashMap<String, List<AvailableMenu>>();
    for (ApplicationMenu applicationMenu : applicationMenus) {
      if (applicationMenu.getParent() == null) {
        availableMenus.add(new AvailableMenu(applicationMenu.getId(), applicationMenu.getLabel(),
            applicationMenu.getRelativeUrl(), null, null));
      } else {
        if (applicationMenuChilds.get(applicationMenu.getParent()) == null) {
          List<AvailableMenu> childs = new ArrayList<AvailableMenu>();
          childs.add(new AvailableMenu(applicationMenu.getId(), applicationMenu.getLabel(),
              applicationMenu.getRelativeUrl(), null, null));
          applicationMenuChilds.put(applicationMenu.getParent(), childs);
        } else {
          applicationMenuChilds.get(applicationMenu.getParent()).add(
              new AvailableMenu(applicationMenu.getId(), applicationMenu.getLabel(),
                  applicationMenu.getRelativeUrl(), null, null));
        }
      }
    }

    for (Entry<String, List<AvailableMenu>> entry : applicationMenuChilds.entrySet()) {
      for (AvailableMenu menu : entry.getValue()) {
        if (menu.getId().equals(entry.getKey())) {
          menu.setChildMenu(entry.getValue());
        }
      }
    }

    for (AvailableMenu availableMenu : availableMenus) {
      availableMenu.setChildMenu(applicationMenuChilds.get(availableMenu.getId()));
    }

    return availableMenus;
  }

  @RequestMapping(value = ROOT, method = RequestMethod.GET)
  public String showHomepage(Model model) {
    return "home";
  }
}
