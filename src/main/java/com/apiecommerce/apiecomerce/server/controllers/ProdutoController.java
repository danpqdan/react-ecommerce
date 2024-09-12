package com.apiecommerce.apiecomerce.server.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiecommerce.apiecomerce.server.entities.Produtos;
import com.apiecommerce.apiecomerce.server.entities.DTO.ProdutoDTO;
import com.apiecommerce.apiecomerce.server.entities.DTO.QuantidadeProdutoDTO;
import com.apiecommerce.apiecomerce.server.interfaces.ProdutoRepository;

@RestController
@RequestMapping("/api")
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public List<Produtos> listarProduto() {
        return produtoRepository.findAll();
    }

    @PostMapping("/produtos")
    public ResponseEntity adicionarProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produtos produtos = new Produtos();
        produtos.setNome(produtoDTO.nome());
        produtos.setPreco(produtoDTO.preco());
        return ResponseEntity.ok().body(produtoRepository.save(produtos));
    }

    @PostMapping("/produtos/quantidade")
    public ResponseEntity<List<Produtos>> atualizarQuantidadeProdutos(
            @RequestBody QuantidadeProdutoDTO quantidadeDeProdutos) {
        var a = produtoRepository.buscarProdutoPorQuantidade(quantidadeDeProdutos.quantidadeProdutos());
        List<Produtos> listaProduto = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            a.get(i).somaQuantidade(1);
            listaProduto.add(a.get(i));
        }
        produtoRepository.saveAllAndFlush(listaProduto);
        return ResponseEntity.ok().body(listaProduto);
    }
}
