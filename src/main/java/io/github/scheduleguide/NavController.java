package io.github.scheduleguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.scheduleguide.domain.Conteudo;
import io.github.scheduleguide.repository.TopicoRepository;
import io.github.scheduleguide.repository.ConteudoRepository;

@Controller
public class NavController {
    @Autowired
    private TopicoRepository repoTopico;

    @Autowired
    private ConteudoRepository repoConteudo;

    @RequestMapping("/")
    public String navegarParaIndex() {
        return "index";
    }

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
    public String navegarParaEstudo(Model model) {
        model.addAttribute("listaTopicos", repoTopico.findAll());
        return "estudo";
    }

    @RequestMapping("/estudo/{id}")
    public String navegarParaEstudoDeConteudo(@PathVariable("id") Long id, Model model) {
        if (repoConteudo.existsById(id)){
            Conteudo cont = repoConteudo.findById(id).orElse(null);
            model.addAttribute("conteudo", cont);
            return "estudo-conteudo";
        } else {
            return "estudo";
        }
    }
}
