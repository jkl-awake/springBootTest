// java
package com.example.demo.service.game;

import java.util.List;

import com.example.demo.mapper.GamePlayingExperienceMapper;
import com.example.demo.model.GamePlayingExperience;
import com.example.demo.request.PlayingExperienceOperateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.GameWithPlayingExperienceDto;
import com.example.demo.response.GameWithPlayingExperienceResponse;
import com.example.demo.mapper.GameMapper;
import com.example.demo.model.Games;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements IGameService {

    private final GameMapper gameMapper;
    private final GamePlayingExperienceMapper gamePlayingExperienceMapper;

    // 查询游戏分页列表
    public Page<Games> getGamesPage(int page, int size) {
        log.debug("getGamesPage called with page={}, size={}", page, size);
        int safePage = Math.max(1, page);
        int safeSize = Math.max(1, size);
        Page<Games> gamesPage = new Page<>(safePage, safeSize);

        LambdaQueryWrapper<Games> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Games::getIsDeleted, false);
        return gameMapper.selectPage(gamesPage, wrapper);
    }

    // 创建游戏
    @Transactional
    public int createGame(Games game) {
        log.info("createGame called with gameName={}", game.getGameName());
        return gameMapper.insert(game);
    }

    // 更新游戏
    @Transactional
    public int updateGame(Games game) {
        log.info("updateGame called with gameId={}", game.getId());
        Games existingGame = gameMapper.selectById(game.getId());
        if (existingGame == null || Boolean.TRUE.equals(existingGame.getIsDeleted())) {
            log.warn("Game not found or deleted, gameId={}", game.getId());
            return 0;
        }
        existingGame.UpdateGame(
                game.getGameName(),
                game.getImage(),
                game.getStar(),
                game.getPlatform(),
                game.getEvaluation()
        );
        return gameMapper.updateById(existingGame);
    }

    // 删除游戏
    @Transactional
    public int deleteGame(Long gameId) {
        log.info("deleteGame called with gameId={}", gameId);
        Games game = gameMapper.selectById(gameId);
        if (game == null || Boolean.TRUE.equals(game.getIsDeleted())) {
            log.warn("Game not found or deleted, gameId={}", gameId);
            return 0;
        }
        
        return gameMapper.deleteById(game);
    }

    // 获取游戏与游玩体验
    public GameWithPlayingExperienceResponse getGameWithPlayingExperience(Long gameId) {
        log.info("getGameWithPlayingExperience called with gameId={}", gameId);
        Games game = gameMapper.selectById(gameId);
        if (game == null || Boolean.TRUE.equals(game.getIsDeleted())) {
            log.warn("Game not found or deleted, gameId={}", gameId);
            return new GameWithPlayingExperienceResponse();
        }

        List<GameWithPlayingExperienceDto> experiences = gameMapper.getGameWithPlayingExperiences(gameId);
        if (!experiences.isEmpty()) {
            GameWithPlayingExperienceResponse response = GamePlayingExperienceMapper.toResponseForGame(experiences, gameId);
            log.debug("getGameWithPlayingExperience success, gameId={}", gameId);
            return response;
        }

        return null;
    }

    // 操作游玩体验
    @Transactional
    public int operateGamePlayingExperience(PlayingExperienceOperateRequest request) {
        log.info("operateGamePlayingExperience called with gameId={}", request.getGameId());
        if(request.getGameId() == 0) {
            log.error("Invalid gameId in request");
            return 0;
        }

        if(request.getPlayingExperienceId() == 0) {
            GamePlayingExperience experience = new GamePlayingExperience(
                    request.getGameId(),
                    request.getContext()
            );
            return gamePlayingExperienceMapper.insert(experience);
        }
        else {
            GamePlayingExperience experience = gamePlayingExperienceMapper.selectById(request.getPlayingExperienceId());
            if (experience == null || Boolean.TRUE.equals(experience.getIsDeleted())) {
                log.warn("Experience not found or deleted, experienceId={}", request.getPlayingExperienceId());
                return 0;
            }
            experience.setContext(request.getContext());
            return gamePlayingExperienceMapper.updateById(experience);
        }
    }

    // 删除游玩体验
    @Transactional
    public int deleteGamePlayingExperience(Long experienceId) {
        log.info("deleteGamePlayingExperience called with experienceId={}", experienceId);
        GamePlayingExperience experience = gamePlayingExperienceMapper.selectById(experienceId);
        if (experience == null || Boolean.TRUE.equals(experience.getIsDeleted())) {
            log.warn("Experience not found or deleted, experienceId={}", experienceId);
            return 0;
        }

        return gamePlayingExperienceMapper.deleteById(experience);
    }
}
