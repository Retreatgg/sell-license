package com.axelor.apps.selllicenseplates2.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "original_phone_number", nullable = false, length = 20)
    private String originalPhoneNumber;

    @Column(name = "changed_phone_number", nullable = false, length = 20)
    private String changedPhoneNumber;

    @Column(name = "email", nullable = false, length = 300)
    private String email;

    @Column(name = "password", nullable = false, length = 260)
    private String password;

    @ColumnDefault("true")
    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public boolean getIsAdmin() {
        return this.role != null && "ADMIN".equalsIgnoreCase(this.role.getRole());
    }
}