package com.apiecommerce.apiecomerce.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiecommerce.apiecomerce.server.entities.Produtos;
import com.apiecommerce.apiecomerce.server.entities.DTO.SacolaProdutoDTO;
import com.apiecommerce.apiecomerce.server.interfaces.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public Produtos retornarUmProduto(SacolaProdutoDTO sacolaProdutoDTO) {
        return produtoRepository.findById(sacolaProdutoDTO.getProdutoID()).orElseThrow();
    }

}
