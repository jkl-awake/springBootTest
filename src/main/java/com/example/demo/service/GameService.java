package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.GameMapper;
import com.example.demo.model.Games;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameMapper _gameMapper;

    public Page<Games> getGamesPage(int page, int size) {
        int safePage = Math.max(1, page);
        int safeSize = Math.max(1, size);
        Page<Games> gamesPage = new Page<>(safePage, safeSize);

        LambdaQueryWrapper<Games> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Games::getIsDeleted, false);
        return _gameMapper.selectPage(gamesPage, wrapper);
    }

    public int CreateGame(Games game) {
        return _gameMapper.insert(game);
    }

    public int UpdateGame(Games game) {
        Games existingGame = _gameMapper.selectById(game.getId());
        if (existingGame == null || Boolean.TRUE.equals(existingGame.getIsDeleted())) {
            return 0;
        }
        existingGame.UpdateGame(
            game.getGameName(),
            game.getImage(),
            game.getStar(),
            game.getPlatform(),
            game.getEvaluation()
        );
        return _gameMapper.updateById(existingGame);
    }

    public int DeleteGame(Long gameId) {
        Games game = _gameMapper.selectById(gameId);
        if (game == null || Boolean.TRUE.equals(game.getIsDeleted())) {
            return 0;
        }
        game.setIsDeleted(true);
        return _gameMapper.updateById(game);
    }
}