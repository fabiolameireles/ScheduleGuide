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

import io.github.scheduleguide.domain.Categoria;
import io.github.scheduleguide.repository.CategoriaRepository;

/**
 * <i>Documentação da classe CategoriaController</i>.
 * <p>
 * A classe CategoriaController abrange o conjunto de operações sobre uma Categoria, do ponto de vista do servidor.
 * Seus métodos englobam o acesso ao banco de dados correspondente, garantindo persistência.
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * 
 * @see io.github.scheduleguide.domain.Categoria
 */
@RestController
@RequestMapping("/interno/categorias")
public class CategoriaController {
    /** Repositório CRUD para gestão de categorias, responsável por implementar a persistência dos objetos. */
    @Autowired
    private CategoriaRepository repoCategoria;

    /**
     * Retorna uma lista com cada {@link Categoria} presente no banco de dados.
     * <p>
     * É possível filtrar os resultados por nome.
     * 
     * @param nome filtro de pesquisa a aplicar sobre os nomes das categorias
     * @return a lista de categorias correspondentes à pesquisa
     */
    @GetMapping
    public List<Categoria> getListaCategorias(@RequestParam(name="nome", defaultValue="") String nome){
        List<Categoria> listaCategorias = new ArrayList<Categoria>();

        Iterable<Categoria> iterCategoria = repoCategoria.findAll();
        for (Categoria c : iterCategoria) {
            if (c.getNome().contains(nome)){
                listaCategorias.add(c);
            }
        }

        return listaCategorias;
    }

    /** 
     * Adiciona uma nova {@link Categoria} ao banco de dados.
     * <p>
     * Um identificador é atribuido automaticamente ao salvar.
     * 
     * @param cat a nova <code>Categoria</code>
     * @return a <code>Categoria</code> adicionada, com seu identificador
     */
    @PostMapping
    public Categoria postCategoria(@RequestBody Categoria cat){
        repoCategoria.save(cat);
        return(cat);
    }

    /**
     * Retorna a {@link Categoria} correspondente a um identificador, se existir.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @return a <code>Categoria</code> correspondente
     * @throws ResponseStatusException se não há uma <code>Categoria</code> associada ao identificador
     */
    @GetMapping("/{id}")
    public Categoria getCategoria(@PathVariable(name="id") Long id){
        if (!repoCategoria.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return repoCategoria.findById(id).orElseThrow();
    }

    /**
     * Atualiza os dados de uma {@link Categoria} a partir de seu identificador.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @param cat a <code>Categoria</code> com os dados atualizados
     * @return a <code>Categoria</code> atualizada
     * @throws ResponseStatusException se não há uma <code>Categoria</code> associada ao identificador
     */
    @PutMapping("/{id}")
    public Categoria setCategoria(@PathVariable(name="id") Long id, @RequestBody Categoria cat) {
        if (!repoCategoria.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    
        cat.setId(id);
        repoCategoria.save(cat);
        return cat;
    }

    /**
     * Remove uma {@link Categoria} do banco de dados.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     */
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable(name="id") Long id) {
        if (repoCategoria.existsById(id)) {
            Categoria cat = repoCategoria.findById(id).orElseThrow();

            repoCategoria.delete(cat);
        }
    }

    
}
