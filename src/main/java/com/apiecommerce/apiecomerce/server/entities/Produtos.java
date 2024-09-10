package com.apiecommerce.apiecomerce.server.entities;

import com.apiecommerce.apiecomerce.server.entities.DTO.ProdutoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    Float preco;
    int quantidade;

    public int sunProdutos() {
        if (this.quantidade == 0) {
            return quantidade = +1;
        }
        return quantidade += 1;
    }

}
