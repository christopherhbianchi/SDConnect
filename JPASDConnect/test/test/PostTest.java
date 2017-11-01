package test;

import static org.junit.Assert.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import entities.Post;

public class PostTest {
	
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
	public void test_post_message_and_user_and_thread() {
		Post test = em.find(Post.class, 1);
		assertEquals(test.getMessage(), "");
		assertEquals(test.getUser().getId(), 1);
		assertEquals(test.getThread().getId(), 1);
	}

}
