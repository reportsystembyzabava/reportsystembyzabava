package com.reportsystembyzabava.demo.jpaRepositorys;

import com.reportsystembyzabava.demo.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileJpaRepository extends JpaRepository<FileEntity, Long> {
}
