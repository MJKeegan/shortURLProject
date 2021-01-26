package com.example.shortURLProject.Controllers;

import com.example.shortURLProject.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping("/")
    public String home() {
        System.out.println("Going home...");
        return "index";
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        System.out.println("FORM...");
        User user = new User();
        model.addAttribute("user", user);
        return "register_form";
    }

    @PostMapping ("/register")
    public String submitForm(@ModelAttribute("user") User user) {
        System.out.println(user);
        return "register_success";
    }
}
