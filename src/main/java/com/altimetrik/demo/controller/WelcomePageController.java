package com.altimetrik.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author G13380
 */
@Controller
public class WelcomePageController {
    
    @GetMapping({"/", "/index"})
    public String welcome() {
        return "decode_vin";
    }

}
