package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.enums.PlatformEnum;
import com.example.demo.common.utils.ApiResponse;
import com.example.demo.common.utils.BaseResponse;
import com.example.demo.converter.GameConverter;
import com.example.demo.model.bo.*;
import com.example.demo.model.dto.*;
import com.example.demo.model.vo.game.GameWithPlayingExperienceVo;
import com.example.demo.model.vo.game.GamesVo;
import com.example.demo.service.game.IGameService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final IGameService gameService;
    private final GameConverter gameConverter;

    @PostMapping("/GetGames")
    @Operation(summary = "查询游戏分页列表")
    public BaseResponse getGames(@Valid @RequestBody GetGamesDto dto) {
        GetGamesBo bo = gameConverter.toBo(dto);
        Page<GamesVo> gamesPage = gameService.getGamesPage(bo);
        return BaseResponse.success(gamesPage);
    }

    @PostMapping("/CreateGame")
    @Operation(summary = "创建游戏")
    public ApiResponse<String> createGame(
        @Valid @RequestBody GameCreateDto request
    ) {
        GameCreateBo game = gameConverter.toBo(request);
        int result = gameService.createGame(game);
        if (result > 0) {
            return ApiResponse.success("游戏创建成功");
        } else {
            return ApiResponse.error("游戏创建失败");
        }
    }

    @PostMapping("/UpdateGame")
    @Operation(summary = "更新游戏")
    public ApiResponse<String> updateGame(@Valid @RequestBody GameUpdateDto dto) {
        GameUpdateBo game = gameConverter.toBo(dto);
        int result = gameService.updateGame(game);
        if (result > 0) {
            return ApiResponse.success("游戏更新成功");
        } else {
            return ApiResponse.error("游戏更新失败");
        }
    }

    @GetMapping("/DeleteGame")
    @Operation(summary = "删除游戏")
    public ApiResponse<String> deleteGame(@Valid @RequestParam Long gameId) {
        int result = gameService.deleteGame(gameId);
        if (result > 0) {
            return ApiResponse.success("游戏删除成功");
        } else {
            return ApiResponse.error("游戏删除失败");
        }
    }

    @GetMapping("/GetGameWithPlayingExperience")
    @Operation(summary = "获取游戏及其游玩体验")
    public ApiResponse<
            GameWithPlayingExperienceVo
    > getGameWithPlayingExperience(@Validated @RequestParam Long gameId) {
        GameWithPlayingExperienceVo response =
            gameService.getGameWithPlayingExperience(gameId);
        return ApiResponse.success(response);
    }

    @PostMapping("/OperateGamePlayingExperience")
    @Operation(summary = "操作游戏游玩体验")
    public ApiResponse<Integer> operateGamePlayingExperience(
        @Valid @RequestBody GameOperateDto request
    ) {
        GameOperateBo bo = gameConverter.toBo(request);
        int result = gameService.operateGamePlayingExperience(bo);
        if (result > 0) {
            return ApiResponse.success(result, "操作成功");
        } else {
            return ApiResponse.error("操作失败");
        }
    }

    @GetMapping
    @Operation(summary = "删除游戏游玩体验")
    public ApiResponse<Integer> deleteGamePlayingExperience(
        @Valid @RequestParam Long experienceId
    ) {
        int result = gameService.deleteGamePlayingExperience(experienceId);
        if (result > 0) {
            return ApiResponse.success(result, "删除成功");
        } else {
            return ApiResponse.error("删除失败");
        }
    }
}
