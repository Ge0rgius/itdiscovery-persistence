package it.discovery.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Getter
@Setter
@Table
@Entity
@Immutable
public class Log extends BaseEntity {

    private int entityId;

    private String entity;

    private boolean success;

    private String error;

}
