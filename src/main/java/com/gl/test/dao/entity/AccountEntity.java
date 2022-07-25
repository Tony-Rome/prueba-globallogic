package com.gl.test.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "account")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountEntity {

    @Id
    @Column(unique = true)
    private String uuid;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDateTime lastLogin;
    private LocalDateTime created;
    private boolean isActive;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    private List<PhoneEntity> phones;
}
