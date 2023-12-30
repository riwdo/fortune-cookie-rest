package com.good.fortunecookierest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Fortune {

    @Id
    private Long id;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String description;

    @Column
    private String author;

}
