package cn.qingmg.demoboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/test/{id}")
    public String test(@PathVariable String id) {
        return "test" + id;
    }
}