package com.nhnacademy.board.service;


import com.nhnacademy.board.domain.Post;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.exception.NotWriter;
import com.nhnacademy.board.repository.IPostRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final IPostRepository postRepository;

    public PostService(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long getId(){
        List<Post> postList = postRepository.findAll();
        int size = postList.size()-1;
        Long id = Long.valueOf(postList.get(size).getId());
        return id+1;
    }

    public Post getPost(Long id){
        Post post =  postRepository.findById(id).orElse(null);
        return post;
    }

    public void delete(Long id, HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        Post beforePost = postRepository.findById(id).orElse(null);
        if(!beforePost.getWriterUserId().equals(user.getId())){
            throw new NotWriter();
        }
        postRepository.deleteById(id);
    }
    public List<Post> getPartList(int num){
        num = (num-1)*10;
        int size=10;
        List<Post> postList = new ArrayList<>();
        List<Post> list = postRepository.findAll();
        if(list.size()<num+10){
            size=list.size()-num;
        }
        for(int i=0;i<size;i++){
            postList.add(list.get(num));
            num++;
        }
        return postList;
    }

    public List<Post> getPostList(){
        return postRepository.findAll();
    }

    public void register(Post post){
        postRepository.save(post);
    }

    public void modify(Long id,Post post){
        Post beforePost = postRepository.findById(id).orElse(null);
        if(!beforePost.getWriterUserId().equals(post.getWriterUserId())){
            throw new NotWriter();
        }
        postRepository.save(post);
    }
}
