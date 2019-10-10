package com.reportSystemByZabavaApplication.demo.jpaRepositorys;

import com.reportSystemByZabavaApplication.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    User findByUserToken(String userToken);

    User findByeMail(String eMail);
}
