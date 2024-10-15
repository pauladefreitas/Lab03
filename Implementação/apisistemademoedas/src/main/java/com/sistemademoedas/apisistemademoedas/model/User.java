package com.sistemademoedas.apisistemademoedas.model;

import com.sistemademoedas.apisistemademoedas.model.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_users")
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(unique = true)
    private String CPF;

    @Enumerated
    private RoleEnum role;
}
