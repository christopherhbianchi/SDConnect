package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.Event;

public class EventTest {

	private EntityManagerFactory emf = null;
	private EntityManager em = null;

	
	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPASDConnect");
		em = emf.createEntityManager();
		
	}

	@AfterClass
	public void tearDownAfterClass() throws Exception {
		em.close();
		emf.close();

	}

	@Test
	public void test_event() {
		Event eTest = em.find(Event.class, 1);
		assertEquals(eTest.getDescription(), "");
		assertEquals(eTest.getDate(), "");
	}
	
//	Post test = em.find(Post.class, 1);
//	assertEquals(test.getMessage(), "");
//	assertEquals(test.getUser().getId(), 1);
//	assertEquals(test.getThread().getId(), 1);
	

}
