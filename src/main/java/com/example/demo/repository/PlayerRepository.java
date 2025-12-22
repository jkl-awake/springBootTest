package com.example.demo.repository;

import com.example.demo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    // 根据用户名查找（未删除的）
    Optional<Player> findByUserNameAndIsDeletedFalse(String userName);
    
    // 查询所有未删除的玩家
    List<Player> findAllByIsDeletedFalse();

    Page<Player> findAllByIsDeletedFalse(Pageable pageable);
}
