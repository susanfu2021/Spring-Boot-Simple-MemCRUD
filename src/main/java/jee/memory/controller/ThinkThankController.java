package jee.memory.controller;

import jee.memory.model.ThinkTank;
import jee.memory.service.ThinkTankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping ("/thinktank")
public class ThinkThankController {
    @Autowired
    ThinkTankService thinkTankService;

    @GetMapping
    public String showAllInThinkTank(Model model) {
        log.info (" GetMapping showAllInThinkTank");
        model.addAttribute("thinktanks", thinkTankService.findAll());
        return "thinktanks";
    }

    @GetMapping("/thinktankslistview")
    public String showAllThinkingInAnotherView (Model model) {
        log.info (" GetMapping showAllInAnotherView");
        model.addAttribute("thinktanks", thinkTankService.findAll());
        return "thinktankslistview";
    }

    @GetMapping ("/update/{id}")
    public String editThinkTank (@PathVariable long id, Model model) {
        log.info (" GetMapping editThinkTank");
        ThinkTank thinkTank = thinkTankService.findById(id);
        model.addAttribute("thinkTank", thinkTank);
        return "updatethinktank";
    }

    @PostMapping ("/update/{id}")
    public String updateThinkTank (@PathVariable long id, @ModelAttribute ("thinkTank") ThinkTank thinkTank, Model model) {
        log.info (" GetMapping editThinkTank");
        ThinkTank thinkTankU = thinkTankService.findById(id);
        thinkTank.setThinkId(id);
        thinkTankU.setDescription(thinkTank.getDescription());
        thinkTankU.setTitle(thinkTank.getTitle());
        model.addAttribute("thinkTank", thinkTank);
        return "redirect:/thinktank";
    }

    @GetMapping ("/add")
    public String addThinkTank (Model model) {
        log.info (" GetMapping addThinkTank");
        ThinkTank thinkTank = new ThinkTank();
        model.addAttribute("thinkTank", thinkTank);
        return "addthinktank";
    }

    @PostMapping("/add")
    public String addNewThank (@ModelAttribute ThinkTank thinkTank) {
        log.info (" GetMapping addNewThank");
        thinkTankService.create(thinkTank);
        return "redirect:/thinktank";
    }

    @GetMapping("/delete/{id}")
    public String deleteFromThinkThank (@PathVariable long id) {
        log.info (" GetMapping delete Think tank");
        thinkTankService.deleteById(id);
        return "redirect:/thinktank";
    }
}
