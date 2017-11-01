package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Cohort;
import entities.Profile;

public class CohortTest {
	
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
	public void cohort_retrieved_by_id() {
		Cohort test = em.find(Cohort.class, 1);
		assertEquals("Lions", test.getName());
		assertEquals(12, test.getCohortNum());
	}

}
