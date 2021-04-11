package com.maldaliza.phonebook.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Phone {

    private Long id;
    private String name;
    private String phoneNumber;
    private String memo;

    public Phone(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
