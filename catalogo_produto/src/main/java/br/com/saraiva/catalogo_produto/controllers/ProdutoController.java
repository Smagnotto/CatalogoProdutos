package br.com.saraiva.catalogo_produto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.saraiva.catalogo_produto.domain.ProdutoDomain;
import br.com.saraiva.catalogo_produto.pojo.Produto;
import br.com.saraiva.catalogo_produto.service.ProdutoService;

/**
 * ProdutoController
 */
@RestController
@RequestMapping(path = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(path = "/")
    public List<ProdutoDomain> getAllProducts() {
        return produtoService.getAll();
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<ProdutoDomain> getProduct(@PathVariable long id) {
        ProdutoDomain produto = produtoService.getById(id);
        if (produto == null)
            return new ResponseEntity<>(produto, HttpStatus.NOT_FOUND);    
        else
            return new ResponseEntity<>(produto, HttpStatus.OK);    
    }

    @PostMapping(path = "/")
    public ResponseEntity<ProdutoDomain> addNewproduto(@RequestBody Produto pojoProduto) {
        ProdutoDomain produto = new ProdutoDomain();

        produto.setDescricao(pojoProduto.getDescricao());
        produto.setQuantidadeEstoque(pojoProduto.getQuantidadeEstoque());
        produto.setValor(pojoProduto.getValor());

        produto = produtoService.addNewProduto(produto);

        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    } 

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateproduto(@PathVariable long id, @RequestBody Produto pojoProduto) {
        ProdutoDomain produto = produtoService.getById(id);
        if (produto == null)
            return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);
        
        produto.setDescricao(pojoProduto.getDescricao());
        produto.setQuantidadeEstoque(pojoProduto.getQuantidadeEstoque());
        produto.setValor(pojoProduto.getValor());

        produtoService.updateProduto(produto);

        return new ResponseEntity<>("Produto atualizado com sucesso", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteproduto(@PathVariable long id) {
        ProdutoDomain produto = produtoService.getById(id);
        if (produto == null)
            return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);

        produtoService.deleteProduto(produto);
        return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
    }
}