package com.junit5mockito;

import com.junit5mockito.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Contact,Long> {
}
