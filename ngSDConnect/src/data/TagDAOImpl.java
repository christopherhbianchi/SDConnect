package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Post;
import entities.Tag;
import entities.User;

@Transactional
@Repository
public class TagDAOImpl implements TagDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Set<Tag> showAll() {
		String queryString = "Select t from Tag t";
		List<Tag> tempList = em.createQuery(queryString, Tag.class)
							   .getResultList();
		Set<Tag> tags = new HashSet<>(tempList);
		return tags;
	}

	@Override
	public Tag showTagById(int tagId) {
		return em.find(Tag.class, tagId);
	}

	@Override
	public Tag createTag(String tagJson) {
		ObjectMapper mapper = new ObjectMapper();
		Tag mappedTag = null;
		try {
			mappedTag = mapper.readValue(tagJson, Tag.class);
			em.persist(mappedTag);
			em.flush();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return mappedTag;
	}

	@Override
	public Tag updateTag(int tagId, String tagJson) {
		Tag managedTag = em.find(Tag.class, tagId);
		if(managedTag == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		Tag mappedTag = null;
		try {
			mappedTag = mapper.readValue(tagJson, Tag.class);
			if(mappedTag.getType() != null && mappedTag.getType() != "") {
				managedTag.setType(mappedTag.getType());
			}
			if(mappedTag.getTopics() != null) {
				managedTag.setTopics(mappedTag.getTopics());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return managedTag;
	}

	@Override
	public boolean deleteTag(int tagId) {
		Tag managedTag = em.find(Tag.class, tagId);
		if(managedTag==null) {
			return false;
		}
		em.remove(managedTag);
		return true;
	}

}
