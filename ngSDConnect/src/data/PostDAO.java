package data;

import java.util.Set;

import entities.Post;


public interface PostDAO {
	
		public Set<Post> showAllByTopicId(int topicId); 
		
		public Post showPostById(int postId); 
		
		public Post createPost(int userId, String postJson);
		
		public Post updatePost(int userId, int postId, String postJson);
		
		public boolean deletePost(int userId, int postId);

}
