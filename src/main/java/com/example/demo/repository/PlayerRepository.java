package com.example.demo.repository;

import com.example.demo.model.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PlayerRepository extends JpaRepository<Players, Long> {
    // 根据用户名查找（未删除的）
    Optional<Players> findByUserNameAndIsDeletedFalse(String userName);
    
    // 查询所有未删除的玩家
    List<Players> findAllByIsDeletedFalse();

    Page<Players> findAllByIsDeletedFalse(Pageable pageable);
}
