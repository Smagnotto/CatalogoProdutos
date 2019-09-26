package br.com.saraiva.catalogo_produto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.saraiva.catalogo_produto.domain.ProdutoDomain;
import br.com.saraiva.catalogo_produto.repository.ProdutoRepository;

/**
 * CarrinhoService
 */
@Service
public class CarrinhoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoDomain reservarProduto(long id, int quantidade) throws Exception {
        ProdutoDomain produto = produtoRepository.findById(id).orElse(null);
        if (produto == null || quantidade > produto.getQuantidadeEstoque()) {
            throw new Exception();
        }else {
            try {
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);                    return produtoRepository.save(produto);
            }
            catch (Exception ex) {
                throw ex;
            }
        }        
    }
}