package com.apiecommerce.apiecomerce.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiecommerce.apiecomerce.server.entities.DTO.AuthenticationDTO;
import com.apiecommerce.apiecomerce.server.entities.DTO.ProdutoDTO;
import com.apiecommerce.apiecomerce.server.entities.DTO.SacolaProdutoDTO;
import com.apiecommerce.apiecomerce.server.services.SacolaService;

@RestController
@RequestMapping("/api/sacola")
public class SacolaController {

    @Autowired
    SacolaService sacolaService;

    @GetMapping
    public ResponseEntity sacolas() {
        return ResponseEntity.ok().body(sacolaService.todasSacolas());
    }

    @PostMapping
    public ResponseEntity novaSacola(@RequestBody AuthenticationDTO authenticationDTO) {
        return ResponseEntity.ok().body(sacolaService.iniciarNovaSacola(authenticationDTO));
    }

    @PostMapping("/sacolaid") // Rota dinamica de acordo com a sacola do usuario *IMPLEMENTAR*
    public ResponseEntity adicionarProduto(@RequestBody Long id, @RequestBody int quantidade,
            @RequestBody SacolaProdutoDTO dto) {
        sacolaService.somaQuantidadeProduto(quantidade, id);
        return ResponseEntity.ok().body(sacolaService.adicionarProdutos(dto));
    }

}
