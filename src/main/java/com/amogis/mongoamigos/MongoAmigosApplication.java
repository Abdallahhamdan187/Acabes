package com.amogis.mongoamigos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongoAmigosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoAmigosApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepo repo, MongoTemplate mongoTemplate) {
        return args -> {
            Address address = new Address(
                    "Jordan",
                    "10010",
                    "Amman"
            );
            String email = "a@gmail.com";
            Student student = new Student(
                    "abd",
                    "mo",
                    email,
                    Gender.Male,
                    address,
                    List.of("computer science", "Maths"),
                    BigDecimal.TEN,
                    LocalDateTime.now()

            );
            repo.findStudentByEmail(email).ifPresentOrElse(s->{},()->{});
//            Query query = new Query();
//            query.addCriteria(Criteria.where("email").is(email));
//            List<Student> students = mongoTemplate.find(query, Student.class);
//            if (students.size() > 1) {
//                throw new IllegalStateException("found many student with email!!" + email);
//            }
//            if (students.isEmpty()) {
//                repo.insert(student);
//            }
        };
    }
}
