package com.apiecommerce.apiecomerce.server.services;

import java.util.Collections;
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
            Sacola sacola2 = new Sacola();
            sacola2.setUsuario(usuario);
            usuario.setSacola(sacola2);
            sacolaRepository.save(sacola2);
            usuarioRepository.saveAndFlush(usuario);
            return sacola2;
        }
        // if(usuario == null){} necessario tratar exceção de falha de usuario
        return sacola;
    }

    public Sacola adicionarProdutos(SacolaDTO dto) {
        Sacola sacola = sacolaRepository.findById(dto.getId()).get();
        Produtos produto = produtoRepository.findById(dto.getProduto()).get();
        sacola.setProdutos(Collections.singleton(produto));
        return sacolaRepository.save(sacola);
    }

    public List<Sacola> todasSacolas() {
        return sacolaRepository.findAll();
    }

    public Sacola listarSacola(SacolaProdutoDTO sacolaProdutoDTO) {
        return sacolaRepository.findById(sacolaProdutoDTO.idSacola()).get();
    }

}
