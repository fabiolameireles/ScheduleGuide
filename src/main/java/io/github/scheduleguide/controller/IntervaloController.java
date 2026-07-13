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

import io.github.scheduleguide.domain.Intervalo;
import io.github.scheduleguide.repository.IntervaloRepository;

/**
 * <i>Documentação da classe IntervaloController</i>.
 * <p>
 * A classe DiaController abrange o conjunto de operações sobre um Intervalo, do ponto de vista do servidor.
 * Seus métodos englobam o acesso ao banco de dados correspondente, garantindo persistência.
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * 
 * @see io.github.scheduleguide.domain.Intervalo
 */
@RestController
@RequestMapping("/interno/intervalos")
public class IntervaloController {
    /** Repositório CRUD para gestão de dias, responsável por implementar a persistência dos objetos. */
    @Autowired
    private IntervaloRepository repoIntervalo;

    /**
     * Retorna uma lista com cada {@link Intervalo} presente no banco de dados.
     * <p>
     * É possível filtrar os resultados por nome.
     * 
     * @param nome filtro de pesquisa a aplicar sobre os nomes dos intervalos
     * @return a lista de intervalos correspondentes à pesquisa
     */
    @GetMapping
    public List<Intervalo> getListaIntervalos(@RequestParam(name="nome", defaultValue="") String nome){
        List<Intervalo> listaIntervalos = new ArrayList<Intervalo>();

        Iterable<Intervalo> iterIntervalo = repoIntervalo.findAll();
        for (Intervalo i : iterIntervalo) {
            if (i.getNome().contains(nome)){
                listaIntervalos.add(i);
            }
        }

        return listaIntervalos;
    }

    /** 
     * Adiciona um novo {@link Intervalo} ao banco de dados.
     * <p>
     * Um identificador é atribuido automaticamente ao salvar.
     * 
     * @param i o novo <code>Intervalo</code>
     * @return o <code>Intervalo</code> adicionado, com seu identificador
     */
    @PostMapping
    public Intervalo postIntervalo(@RequestBody Intervalo i){
        repoIntervalo.save(i);
        return(i);
    }

    /**
     * Retorna o {@link Intervalo} correspondente a um identificador, se existir.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @return o <code>Intervalo</code> correspondente
     * @throws ResponseStatusException se não há um <code>Intervalo</code> associada ao identificador
     */
    @GetMapping("/{id}")
    public Intervalo getIntervalo(@PathVariable(name="id") Long id){
        if (!repoIntervalo.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return repoIntervalo.findById(id).orElseThrow();
    }

    /**
     * Atualiza os dados de um {@link Intervalo} a partir de seu identificador.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @param i o <code>Intervalo</code> com os dados atualizados
     * @return o <code>Intervalo</code> atualizado
     * @throws ResponseStatusException se não há um <code>Intervalo</code> associada ao identificador
     */
    @PutMapping("/{id}")
    public Intervalo putIntervalo(@PathVariable(name="id") Long id, @RequestBody Intervalo i) {
        if (!repoIntervalo.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    
        i.setId(id);
        repoIntervalo.save(i);
        return i;
    }

    /**
     * Remove um {@link Intervalo} do banco de dados.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     */
    @DeleteMapping("/{id}")
    public void deleteIntervalo(@PathVariable(name="id") Long id) {
        if (repoIntervalo.existsById(id)) {
            Intervalo i = repoIntervalo.findById(id).orElseThrow();

            repoIntervalo.delete(i);
        }
    }
}
