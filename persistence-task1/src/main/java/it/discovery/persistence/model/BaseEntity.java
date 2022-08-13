package it.discovery.persistence.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseEntity {
	private int id;

	private LocalDateTime created;

	private LocalDateTime modified;

}
