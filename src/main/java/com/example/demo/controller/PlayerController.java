package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.PlayerCreateRequest;
import com.example.demo.dto.PlayerResponse;
import com.example.demo.dto.PlayerUpdateRequest;
import com.example.demo.model.Player;
import com.example.demo.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
@Tag(name = "Player", description = "Player management APIs")
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping
    @Operation(summary = "Create player")
    public ResponseEntity<ApiResponse<PlayerResponse>> create(@Valid @RequestBody PlayerCreateRequest req) {
        Player created = playerService.createPlayer(req.getUserName());
        return ResponseEntity.created(URI.create("/api/players/" + created.getId()))
                .body(ApiResponse.success(toResponse(created)));
    }

    @GetMapping
    @Operation(summary = "List all players")
    public ApiResponse<List<PlayerResponse>> list() {
        List<PlayerResponse> data = playerService.getAllPlayers().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ApiResponse.success(data);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get player by id")
    public ApiResponse<PlayerResponse> get(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
        return ApiResponse.success(toResponse(player));
    }

    @GetMapping("/username/{userName}")
    @Operation(summary = "Get player by username")
    public ApiResponse<PlayerResponse> getByUserName(@PathVariable String userName) {
        Player player = playerService.getPlayerByUserName(userName)
                .orElseThrow(() -> new RuntimeException("Player not found with username: " + userName));
        return ApiResponse.success(toResponse(player));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update player")
    public ApiResponse<PlayerResponse> update(@PathVariable Long id, @Valid @RequestBody PlayerUpdateRequest req) {
        Player updated = playerService.updatePlayer(id, req.getUserName());
        return ApiResponse.success(toResponse(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete player (soft delete)")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok(ApiResponse.success(null, "deleted"));
    }

    @DeleteMapping("/{id}/permanent")
    @Operation(summary = "Delete player permanently (hard delete)")
    public ResponseEntity<ApiResponse<Void>> deletePermanently(@PathVariable Long id) {
        playerService.deletePlayerPermanently(id);
        return ResponseEntity.ok(ApiResponse.success(null, "deleted permanently"));
    }

    @GetMapping("/page")
    @Operation(summary = "Page through players")
    public ApiResponse<Page<PlayerResponse>> page(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Page<PlayerResponse> data = playerService.getPlayersPage(page, size).map(this::toResponse);
        return ApiResponse.success(data);
    }

    private PlayerResponse toResponse(Player player) {
        return new PlayerResponse(
                player.getId(),
                player.getUserName(),
                player.getCreatedAt(),
                player.getUpdatedAt(),
                player.getIsDeleted()
        );
    }
}
