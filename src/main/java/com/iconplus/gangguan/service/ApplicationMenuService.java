package com.iconplus.gangguan.service;

import java.util.List;

import com.iconplus.gangguan.domain.ApplicationMenu;

public interface ApplicationMenuService {

  public void deleteApplicationMenu(String applicationMenuId) throws Exception;

  public List<ApplicationMenu> getApplicationMenu();

  public List<ApplicationMenu> getApplicationMenu(Integer page, Integer size);

  public ApplicationMenu getApplicationMenuById(String id);

  public List<ApplicationMenu> getApplicationMenuChildrenFor(ApplicationMenu applicationMenu);

  public List<ApplicationMenu> getAvailableMenuForRole(List<String> roles) throws Exception;

  public void saveApplicationMenu(ApplicationMenu applicationMenu) throws Exception;

  public void updateApplicationMenu(ApplicationMenu applicationMenu) throws Exception;
}
