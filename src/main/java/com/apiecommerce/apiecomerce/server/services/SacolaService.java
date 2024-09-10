package com.apiecommerce.apiecomerce.server.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiecommerce.apiecomerce.server.entities.Produtos;
import com.apiecommerce.apiecomerce.server.entities.Sacola;
import com.apiecommerce.apiecomerce.server.entities.Usuario;
import com.apiecommerce.apiecomerce.server.entities.DTO.AuthenticationDTO;
import com.apiecommerce.apiecomerce.server.entities.DTO.ProdutoDTO;
import com.apiecommerce.apiecomerce.server.entities.DTO.SacolaDTO;
import com.apiecommerce.apiecomerce.server.entities.DTO.SacolaProdutoDTO;
import com.apiecommerce.apiecomerce.server.interfaces.ProdutoRepository;
import com.apiecommerce.apiecomerce.server.interfaces.SacolaRepository;
import com.apiecommerce.apiecomerce.server.interfaces.UsuarioRepository;

@Service
public class SacolaService {

    @Autowired
    SacolaRepository sacolaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    public Double somaValorProdutos(SacolaDTO dto) {
        Sacola sacola = listarSacola(dto);
        var total = sacola.getProdutos().stream().mapToDouble(Produtos::getPreco).sum();
        sacola.setValorFinal(total);
        sacolaRepository.saveAndFlush(sacola);
        return sacola.getValorFinal();
    }

    public void somaQuantidadeProduto(int quantidade, Long id) {
        var quantidadeAtual = +quantidade;
        var produtoDB = produtoRepository.findById(id).get().getQuantidade();
        var sunFinal = produtoDB + quantidadeAtual;
        produtoRepository.findById(id).get().setQuantidade(sunFinal);
    }

    public void removeItem(ProdutoDTO produtoDTO) {
        produtoRepository.deleteById(produtoDTO.id());
    }

    public Sacola novaSacola(AuthenticationDTO dto) {
        var userBag = usuarioRepository.encontrarByUsername(dto.username());
        if (userBag.getSacola().isEmpty()) {
            Sacola newSacola = new Sacola();
            userBag.getSacola().add(newSacola);
            return newSacola;
        }
        return userBag.getSacola().iterator().next();
    }

    public Sacola iniciarNovaSacola(AuthenticationDTO authenticationDTO) {
        Sacola sacola = new Sacola();
        novaSacola(authenticationDTO);
        return sacola;
    }

    public Sacola adicionarProdutos(SacolaProdutoDTO dto) {
        Sacola sacola = sacolaRepository.findById(dto.id()).orElseThrow();
        Produtos produto = produtoRepository.findById(dto.produto()).orElseThrow();
        Usuario usuario = usuarioRepository.findById(dto.usuario()).orElseThrow();
        sacola.addProduto(produto);
        sacola.setUsuario(usuario);
        return sacolaRepository.saveAndFlush(sacola);
    }

    public List<Sacola> todasSacolas() {
        return sacolaRepository.findAll();
    }

    public Sacola listarSacola(SacolaDTO dto) {
        return sacolaRepository.findById(dto.id()).get();
    }

}
