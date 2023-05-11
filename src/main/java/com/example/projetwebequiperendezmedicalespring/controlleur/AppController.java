package com.example.projetwebequiperendezmedicalespring.controlleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AppController {
    @GetMapping("/")
    public String homePage(){return "index";}

    @GetMapping("/login")
    public String logPage(){
        return "Vues/login";
    }

    @GetMapping("/creer")
    public String creerCompte(){return "Vues/creer_compte";}

    @GetMapping("/presentation")
    public String presentation(){
        return "Vues/presentation";
    }

    @GetMapping("admin_index")
    public String admin_index(){return "Vues/Admin/admin_index";}
}
