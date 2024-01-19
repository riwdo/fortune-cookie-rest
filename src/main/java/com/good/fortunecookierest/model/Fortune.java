package com.good.fortunecookierest.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Fortune extends BaseEntity {

  @Column private String description;

  @Column private String author;
}
