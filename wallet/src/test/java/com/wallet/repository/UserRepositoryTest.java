package com.wallet.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entities.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
public class UserRepositoryTest {

	private static final String EMAIL = "email@teste.com.br";
	
	@Autowired
	UserRepository repository;
	
	@Before
	public void setUp() {
		User u = new User();
		u.setName("Wallace");
		u.setPassword("12345");
		u.setEmail(EMAIL);
		
		repository.save(u);
	}
	
	@After
	public void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	public void testSave() {
		User user = new User();
		user.setName("Wallace");
		user.setPassword("12345");
		user.setEmail("wallacecosme@gmail.com");
		
		User response = repository.save(user);
		
		assertNotNull(response);
	}
	
	@Test
	public void testFindByEmail() {
		Optional<User> response = repository.findByEmailEquals(EMAIL);
		
		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), EMAIL);
	}
}
