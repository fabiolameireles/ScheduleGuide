package io.github.scheduleguide.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.scheduleguide.domain.Conteudo;
import io.github.scheduleguide.repository.ConteudoRepository;
import io.github.scheduleguide.repository.TopicoRepository;

/**
 * <i>Documentação da classe ConteudoController</i>.
 * <p>
 * A classe ConteudoController abrange o conjunto de operações sobre um Conteudo, do ponto de vista do servidor.
 * Seus métodos englobam o acesso ao banco de dados correspondente, garantindo persistência.
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * 
 * @see io.github.scheduleguide.domain.Conteudo
 */
@RestController
@RequestMapping("/interno/conteudos")
public class ConteudoController {
    /** Repositório CRUD para gestão de conteudos, responsável por implementar a persistência dos objetos. */
    @Autowired
    private ConteudoRepository repoConteudo;

    @Autowired
    private TopicoRepository repoTopico;

    /**
     * Retorna uma lista com cada {@link Conteudo} presente no banco de dados.
     * <p>
     * É possível filtrar os resultados por nome.
     * 
     * @param nome filtro de pesquisa a aplicar sobre os nomes dos conteudos
     * @return a lista de conteudos correspondentes à pesquisa
     */
    @GetMapping
    public List<Conteudo> getListaConteudos(@RequestParam(name="nome", defaultValue="") String nome){
        List<Conteudo> listaConteudos = new ArrayList<Conteudo>();

        Iterable<Conteudo> iterConteudo = repoConteudo.findAll();
        for (Conteudo cont : iterConteudo) {
            if (cont.getNome().contains(nome)){
                listaConteudos.add(cont);
            }
        }

        return listaConteudos;
    }

    /** 
     * Adiciona um novo {@link Conteudo} ao banco de dados.
     * <p>
     * Um identificador é atribuido automaticamente ao salvar.
     * 
     * @param cont o novo <code>Conteudo</code>
     * @return o <code>Conteudo</code> adicionado, com seu identificador
     */
    @PostMapping
    public Conteudo postConteudo(@RequestBody Conteudo cont){
        repoConteudo.save(cont);
        return(cont);
    }

    /**
     * Retorna o {@link Conteudo} correspondente a um identificador, se existir.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @return o <code>Conteudo</code> correspondente
     * @throws ResponseStatusException se não há um <code>Conteudo</code> associada ao identificador
     */
    @GetMapping("/{id}")
    public Conteudo getConteudo(@PathVariable(name="id") Long id){
        if (!repoConteudo.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return repoConteudo.findById(id).orElseThrow();
    }

    /**
     * Atualiza os dados de um {@link Conteudo} a partir de seu identificador.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @param cont o <code>Conteudo</code> com os dados atualizados
     * @return o <code>Conteudo</code> atualizado
     * @throws ResponseStatusException se não há um <code>Conteudo</code> associado ao identificador
     */
    @PutMapping("/{id}")
    public Conteudo setConteudo(@PathVariable(name="id") Long id, @RequestBody Conteudo cont) {
        if (!repoConteudo.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    
        cont.setId(id);
        repoConteudo.save(cont);
        return cont;
    }

    /**
     * Remove um {@link Cv} do banco de dados.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     */
    @DeleteMapping("/{id}")
    public void deleteConteudo(@PathVariable(name="id") Long id) {
        if (repoConteudo.existsById(id)) {
            Conteudo cont = repoConteudo.findById(id).orElseThrow();

            repoConteudo.delete(cont);
        }
    }

    /**
     * Retorna a lista de {@link Conteudo} pertencentes a um tópico, se existir.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @return a lista de <code>Conteudo</code> correspondente
     * @throws ResponseStatusException se não há um <code>Conteudo</code> associado ao identificador
     */
    @GetMapping("/lista/{id}")
    public List<Conteudo> getConteudoByTopicoId(@PathVariable(name="id") Long id) {
        if (!repoTopico.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return repoConteudo.findByTopicoId(id);
    }
}
