package com.girlcoder.technicaltest.respository;

import com.girlcoder.technicaltest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
