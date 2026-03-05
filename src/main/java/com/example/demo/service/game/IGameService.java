package com.example.demo.service.game;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.model.bo.*;
import com.example.demo.model.vo.game.GameWithPlayingExperienceVo;
import com.example.demo.model.vo.game.GamesVo;

public interface IGameService {

    Page<GamesVo> getGamesPage(GetGamesBo bo);

    int createGame(GameCreateBo game);

    int updateGame(GameUpdateBo game);

    int deleteGame(Long gameId);

    GameWithPlayingExperienceVo getGameWithPlayingExperience(Long gameId);

    int operateGamePlayingExperience(GameOperateBo request);

    int deleteGamePlayingExperience(Long experienceId);
}
