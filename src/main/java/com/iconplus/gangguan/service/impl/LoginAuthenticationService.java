package com.iconplus.gangguan.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iconplus.gangguan.domain.credential.Role;
import com.iconplus.gangguan.domain.credential.User;
import com.iconplus.gangguan.repository.RoleRepository;
import com.iconplus.gangguan.repository.UserRepository;

@Service("loginAuthenticationService")
public class LoginAuthenticationService implements UserDetailsService {

  private static final Logger LOG = LoggerFactory.getLogger(LoginAuthenticationService.class);

  @Inject
  private UserRepository userRepository;

  @Inject
  private RoleRepository roleRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LOG.debug("try to authenticate user : {}", username);
    User user = userRepository.findByByUsername(username);
    if (user == null) {
      return null;
    }
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (Role role : roleRepository.findRoleByUsername(user.getUsername())) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    LOG.debug("{} authenticate successfully", username);
    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), authorities);
  }

}
