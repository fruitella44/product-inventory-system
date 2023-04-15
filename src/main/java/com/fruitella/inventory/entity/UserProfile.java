package com.fruitella.inventory.entity;

import com.fruitella.inventory.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(schema = "inventory_system", name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userProfileId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Enumerated
    @Column(name = "user_role")
    private UserRole userRole;

    @Column(name = "birthday")
    private LocalDate birthday;

    @OneToOne
    @JoinColumn(name = "id")
    private Users users;
}
