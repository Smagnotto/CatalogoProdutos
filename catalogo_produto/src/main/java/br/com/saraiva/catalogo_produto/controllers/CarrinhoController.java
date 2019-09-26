
package br.com.saraiva.catalogo_produto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.saraiva.catalogo_produto.config.ApplicationProperties;
import br.com.saraiva.catalogo_produto.domain.ProdutoDomain;
import br.com.saraiva.catalogo_produto.service.CarrinhoService;

/**
 * CarrinhoController
 */
@RestController
@RequestMapping(path = "/carrinho")
public class CarrinhoController {

    private String url = ApplicationProperties.INSTANCE.getUrlServiceCarrinho();

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/adicionar/produto/{id}/quantidade/{quantidade}")
    public ResponseEntity<String> adicionarProdutoCarrinho (@PathVariable long id, @PathVariable int quantidade) {
        try {   
            carrinhoService.reservarProduto(id, quantidade);
            
            ProdutoDomain produto = new ProdutoDomain();
            produto.setId(id);
            produto.setQuantidade(quantidade);

            RestTemplate rest = new RestTemplate();
            ResponseEntity<String> response = rest.postForEntity(url + "/", produto, String.class);

            return response;
        }
        catch (Exception ex) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }  
}