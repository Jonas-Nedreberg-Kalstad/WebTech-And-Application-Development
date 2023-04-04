package no.ntnu.idata2306.security;

import no.ntnu.idata2306.model.Role;
import no.ntnu.idata2306.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * Contains authentication information, needed by UserDetailsService.
 */
public class AccessUserDetails implements UserDetails {
  private final String email;
  private final String firstName;
  private final String lastName;
  private final String password;
  private final boolean active;
  private final Set<GrantedAuthority> authorities = new HashSet<>();

  public AccessUserDetails(User user) {
    this.email = user.getEmail();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.password = user.getPassword();
    this.active = user.getActive();
    this.convertRoles(user.getRoles());
  }

  private void convertRoles(Set<Role> roles) {
    authorities.clear();
    for (Role role : roles) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return active;
  }

  @Override
  public boolean isAccountNonLocked() {
    return active;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return active;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public boolean isActive() {
    return active;
  }
}
