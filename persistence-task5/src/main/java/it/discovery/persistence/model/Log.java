package it.discovery.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table
@Entity
public class Log extends BaseEntity {

    private int entityId;

    private String entity;

    private boolean success;

    private String error;

}
