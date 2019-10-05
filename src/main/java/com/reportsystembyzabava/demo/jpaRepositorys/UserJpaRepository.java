package com.reportsystembyzabava.demo.jpaRepositorys;

import com.reportsystembyzabava.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
