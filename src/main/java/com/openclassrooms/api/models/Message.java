package com.openclassrooms.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "MESSAGES")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private Rental rental;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String message;
    private Date created_at;
    private Date updated_at;
}
