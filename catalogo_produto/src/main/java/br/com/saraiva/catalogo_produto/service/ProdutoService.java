package br.com.saraiva.catalogo_produto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.saraiva.catalogo_produto.domain.ProdutoDomain;
import br.com.saraiva.catalogo_produto.repository.ProdutoRepository;

/**
 * ProdutoService
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoDomain> getAll() {
        List<ProdutoDomain> produtos = new ArrayList<>();
        repository.findAll().forEach(produto -> produtos.add(produto));
        return produtos;
    }

    public ProdutoDomain getById(long id) {
        return repository.findById(id).orElse(null);
    }

    public ProdutoDomain addNewProduto(ProdutoDomain produto) {
        return repository.save(produto);
    }

    public void updateProduto(ProdutoDomain produto) {
        repository.save(produto);
    }

    public void deleteProduto (ProdutoDomain produto) {
        repository.delete(produto);
    }
}