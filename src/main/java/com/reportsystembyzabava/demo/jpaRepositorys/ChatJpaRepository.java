package com.reportsystembyzabava.demo.jpaRepositorys;

import com.reportsystembyzabava.demo.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatJpaRepository extends JpaRepository<Chat,Long> {
}
