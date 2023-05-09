package com.example.projetwebequiperendezmedicalespring.controlleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CliniqueController {

    @GetMapping("/cliniques")
    public String cliPage(){
        return "Vues/Clinique/clinique_index";
    }

    @GetMapping("/clilistpat")
    public String clilistPatPage(){
        return "Vues/Clinique/liste_patients";
    }

    @GetMapping("/clilistmed")
    public String clilistMedPage(){
        return "Vues/Clinique/liste_medecins";
    }

    @GetMapping("/clicompte")
    public String compteCliPage(){
        return "Vues/Clinique/compte_clinique";
    }

    @GetMapping("/histrdv")
    public String histrdvPage(){
        return "Vues/Clinique/historique_rdv";
    }

    @GetMapping("/modcli")
    public String modCliPage(){
        return "Vues/Clinique/modifier_clinique";
    }


}
