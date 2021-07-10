package com.jpa.bookmanager.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {


    private String city;    //시

    private String district;//구

    @Column(name = "address_detail")
    private String detail;  //상세주소

    private String zipCode; //우편번호


}
