package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "game_playing_experience", schema = "player")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GamePlayingExperience extends BaseEntity {

    //game表的id
    @Column(name = "game_id")
    private long GameId;

    @Column(name = "context")
    private String Context;
}
