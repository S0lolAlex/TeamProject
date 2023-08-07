package com.example.DevTeamProject_Notes.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CmsPageController {
    @GetMapping("/about")
    public ModelAndView about() {
        return new ModelAndView("cms/about");
    }

    @GetMapping("/contact-us")
    public ModelAndView contact() {
        return new ModelAndView("cms/contact-us");
    }
}
