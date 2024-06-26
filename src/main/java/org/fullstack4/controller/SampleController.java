package org.fullstack4.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Controller
public class SampleController {
    @GetMapping("/hello")
    public void hello(Model model){
        model.addAttribute("msg", "Hello");
    }
    @GetMapping("/ex/ex1")
    public void ex1(Model model){
        List<String> list = Arrays.asList("AAA", "BBB", "CCC");
        model.addAttribute("list", list);
    }
    @GetMapping("/ex/ex2")
    public void ex2(Model model){
    }
    @GetMapping("/ex/ex3")
    public void ex3(Model model){
    }
}
