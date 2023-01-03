package kr.study.spring.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/sample")
    public String[] getMessages() {
        return new String[] {"apple", "banana", "cherry"};
    }
}
