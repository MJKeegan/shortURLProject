package com.example.shortURLProject.Controllers;

import com.example.shortURLProject.Models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    private Map<String, User> shortenUrlList = new HashMap<>();

    @RequestMapping(value="/shortenurl", method=RequestMethod.POST)
    public String getShortenUrl( User shortenUrl) throws MalformedURLException {
        String randomChar = getRandomChars();
        setShortUrl(randomChar, shortenUrl);
        return "register_success";
    }

    private void setShortUrl(String randomChar, User shortenUrl) throws MalformedURLException {
        shortenUrl.setShort_url("http://localhost:8080/s/"+randomChar);
        shortenUrlList.put(randomChar, shortenUrl);
    }

    private String getRandomChars() {
        String randomStr = "";
        String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 5; i++)
            randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
        return randomStr;
    }

    @RequestMapping(value="/s/{randomstring}", method=RequestMethod.GET)
    public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString) throws IOException {
        response.sendRedirect(shortenUrlList.get(randomString).getFull_url());
    }

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
