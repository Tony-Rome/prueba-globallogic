package com.gl.test.dao.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "phone")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PhoneEntity {

    @Id
    private int number;
    private int cityCode;
    private String countryCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_uuid")
    private AccountEntity account;

}
