package jee.memory.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping ("/")
public class HomeController {
    @GetMapping
    public String showHome () {
        return "home";
    }

    private static int n = 0;
    private static final int max = 20;
    private static int moving = 0;
    @GetMapping("/next")
    public String nextImage (Model model) {
        if (n==20) n = 0;
        moving = ++n;
        model.addAttribute("id", Integer.toString(moving));
        return "imageshow";
    }


    @GetMapping("/prev")
    public String prevImage (Model model) {

        if (moving >= 2)
            moving = --n;
        else if (moving==1)
            moving =n=max;

        model.addAttribute("id", Integer.toString(moving));
        return "imageshow";
    }


    @GetMapping("/loop")
    public String loopImage (Model model) {
        try {
            Thread.sleep(1000);
            model.addAttribute("id", Integer.toString(n));
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "error";
        }
        return ("redirect:/slide");
    }

    @GetMapping("/slide")
    public String loop () {
        return "slideshow";
    }

    private static int m = 0;
    @GetMapping("/music")
    public String showVideo (Model model) {
        model.addAttribute("id", Integer.toString(++n));
        if (m==6) m = 0;
        return "music";
    }
}
