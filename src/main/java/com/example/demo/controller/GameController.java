package com.example.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.request.PlayingExperienceOperateRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.request.GameCreateRequest;
import com.example.demo.response.GameWithPlayingExperienceResponse;
import com.example.demo.enums.PlatformEnum;
import com.example.demo.model.Games;
import com.example.demo.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/GetGames")
    @Operation(summary = "查询游戏分页列表")
    public ApiResponse<Page<Games>> GetGames(int page, int size) {
        Page<Games> gamesPage = gameService.getGamesPage(page, size);
        return ApiResponse.success(gamesPage);
    }

    @PostMapping("/CreateGame")
    @Operation(summary = "创建游戏")
    public ApiResponse<String> CreateGame(@Valid @RequestBody GameCreateRequest request) {
        Games game = new Games(request.getName(),
                request.getImage(),
                request.getStar(),
                PlatformEnum.fromCode(request.getPlatform()),
                request.getEvaluation());
        int result = gameService.createGame(game);
        if (result > 0) {
            return ApiResponse.success("游戏创建成功");
        } else {
            return ApiResponse.error("游戏创建失败");
        }
    }

    @PostMapping("/UpdateGame")
    @Operation(summary = "更新游戏")
    public ApiResponse<String> UpdateGame(@Valid @RequestBody Games game) {
        int result = gameService.updateGame(game);
        if (result > 0) {
            return ApiResponse.success("游戏更新成功");
        } else {
            return ApiResponse.error("游戏更新失败");
        }
    }

    @GetMapping("/DeleteGame")
    @Operation(summary = "删除游戏")
    public ApiResponse<String> DeleteGame(@Valid @RequestParam Long gameId) {
        int result = gameService.deleteGame(gameId);
        if (result > 0) {
            return ApiResponse.success("游戏删除成功");
        } else {
            return ApiResponse.error("游戏删除失败");
        }
    }

    @GetMapping("/GetGameWithPlayingExperience")
    @Operation(summary = "获取游戏及其游玩体验")
    public ApiResponse<GameWithPlayingExperienceResponse> GetGameWithPlayingExperience(@Valid @RequestParam Long gameId) {
        GameWithPlayingExperienceResponse response = gameService.getGameWithPlayingExperience(gameId);
        return ApiResponse.success(response);
    }

    @PostMapping
    @Operation(summary = "操作游戏游玩体验")
    public ApiResponse<Integer> OperateGamePlayingExperience(@Valid @RequestBody PlayingExperienceOperateRequest request){
        int result = gameService.operateGamePlayingExperience(request);
        if (result > 0) {
            return ApiResponse.success(result, "操作成功");
        } else {
            return ApiResponse.error("操作失败");
        }
    }

    @GetMapping
    @Operation(summary = "删除游戏游玩体验")
    public ApiResponse<Integer> DeleteGamePlayingExperience(@Valid @RequestParam Long experienceId){
        int result = gameService.deleteGamePlayingExperience(experienceId);
        if (result > 0) {
            return ApiResponse.success(result, "删除成功");
        } else {
            return ApiResponse.error("删除失败");
        }
    }
}
