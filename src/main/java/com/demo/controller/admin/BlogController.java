package com.demo.controller.admin;

import com.demo.dto.BlogDTO;
import com.demo.service.IBlogService;
import com.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/admin-blog/list", method = RequestMethod.GET)
    public ModelAndView listBlog() {
        ModelAndView modelAndView = new ModelAndView("admin/blog/list");
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setListModel(blogService.findAll());
        modelAndView.addObject("model", blogDTO);
        return modelAndView;
    }

    @RequestMapping(value = "/admin-blog/edit", method = RequestMethod.GET)
    public ModelAndView editBlog(@RequestParam(value = "id", required = false) Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/blog/edit");
        BlogDTO blogDTO = new BlogDTO();
        if (id != null) {
            blogDTO = blogService.findOne(id);
        }
        modelAndView.addObject("model", blogDTO);
        modelAndView.addObject("categories", categoryService.findAllAsMap());
        return modelAndView;
    }
}
