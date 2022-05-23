package com.demo.api.admin;

import com.demo.dto.BlogDTO;
import com.demo.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogAPI {

    @Autowired
    private IBlogService blogService;

    @PostMapping("/api/blog")
    public BlogDTO createBlog(@RequestBody BlogDTO blogDTO) {
        return blogService.save(blogDTO);
    }

    @PutMapping("/api/blog")
    public BlogDTO updateBlog(@RequestBody BlogDTO blogDTO) {
        return blogService.save(blogDTO);
    }

    @DeleteMapping("/api/blog")
    public void deleteBlog(@RequestBody long[] ids) {
        blogService.delete(ids);
    }

}
