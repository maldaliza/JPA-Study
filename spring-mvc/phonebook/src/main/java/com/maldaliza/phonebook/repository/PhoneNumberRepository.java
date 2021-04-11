package com.maldaliza.phonebook.repository;

import com.maldaliza.phonebook.domain.Phone;

import java.util.List;

public interface PhoneNumberRepository {

    // 전화번호 저장
    Phone save(Phone phone);

    // 전화번호id로 전화번호 조회
    Phone findById(Long phoneNumberId);

    // 모든 전화번호 조회
    List<Phone> findAll();
}
