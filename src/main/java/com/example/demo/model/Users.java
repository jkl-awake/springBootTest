package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", schema = "player")
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseEntity {
    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;
}
