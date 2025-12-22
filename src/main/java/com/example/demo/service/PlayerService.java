package com.example.demo.service;

import com.example.demo.model.Players;
import com.example.demo.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository repository;

    /**
     * 创建玩家
     */
    public Players createPlayer(String userName) {
        Players player = new Players();
        player.setUserName(userName);
        player.setCreatedAt(OffsetDateTime.now());
        player.setUpdatedAt(OffsetDateTime.now());
        player.setIsDeleted(false);
        return repository.save(player);
    }

    /**
     * 根据ID查询玩家
     */
    public Optional<Players> getPlayerById(Long id) {
        Optional<Players> player = repository.findById(id);
        // 如果被删除，返回空
        if (player.isPresent() && player.get().getIsDeleted()) {
            return Optional.empty();
        }
        return player;
    }

    /**
     * 根据用户名查询玩家
     */
    public Optional<Players> getPlayerByUserName(String userName) {
        return repository.findByUserNameAndIsDeletedFalse(userName);
    }

    /**
     * 查询所有未删除的玩家
     */
    public List<Players> getAllPlayers() {
        return repository.findAllByIsDeletedFalse();
    }

    public Page<Players> getPlayersPage(int page, int size) {
        int safePage = Math.max(0, page - 1);
        int safeSize = Math.max(1, size);
        PageRequest pageRequest = PageRequest.of(safePage, safeSize);
        return repository.findAllByIsDeletedFalse(pageRequest);
    }

    /**
     * 更新玩家信息
     */
    public Players updatePlayer(Long id, String userName) {
        Optional<Players> playerOpt = getPlayerById(id);
        if (playerOpt.isPresent()) {
            Players player = playerOpt.get();
            player.setUserName(userName);
            player.setUpdatedAt(OffsetDateTime.now());
            return repository.save(player);
        }
        throw new RuntimeException("Player not found with id: " + id);
    }

    /**
     * 软删除玩家（标记为已删除，不真正删除）
     */
    public void deletePlayer(Long id) {
        Optional<Players> playerOpt = repository.findById(id);
        if (playerOpt.isPresent()) {
            Players player = playerOpt.get();
            player.setIsDeleted(true);
            player.setUpdatedAt(OffsetDateTime.now());
            repository.save(player);
        } else {
            throw new RuntimeException("Player not found with id: " + id);
        }
    }

    /**
     * 真正删除玩家（从数据库中删除）
     */
    public void deletePlayerPermanently(Long id) {
        repository.deleteById(id);
    }
}
