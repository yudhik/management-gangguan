package com.iconplus.gangguan.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iconplus.gangguan.domain.ApplicationMenu;
import com.iconplus.gangguan.repository.ApplicationMenuRepository;
import com.iconplus.gangguan.service.ApplicationMenuService;

@Service("applicationMenuService")
@Transactional(readOnly = true)
public class ApplicationMenuServiceBean implements ApplicationMenuService {

  @Inject
  private ApplicationMenuRepository repository;

  @Override
  @Transactional(readOnly = false)
  public void deleteApplicationMenu(String applicationMenuId) throws Exception {
    repository.delete(new ApplicationMenu(applicationMenuId, null, null, null));
  }

  @Override
  public List<ApplicationMenu> getApplicationMenu() {
    return repository.findAllMenu();
  }

  @Override
  public List<ApplicationMenu> getApplicationMenu(Integer page, Integer size) {
    return repository.findAllMenu(page, size);
  }

  @Override
  public ApplicationMenu getApplicationMenuById(String id) {
    return repository.findById(id);
  }

  @Override
  public List<ApplicationMenu> getApplicationMenuChildrenFor(ApplicationMenu applicationMenu) {
    return repository.findMenuChildrens(applicationMenu.getId());
  }

  @Override
  public List<ApplicationMenu> getAvailableMenuForRole(List<String> roles) throws Exception {
    return repository.findAvailableMenuForRole(roles);
  }

  @Override
  @Transactional(readOnly = false)
  public void saveApplicationMenu(ApplicationMenu applicationMenu) throws Exception {
    repository.save(applicationMenu);
  }

  @Override
  @Transactional(readOnly = false)
  public void updateApplicationMenu(ApplicationMenu applicationMenu) throws Exception {
    repository.update(applicationMenu);
  }

}
