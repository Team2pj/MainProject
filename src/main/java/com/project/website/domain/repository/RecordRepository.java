package com.project.website.domain.repository;


import com.project.website.domain.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {

}