package com.example.shortURLProject.Controllers;

import com.example.shortURLProject.Models.User;
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

    // default localhost url will return index.html template
    @RequestMapping("/")
    public String home() {
        return "index";
    }

    // creates new model and binds returned values to model
    @GetMapping("/register")
    public String showForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register_form";
    }

    // recieves full_url from model creates randomChars as a new short url address
    //calls setShortURL
    @RequestMapping(value = "/shortenurl", method = RequestMethod.POST)
    public String getShortenUrl(User shortenUrl) throws MalformedURLException {
        String randomChar = getRandomChars();
        setShortUrl(randomChar, shortenUrl);
        return "register_success";
    }

    // loops 5 times adding a randomChar onto a string and returns random generated String
    private String getRandomChars() {
        String randomStr = "";
        String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 5; i++)
            randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
        return randomStr;
    }

    // sets shortUrl and adds it to hashmap using random char as the key
    private void setShortUrl(String randomChar, User shortenUrl) throws MalformedURLException {
        shortenUrl.setShort_url("http://localhost:8080/s/" + randomChar);
        shortenUrlList.put(randomChar, shortenUrl);
    }

    // gets full url from short url and links to site
    @RequestMapping(value = "/s/{randomstring}", method = RequestMethod.GET)
    public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString) throws IOException {
        response.sendRedirect(shortenUrlList.get(randomString).getFull_url());
    }

    // when user clicks generate return success page with url link and return home
    @PostMapping("/register")
    public String submitForm(@ModelAttribute("user") User user) {
        System.out.println(user);
        return "register_success";
    }


}
