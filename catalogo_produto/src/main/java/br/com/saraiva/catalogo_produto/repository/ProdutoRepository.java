package br.com.saraiva.catalogo_produto.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.saraiva.catalogo_produto.domain.ProdutoDomain;

/**
 * ProdutoRepository
 */
public interface ProdutoRepository extends CrudRepository<ProdutoDomain, Long> {
}