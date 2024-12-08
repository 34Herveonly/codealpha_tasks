package net.Management001.Student_mgt_system_SpringBoot.Service;

import net.Management001.Student_mgt_system_SpringBoot.Entity.Post;
import net.Management001.Student_mgt_system_SpringBoot.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Create a new post
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // Get all posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Get a single post by ID
    public Optional<Post> getPostById(long id) {
        return postRepository.findById(id);
    }

    // Update an existing post
    public Post updatePost(long id, Post updatedPost) {
        if (postRepository.existsById(id)) {
            updatedPost.setId(id);  // Ensure that the ID remains the same for the update
            return postRepository.save(updatedPost);
        }
        return null;  // Optionally handle this scenario more gracefully
    }

    // Delete a post by ID
    public boolean deletePost(long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
