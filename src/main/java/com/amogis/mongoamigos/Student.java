package com.amogis.mongoamigos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "Student")
public class Student {
    @Id
    private String id;
    private String fName;
    private String lName;
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favSub;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime created;

    public Student(String lName, String fName, String email, Gender gender, Address address, List<String> favSub, BigDecimal totalSpentInBooks, LocalDateTime created) {
        this.lName = lName;
        this.fName = fName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favSub = favSub;
        this.totalSpentInBooks = totalSpentInBooks;
        this.created = created;
    }
}
