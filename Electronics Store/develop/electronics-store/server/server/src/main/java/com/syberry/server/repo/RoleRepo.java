package com.syberry.server.repo;

import com.syberry.server.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import com.syberry.server.model.ERole;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
