package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.User;

public class UserTest {
	
	private EntityManagerFactory emf = null;
	private EntityManager em = null;

	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPASDConnect");
		em = emf.createEntityManager();
	}

	@After
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}
	
	@Test
	public void smokeTest() {
		assertEquals("test", "test");
	}
	
	@Test
	public void User_retrieved_by_id() {
		User test = em.find(User.class, 1);
		assertEquals("jacqualine.y.mckenna@gmail.com", test.getEmail());
		assertEquals("1", test.getType());
	}

}