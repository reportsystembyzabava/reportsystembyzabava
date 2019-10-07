package com.reportSystemByZabavaApplication.demo.jpaRepositorys;

import com.reportSystemByZabavaApplication.demo.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailJpaRepository extends JpaRepository<Mail, Long> {
}
