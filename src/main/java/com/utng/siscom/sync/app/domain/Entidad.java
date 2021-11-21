package com.utng.siscom.sync.app.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "entidades")
public class Entidad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String nombre;
    private Boolean activo;
    @Column(nullable = false, unique = true)
    @NotEmpty
    @Size(min = 2, max = 4)
    private String alias;
}
