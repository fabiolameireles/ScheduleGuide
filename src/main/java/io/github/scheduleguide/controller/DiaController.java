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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.scheduleguide.domain.Dia;
import io.github.scheduleguide.repository.DiaRepository;

/**
 * <i>Documentação da classe DiaController</i>.
 * <p>
 * A classe DiaController abrange o conjunto de operações sobre um Dia, do ponto de vista do servidor.
 * Seus métodos englobam o acesso ao banco de dados correspondente, garantindo persistência.
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * 
 * @see io.github.scheduleguide.domain.Dia
 */
@RestController
@RequestMapping("/interno/dias")
public class DiaController {
    /** Repositório CRUD para gestão de dias, responsável por implementar a persistência dos objetos. */
    @Autowired
    private DiaRepository repoDia;

    /**
     * Retorna uma lista com cada {@link Dia} presente no banco de dados.
     * 
     * @return a lista de dias correspondentes à pesquisa
     */
    @GetMapping
    public List<Dia> getListaDias(){
        List<Dia> listaDias = new ArrayList<Dia>();

        Iterable<Dia> iterDia = repoDia.findAll();
        for (Dia d : iterDia) {
            listaDias.add(d);
        }

        return listaDias;
    }

    /** 
     * Adiciona um novo {@link Dia} ao banco de dados.
     * <p>
     * Um identificador é atribuido automaticamente ao salvar.
     * 
     * @param dia o novo <code>Dia</code>
     * @return o <code>Dia</code> adicionado, com seu identificador
     */
    @PostMapping
    public Dia postDia(@RequestBody Dia dia){
        repoDia.save(dia);
        return(dia);
    }

    /**
     * Retorna o {@link Dia} correspondente a um identificador, se existir.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @return o <code>Dia</code> correspondente
     * @throws ResponseStatusException se não há um <code>Dia</code> associada ao identificador
     */
    @GetMapping("/{id}")
    public Dia getDia(@PathVariable(name="id") Long id){
        if (!repoDia.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return repoDia.findById(id).orElseThrow();
    }

    /**
     * Atualiza os dados de um {@link Dia} a partir de seu identificador.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @param dia o <code>Dia</code> com os dados atualizados
     * @return o <code>Dia</code> atualizado
     * @throws ResponseStatusException se não há um <code>Dia</code> associada ao identificador
     */
    @PutMapping("/{id}")
    public Dia putDia(@PathVariable(name="id") Long id, @RequestBody Dia dia) {
        if (!repoDia.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    
        dia.setId(id);
        repoDia.save(dia);
        return dia;
    }

    /**
     * Remove um {@link Dia} do banco de dados.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     */
    @DeleteMapping("/{id}")
    public void deleteDia(@PathVariable(name="id") Long id) {
        if (repoDia.existsById(id)) {
            Dia dia = repoDia.findById(id).orElseThrow();

            repoDia.delete(dia);
        }
    }

    
}
