package it.discovery.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GeneratedValue
	private int id;

	@Column(updatable = false)
	private LocalDateTime created;

	private LocalDateTime modified;


	@PrePersist
	void onPersist() {
		created = LocalDateTime.now();
	}

	@PreUpdate
	void onUpdate() {
		modified = LocalDateTime.now();
	}

}
