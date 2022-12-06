package com.junit5mockito.repository;

import com.junit5mockito.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    /*List<Contact> findByName(String name);*/
}
