package com.axelor.apps.selllicenseplates2.repository;

import com.axelor.apps.selllicenseplates2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findRoleByRole(String role);
}