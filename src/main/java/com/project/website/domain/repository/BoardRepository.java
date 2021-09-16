package com.project.website.domain.repository;

import com.project.website.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    List<BoardEntity> findByTitleContaining(String keyword);

    @Transactional
    @Modifying
    @Query("UPDATE BoardEntity b set b.viewCnt = b.viewCnt + 1 where id = ?1")
    int updateCount(Long id);
}
