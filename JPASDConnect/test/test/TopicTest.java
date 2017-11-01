package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Topic;

public class TopicTest {

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
	public void test_topic_name_and_posts () {
		Topic topic = em.find(Topic.class, 1);
		assertEquals(topic.getName(), "Searching for roommate.");
		assertEquals(topic.getPosts().size(), 1);

	}
}
