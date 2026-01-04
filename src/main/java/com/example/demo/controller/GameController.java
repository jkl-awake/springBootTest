package com.example.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.GameCreateRequest;
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
                               request.getPlatform(),
                               request.getEvaluation());
        int result = gameService.CreateGame(game);
        if (result > 0) {
            return ApiResponse.success("游戏创建成功");
        } else {
            return ApiResponse.error("游戏创建失败");
        }
    }

    @PostMapping("/UpdateGame")
    @Operation(summary = "更新游戏")
    public ApiResponse<String> UpdateGame(@Valid @RequestBody Games game) {
        int result = gameService.UpdateGame(game);
        if (result > 0) {
            return ApiResponse.success("游戏更新成功");
        } else {
            return ApiResponse.error("游戏更新失败");
        }
    }

    @GetMapping("/DeleteGame")
    @Operation(summary = "删除游戏")
    public ApiResponse<String> DeleteGame(@Valid @RequestParam Long gameId) {
        int result = gameService.DeleteGame(gameId);
        if (result > 0) {
            return ApiResponse.success("游戏删除成功");
        } else {
            return ApiResponse.error("游戏删除失败");
        }
    }
}
