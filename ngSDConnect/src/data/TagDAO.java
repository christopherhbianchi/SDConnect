package data;

import java.util.Set;

import entities.Tag;

public interface TagDAO {
	
		public Set<Tag> showAll(); //does not need id to show all topics
	
		public Tag showTagById(int tagId); //all users can see every topic, no need for id
	
		public Tag createTag(String tagJson);
		
		public Tag updateTag(int tagId, String tagJson);
		
		public boolean deleteTag(int tagId);

}
