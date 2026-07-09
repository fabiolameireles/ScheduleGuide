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

import io.github.scheduleguide.domain.Material;
import io.github.scheduleguide.repository.MaterialRepository;

/**
 * <i>Documentação da classe MaterialController</i>.
 * <p>
 * A classe MaterialController abrange o conjunto de operações sobre um Material, do ponto de vista do servidor.
 * Seus métodos englobam o acesso ao banco de dados correspondente, garantindo persistência.
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * 
 * @see io.github.scheduleguide.domain.Material
 */
@RestController
@RequestMapping("/interno/materiais")
public class MaterialController {
    /** Repositório CRUD para gestão de materiais, responsável por implementar a persistência dos objetos. */
    @Autowired
    private MaterialRepository repoMaterial;
    
    /**
     * Retorna uma lista com cada {@link Material} presente no banco de dados.
     * <p>
     * É possível filtrar os resultados por nome.
     * 
     * @param nome filtro de pesquisa a aplicar sobre os nomes dos materiais
     * @return a lista de materiais correspondentes à pesquisa
     */
    @GetMapping
    public List<Material> getListaMateriais(@RequestParam(name="nome", defaultValue="") String nome) {
        List<Material> listaMateriais = new ArrayList<>();
        Iterable<Material> iterMaterial = repoMaterial.findAll();
        for (Material mat : iterMaterial) {
            if (mat.getNome().contains(nome))
                listaMateriais.add(mat);
        }

        return listaMateriais;
    }

    /** 
     * Adiciona um novo {@link Material} ao banco de dados.
     * <p>
     * Um identificador é atribuido automaticamente ao salvar.
     * 
     * @param mat o novo <code>Material</code>
     * @return o <code>Material</code> adicionado, com seu identificador
     */
    @PostMapping
    public Material postMaterial(@RequestBody Material mat) {
        repoMaterial.save(mat);
        return mat;
    }

    /**
     * Retorna o {@link Material} correspondente a um identificador, se existir.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @return o <code>Material</code> correspondente
     * @throws ResponseStatusException se não há um <code>Material</code> associado ao identificador
     */
    @GetMapping("/{id}")
    public Material getMaterial(@PathVariable(name="id") Long id) {
        if (!repoMaterial.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else
            return repoMaterial.findById(id).orElseThrow();
    }

    /**
     * Atualiza os dados de um {@link Material} a partir de seu identificador.
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     * @param mat o <code>Material</code> com os dados atualizados
     * @return o <code>Material</code> atualizado
     * @throws ResponseStatusException se não há um <code>Material</code> associado ao identificador
     */
    @PutMapping("/{id}")
    public Material setMaterial(@PathVariable(name="id") Long id, @RequestBody Material mat) {
        if (!repoMaterial.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    
        mat.setId(id);
        repoMaterial.save(mat);
        return mat;
    }

    /**
     * Remove um {@link Material} do banco de dados.
     * <p>
     * 
     * @param id deve corresponder a uma entrada no banco de dados
     */
    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable(name="id") Long id) {
        if (repoMaterial.existsById(id)) {
            Material mat = repoMaterial.findById(id).orElseThrow();

            repoMaterial.delete(mat);
        }
    }
}
