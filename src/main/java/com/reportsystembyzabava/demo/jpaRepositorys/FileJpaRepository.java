package com.reportsystembyzabava.demo.jpaRepositorys;

import com.reportsystembyzabava.demo.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileJpaRepository extends JpaRepository<File, Long> {
}
