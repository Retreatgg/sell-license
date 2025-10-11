package com.axelor.apps.selllicenseplates2.service.impl;

import com.axelor.apps.selllicenseplates2.model.Role;
import com.axelor.apps.selllicenseplates2.repository.RoleRepository;
import com.axelor.apps.selllicenseplates2.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private static final String DEFAULT_ROLE_NAME = "USER";

    @Override
    public Role getRoleForUser() {
        return findRoleByName(DEFAULT_ROLE_NAME);
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findRoleByRole(roleName);
    }

}
