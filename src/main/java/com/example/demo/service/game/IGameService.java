package com.example.demo.service.game;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.model.dos.Games;
import com.example.demo.model.dto.PlayingExperienceOperateDto;
import com.example.demo.model.vo.game.GameWithPlayingExperienceVo;

public interface IGameService {

    Page<Games> getGamesPage(int page, int size);

    int createGame(Games game);

    int updateGame(Games game);

    int deleteGame(Long gameId);

    GameWithPlayingExperienceVo getGameWithPlayingExperience(Long gameId);

    int operateGamePlayingExperience(PlayingExperienceOperateDto request);

    int deleteGamePlayingExperience(Long experienceId);
}
