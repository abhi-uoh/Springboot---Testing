package com.junit5mockito;

import com.junit5mockito.entity.Contact;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbokJunit5MockitoExampleApplicationTests {


	@LocalServerPort
	private int port;

	@Autowired
	private TestH2Repository testH2Repository;

	private String baseUrl = "http://localhost";
	private  static RestTemplate restTemplate;

	@BeforeAll
	public static void init(){
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp(){
		baseUrl = baseUrl.concat(":").concat(port+"").concat("/contact");
	}

	@Test
	public void testAddContact(){
		Contact contact = new Contact(123L,"Manish","Raj","raj.manish@example.org","4105551212");
		Contact response = restTemplate.postForObject(baseUrl,contact,Contact.class);
		assertEquals("Manish",response.getFirstName());
		assertEquals(1,testH2Repository.findAll().size());

	}

	@Test
	@Sql(statements = "INSERT INTO contact (id, email, first_name, last_name, phone_no) VALUES(5, 'nk@gmail.com', 'Neeraj', 'Kr', '5678948374')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM contact WHERE id=5",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetContacts(){
		List<Contact> contacts = restTemplate.getForObject(baseUrl +"s",List.class);
		assertEquals(1,contacts.size());
		assertEquals(1,testH2Repository.findAll().size());
	}


	@Test
	@Sql(statements = "INSERT INTO contact (id, email, first_name, last_name, phone_no) VALUES(1, 'nk@gmail.com', 'Neeraj', 'Kr', '5678948374')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM contact WHERE id=1",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetContactsById(){
		Contact contact = restTemplate.getForObject(baseUrl +"/{id}",Contact.class,1);
		assertAll(
				()->assertNotNull(contact),
				()->assertEquals(1,contact.getId()),
				()->assertEquals("Neeraj",contact.getFirstName())
		);
	}


	@Test
	@Sql(statements = "INSERT INTO contact (id, email, first_name, last_name, phone_no) VALUES(1, 'nk@gmail.com', 'Neeraj', 'Kr', '5678948374')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void testDeleteContacts(){
		int recordCount = testH2Repository.findAll().size();
		assertEquals(1,recordCount);
		restTemplate.delete(baseUrl,Contact.class);
		assertEquals(0,testH2Repository.findAll().size());

	}

	@Test
	void contextLoads() {
	}

}
