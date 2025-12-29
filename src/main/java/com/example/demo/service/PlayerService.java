package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.PlayerMapper;
import com.example.demo.model.Players;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerMapper playerMapper;

    /**
     * 创建玩家
     */
    public Players createPlayer(String userName) {
        Players player = new Players();
        player.setUserName(userName);
        // createdAt, updatedAt, isDeleted 会由 MyBatis-Plus 自动填充
        playerMapper.insert(player);
        return player;
    }

    /**
     * 根据ID查询玩家
     */
    public Optional<Players> getPlayerById(Long id) {
        Players player = playerMapper.selectById(id);
        // 如果被删除或不存在，返回空
        if (player == null || Boolean.TRUE.equals(player.getIsDeleted())) {
            return Optional.empty();
        }
        return Optional.of(player);
    }

    /**
     * 根据用户名查询玩家
     */
    public Optional<Players> getPlayerByUserName(String userName) {
        LambdaQueryWrapper<Players> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Players::getUserName, userName)
               .eq(Players::getIsDeleted, false);
        Players player = playerMapper.selectOne(wrapper);
        return Optional.ofNullable(player);
    }

    /**
     * 查询所有未删除的玩家
     */
    public List<Players> getAllPlayers() {
        LambdaQueryWrapper<Players> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Players::getIsDeleted, false);
        return playerMapper.selectList(wrapper);
    }

    public Page<Players> getPlayersPage(int page, int size) {
        int safePage = Math.max(1, page);
        int safeSize = Math.max(1, size);
        
        Page<Players> pageParam = new Page<>(safePage, safeSize);
        LambdaQueryWrapper<Players> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Players::getIsDeleted, false);
        
        return playerMapper.selectPage(pageParam, wrapper);
    }

    /**
     * 更新玩家信息
     */
    public Players updatePlayer(Long id, String userName) {
        Optional<Players> playerOpt = getPlayerById(id);
        if (playerOpt.isPresent()) {
            Players player = playerOpt.get();
            player.setUserName(userName);
            // updatedAt 会由 MyBatis-Plus 自动填充
            playerMapper.updateById(player);
            return player;
        }
        throw new RuntimeException("Player not found with id: " + id);
    }

    /**
     * 软删除玩家（标记为已删除，不真正删除）
     */
    public void deletePlayer(Long id) {
        Players player = playerMapper.selectById(id);
        if (player != null) {
            player.setIsDeleted(true);
            player.setUpdatedAt(OffsetDateTime.now());
            playerMapper.updateById(player);
        } else {
            throw new RuntimeException("Player not found with id: " + id);
        }
    }

    /**
     * 真正删除玩家（从数据库中删除）
     */
    public void deletePlayerPermanently(Long id) {
        playerMapper.deleteById(id);
    }
}
