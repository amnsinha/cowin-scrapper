package com.cowinscrapper.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapperController {

    @GetMapping("/scrappers")
    String all() {
        return "Hello";
    }


}
