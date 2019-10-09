package com.reportSystemByZabavaApplication.demo.jpaRepositorys;

import com.reportSystemByZabavaApplication.demo.entity.userExtraData.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationJpnRepository extends JpaRepository<Confirmation, Long> {
}
