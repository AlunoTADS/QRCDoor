package br.ufpr.qrcdoor.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Lucas
 */
@Controller
public class Index {
    @RequestMapping("/")
    public String index(Map<String, Object> model) {        
        return "index";
    }
}
