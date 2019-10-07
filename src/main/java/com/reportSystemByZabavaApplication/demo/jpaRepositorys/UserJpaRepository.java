package com.reportSystemByZabavaApplication.demo.jpaRepositorys;

import com.reportSystemByZabavaApplication.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
