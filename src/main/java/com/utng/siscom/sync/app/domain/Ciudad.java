package com.utng.siscom.sync.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ciudades")
public class Ciudad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Boolean activo;
    @Column(unique = true)
    private String alias;
    @ManyToOne
    @JoinColumn(name = "entidad_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Entidad entidad;
}
