package com.axelor.apps.selllicenseplates2.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@Table(name = "car_number_lots")
@AllArgsConstructor
@NoArgsConstructor
public class CarNumberLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_car_number", nullable = false, length = 20)
    private String fullCarNumber;

    @Column(name = "first_letter", nullable = false, length = 1)
    private String firstLetter;

    @Column(name = "first_digit", nullable = false, length = 1)
    private String firstDigit;

    @Column(name = "second_digit", nullable = false, length = 1)
    private String secondDigit;

    @Column(name = "third_digit", nullable = false, length = 1)
    private String thirdDigit;

    @Column(name = "second_letter", nullable = false, length = 1)
    private String secondLetter;

    @Column(name = "third_letter", nullable = false, length = 1)
    private String thirdLetter;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(name = "updated_date")
    private Instant updatedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ColumnDefault("false")
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "original_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal originalPrice;

    @Column(name = "markup_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal markupPrice;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @ColumnDefault("false")
    @Column(name = "is_sold")
    private Boolean isSold;

    @Column(name = "comment")
    private String comment;

    @ColumnDefault("false")
    @Column(name = "is_confirm")
    private Boolean isConfirm;

}