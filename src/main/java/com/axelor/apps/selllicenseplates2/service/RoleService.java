package com.axelor.apps.selllicenseplates2.service;

import com.axelor.apps.selllicenseplates2.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getRoleForUser();

    Role findRoleByName(String roleName);
}
