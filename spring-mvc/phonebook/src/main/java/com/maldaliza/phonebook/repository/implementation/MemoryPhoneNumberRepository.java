package com.maldaliza.phonebook.repository.implementation;

import com.maldaliza.phonebook.domain.Phone;
import com.maldaliza.phonebook.repository.PhoneNumberRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryPhoneNumberRepository implements PhoneNumberRepository {

    private static final Map<Long, Phone> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Phone save(Phone phone) {
        phone.setId(++sequence);
        store.put(phone.getId(), phone);
        return phone;
    }

    @Override
    public Phone findById(Long phoneNumberId) {
        Phone phone = store.get(phoneNumberId);
        return phone;
    }

    @Override
    public List<Phone> findAll() {
        ArrayList<Phone> phones = new ArrayList<>(store.values());
        return phones;
    }
}
