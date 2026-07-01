package io.github.scheduleguide;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavController {
    @RequestMapping("/topicos")
    public String navegarParaTopicos() {
        return "topicos";
    }

    @RequestMapping("/cronograma")
    public String navegarParaCronograma() {
        return "cronograma";
    }

    @RequestMapping("/relatorio")
    public String navegarParaRelatorio() {
        return "relatorio";
    }

    @RequestMapping("/estudo")
    public String navegarParaEstudo() {
        return "estudo";
    }
}
