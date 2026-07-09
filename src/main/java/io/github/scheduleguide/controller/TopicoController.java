package io.github.scheduleguide.controller;

import java.util.ArrayList;
import java.util.List;

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
import io.github.scheduleguide.domain.Topico;
import io.github.scheduleguide.repository.TopicoRepository;
import io.github.scheduleguide.repository.CategoriaRepository;

/**
 * <i>Documentação da classe TopicoController</i>.
 * <p>
 * A classe TopicoController abrange o conjunto de operações sobre um Topico, do ponto de vista do servidor.
 * Seus métodos englobam o acesso ao banco de dados correspondente, garantindo persistência.
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * 
 * @see io.github.scheduleguide.domain.Topico
 */
@RestController
@RequestMapping("/interno/topicos")
public class TopicoController {
    /** Repositório CRUD para gestão de tópicos, responsável por implementar a persistência dos objetos. */
    @Autowired
    private TopicoRepository repoTopico;

    @Autowired
    private CategoriaRepository repoCategoria;
    
    /**
     * Retorna uma lista com cada {@link Topico} presente no banco de dados.
     * <p>
     * É possível filtrar os resultados por nome.
     * 
     * @param nome filtro de pesquisa a aplicar sobre os nomes dos tópicos
     * @return a lista de tópicos correspondentes à pesquisa
     */
    @GetMapping
    public List<Topico> getListaTopicos(@RequestParam(name="nome", defaultValue="") String nome) {
        List<Topico> listaTopicos = new ArrayList<>();
        Iterable<Topico> iterTopico = repoTopico.findAll();
        for (Topico t : iterTopico) {
            if (t.getNome().contains(nome))
                listaTopicos.add(t);
        }

        return listaTopicos;
    }

    /**
     * Retorna o {@link Topico} correspondente a um identificador, se existir.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @return o <code>Topico</code> correspondente
     * @throws ResponseStatusException se não há um <code>Topico</code> associado ao identificador
     */
    @GetMapping("/{id}")
    public Topico getTopico(@PathVariable(name="id") Long id) {
        if (!repoTopico.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return repoTopico.findById(id).orElseThrow();
    }

    /**
     * Atualiza os dados de um {@link Topico} a partir de seu identificador.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @param t o <code>Topico</code> com os dados atualizados
     * @return o <code>Topico</code> atualizado
     * @throws ResponseStatusException se não há um <code>Topico</code> associado ao identificador
     */
    @PutMapping("/{id}")
    public Topico setTopico(@PathVariable(name="id") Long id, @RequestBody Topico t) {
        if (!repoTopico.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    
        t.setId(id);
        repoTopico.save(t);
        return t;
    }

    /**
     * Remove um {@link Topico} do banco de dados.
     * <p>
     * Esta operação desencadeia a remoção de cada {@link Conteudo} associado ao <code>Topico</code>.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     */
    @DeleteMapping("/{id}")
    public void deleteTopico(@PathVariable(name="id") Long id) {
        if (repoTopico.existsById(id)) {
            Topico t = repoTopico.findById(id).orElseThrow();

            // deletar conteudos associados

            repoTopico.delete(t);
        }
    }

    /** 
     * Adiciona um novo {@link Topico} ao banco de dados.
     * <p>
     * Um identificador é atribuido automaticamente ao salvar.
     * 
     * @param t o novo <code>Topico</code>
     * @return o <code>Topico</code> adicionado, com seu identificador
     */
    @PostMapping
    public Topico postTopico(@RequestBody Topico t) {
        repoTopico.save(t);
        return t;
    }

    /**
     * Retorna a lista de {@link Topico} pertencentes a uma categoria, se existir.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @return a lista de <code>Topico</code> correspondente
     * @throws ResponseStatusException se não há um <code>Topico</code> associado ao identificador
     */
    @GetMapping("/lista/{id}")
    public List<Topico> getTopicoByCategoriaId(@PathVariable(name="id") Long id) {
        if (!repoCategoria.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return repoTopico.findByCategoriaId(id);
    }
}
