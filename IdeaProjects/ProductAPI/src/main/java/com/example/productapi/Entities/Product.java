package com.example.productapi.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Product extends BaseClass {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private double price;
        private String category;
        private String description;
        private int quantity;
        private boolean isPaid;
        private boolean isSeen;

}
