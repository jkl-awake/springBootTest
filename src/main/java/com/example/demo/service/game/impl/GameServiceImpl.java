// java
package com.example.demo.service.game.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.demo.common.enums.PlatformEnum;
import com.example.demo.converter.GameConverter;
import com.example.demo.mapper.GamePlayingExperienceMapper;
import com.example.demo.model.bo.*;
import com.example.demo.model.dos.GamePlayingExperience;
import com.example.demo.model.vo.game.GameWithPlayingExperienceVo;
import com.example.demo.model.vo.game.GamesVo;
import com.example.demo.service.game.IGameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.GameMapper;
import com.example.demo.model.dos.Games;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements IGameService {

    private final GameMapper gameMapper;
    private final GamePlayingExperienceMapper gamePlayingExperienceMapper;
    private final GameConverter gameConverter;

    // 查询游戏分页列表
    public Page<GamesVo> getGamesPage(GetGamesBo bo) {
        log.debug("getGamesPage called with page={}, size={}", bo.getPage(), bo.getSize());
        int safePage = Math.max(1, bo.getPage());
        int safeSize = Math.max(1, bo.getSize());
        Page<Games> gamesPage = new Page<>(safePage, safeSize);

        LambdaQueryWrapper<Games> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Games::getIsDeleted, false);
        var gamesVoIPage = gameMapper.selectPage(gamesPage, wrapper)
                .convert(gameConverter::toVo);
        Page<GamesVo> resultPage = new Page<>(gamesVoIPage.getCurrent(), gamesVoIPage.getSize(), gamesVoIPage.getTotal());
        resultPage.setRecords(gamesVoIPage.getRecords());
        return resultPage;
    }

    // 创建游戏
    @Transactional
    public int createGame(GameCreateBo game) {
        Games newGame = new Games(
                game.getName(),
                game.getImage(),
                game.getStar(),
                game.getPlatform(),
                game.getEvaluation()
        );
        return gameMapper.insert(newGame);
    }

    // 更新游戏
    @Transactional
    public int updateGame(GameUpdateBo game) {
        log.info("updateGame called with gameId={}", game.getId());
        Games existingGame = gameMapper.selectById(game.getId());
        if (existingGame == null || Boolean.TRUE.equals(existingGame.getIsDeleted())) {
            log.warn("Game not found or deleted, gameId={}", game.getId());
            return 0;
        }
        existingGame.UpdateGame(
                game.getName(),
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
    public GameWithPlayingExperienceVo getGameWithPlayingExperience(Long gameId) {
        log.info("getGameWithPlayingExperience called with gameId={}", gameId);
        Games game = gameMapper.selectById(gameId);
        if (game == null || Boolean.TRUE.equals(game.getIsDeleted())) {
            log.warn("Game not found or deleted, gameId={}", gameId);
            return new GameWithPlayingExperienceVo();
        }

        List<GameWithPlayingExperienceBo> experiences = gameMapper.getGameWithPlayingExperiences(gameId);
        if (!experiences.isEmpty()) {
            GameWithPlayingExperienceVo response = GamePlayingExperienceMapper.toResponseForGame(experiences, gameId);
            log.debug("getGameWithPlayingExperience success, gameId={}", gameId);
            return response;
        }

        return null;
    }

    // 操作游玩体验
    @Transactional(rollbackFor = Exception.class)
    public int operateGamePlayingExperience(GameOperateBo request) {
        validateRequest(request);
        Long gameId = request.getId();
        log.info("operateGameWithExperiences start, gameId={}", gameId);
        Games game = loadAndCheckGame(gameId);
        updateGame(game, request);

        List<GamePlayingExperience> dbActiveList = loadActiveExperiences(gameId);
        Set<Long> keepIds = saveOrUpdateExperiences(gameId, request.getPlayingExperiences(), dbActiveList);
        logicalDeleteMissing(dbActiveList, keepIds);
        log.info("operateGameWithExperiences success, gameId={}", gameId);
        return 1;
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

    // ------------------------- private methods -------------------------
    private void validateRequest(GameOperateBo request) {
        if (request == null) {
            throw new IllegalArgumentException("request 不能为空");
        }
        if (request.getId() == null || request.getId() <= 0) {
            throw new IllegalArgumentException("gameId 非法");
        }
    }

    private Games loadAndCheckGame(Long gameId) {
        Games game = gameMapper.selectById(gameId);
        if (game == null || Boolean.TRUE.equals(game.getIsDeleted())) {
            throw new IllegalStateException("游戏不存在或已删除，gameId=" + gameId);
        }
        return game;
    }

    private void updateGame(Games game, GameOperateBo request) {
        game.setGameName(request.getName());
        game.setImage(request.getImage());
        game.setStar(request.getStar());
        game.setPlatform(PlatformEnum.fromCode(request.getPlatform()));
        game.setEvaluation(request.getEvaluation());
        int rows = gameMapper.updateById(game);
        if (rows != 1) {
            throw new RuntimeException("更新游戏主表失败，gameId=" + game.getId());
        }
    }

    private List<GamePlayingExperience> loadActiveExperiences(Long gameId) {
        // 需要你在 mapper 中实现该方法：按 game_id 查询 is_deleted = false 的数据
        List<GamePlayingExperience> list = gamePlayingExperienceMapper.selectActiveByGameId(gameId);
        return list == null ? Collections.emptyList() : list;
    }

    /**
     * @return keepIds 本次前端明确保留（更新）的旧子项id，用于后续判定哪些要逻辑删除
     */
    private Set<Long> saveOrUpdateExperiences(Long gameId,
                                              List<PlayingExperienceOperateBo> incomingList,
                                              List<GamePlayingExperience> dbActiveList) {
        Map<Long, GamePlayingExperience> dbMap = dbActiveList.stream()
                .collect(Collectors.toMap(GamePlayingExperience::getId, e -> e));
        Set<Long> keepIds = new HashSet<>();
        if (CollectionUtils.isEmpty(incomingList)) {
            // 空集合代表“全量覆盖为空”，后续会把 dbActiveList 全部逻辑删除
            return keepIds;
        }
        for (PlayingExperienceOperateBo dto : incomingList) {
            if (dto == null) {
                throw new IllegalArgumentException("playingExperiences 存在空元素");
            }
            Long expId = dto.getPlayingExperienceId();
            String context = dto.getContext();
            // 新增
            if (expId <= 0) {
                GamePlayingExperience entity = new GamePlayingExperience();
                entity.setGameId(gameId);
                entity.setContext(context);
                entity.setIsDeleted(false);
                int insert = gamePlayingExperienceMapper.insert(entity);
                if (insert != 1) {
                    throw new RuntimeException("新增游玩体验失败，gameId=" + gameId);
                }
                continue;
            }
            // 更新
            if (!keepIds.add(expId)) {
                throw new IllegalArgumentException("playingExperiences 存在重复id: " + expId);
            }
            GamePlayingExperience dbEntity = dbMap.get(expId);
            if (dbEntity == null) {
                throw new IllegalStateException("游玩体验不存在/已删除/不属于当前游戏，id=" + expId);
            }
            dbEntity.setContext(context);
            int update = gamePlayingExperienceMapper.updateById(dbEntity);
            if (update != 1) {
                throw new RuntimeException("更新游玩体验失败，id=" + expId);
            }
        }
        return keepIds;
    }

    private void logicalDeleteMissing(List<GamePlayingExperience> dbActiveList, Set<Long> keepIds) {
        for (GamePlayingExperience old : dbActiveList) {
            Long oldId = old.getId();
            if (!keepIds.contains(oldId)) {
                old.setIsDeleted(true);
                int rows = gamePlayingExperienceMapper.updateById(old);
                if (rows != 1) {
                    throw new RuntimeException("逻辑删除游玩体验失败，id=" + oldId);
                }
            }
        }
    }
}
