package com.apiecommerce.apiecomerce.server.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiecommerce.apiecomerce.server.entities.Produtos;
import com.apiecommerce.apiecomerce.server.entities.Sacola;
import com.apiecommerce.apiecomerce.server.entities.Usuario;
import com.apiecommerce.apiecomerce.server.entities.DTO.AuthenticationDTO;
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

    public Sacola iniciarNovaSacola(AuthenticationDTO authenticationDTO) {
        Usuario usuario = usuarioRepository.encontrarByUsername(authenticationDTO.username());
        Sacola sacola = sacolaRepository.findByUsuario(usuario);
        if (usuario != null && sacola == null) {
            Sacola sacola1 = new Sacola();
            usuario.newSacola(sacola1);
            sacola1.setUsuario(usuario);
            sacolaRepository.save(sacola1);
            usuarioRepository.saveAndFlush(usuario);
            return sacola1;
        }
        // if(usuario == null){} necessario tratar exceção de falha de usuario
        return sacola;
    }

    public Sacola adicionarProdutos(SacolaProdutoDTO dto) {
        Sacola sacola = sacolaRepository.findById(dto.id()).orElseThrow();
        Produtos produto = produtoRepository.findById(dto.produto()).orElseThrow();
        Usuario usuario = usuarioRepository.findById(dto.usuario()).orElseThrow();
        if (sacola.getProdutos().isEmpty()) {
            sacola.setProdutos(new HashSet<>());
        }
        sacola.addProduto(produto);
        sacola.setUsuario(usuario);
        return sacolaRepository.saveAndFlush(sacola);
    }

    public List<Sacola> todasSacolas() {
        return sacolaRepository.findAll();
    }

    public Sacola listarSacola(SacolaProdutoDTO dto) {
        return sacolaRepository.findById(dto.id()).get();
    }

}
