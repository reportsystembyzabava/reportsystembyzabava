package com.reportsystembyzabava.demo.jpaRepositorys;

import com.reportsystembyzabava.demo.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatJpaRepository extends JpaRepository<ChatEntity,Long> {
}
