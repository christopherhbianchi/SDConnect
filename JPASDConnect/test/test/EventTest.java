package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Event;


public class EventTest {

	
	
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
	public void test_event() {
		Event eTest = em.find(Event.class, 1);
		assertEquals(eTest.getDescription(), "Have a ball on the company dime. Free games for all!");
		//assertEquals(eTest.getDate(), "2017-11-13 00:00:00");
	}
	
//	Post test = em.find(Post.class, 1);
//	assertEquals(test.getMessage(), "");
//	assertEquals(test.getUser().getId(), 1);
//	assertEquals(test.getThread().getId(), 1);
	

}
