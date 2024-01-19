package com.good.fortunecookierest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnTransformer;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`order`")
public class Order extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fortune_id")
  private Fortune fortune;

  @Enumerated(EnumType.STRING)
  private Status status;

  @Convert(converter = JpaConverterJson.class)
  @ColumnTransformer(write = "?::jsonb")
  @Column
  private Address address;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Address {
    private String street;
    private String postalCode;
    private String city;
  }

  public enum Status {
    RECEIVED,
    BAKING,
    DELIVERED
  }
}
