package com.project.Ambulance.service;

import java.util.List;

import com.project.Ambulance.model.Blog;

public interface BlogService {
	List<Blog> getAllBlog();

	Blog getBlogById(int id);

	void saveBlog(Blog blog);

	void deleteBlog(int id);
	int countBlog();
}
