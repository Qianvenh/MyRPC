package org.example;

public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).title("My Blog").userId(22).build();
        System.out.println("Client query the blog of id " + id);
        return blog;
    }
}
