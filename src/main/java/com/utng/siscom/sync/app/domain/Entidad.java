package com.utng.siscom.sync.app.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "entidades")
public class Entidad implements Serializable {
    private Long id;
    private String nombre;
    private Boolean activo;
    private String alias;
}
