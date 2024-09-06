package com.apiecommerce.apiecomerce.server.entities;

import java.util.HashSet; // Import the HashSet class
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sacola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonBackReference  // Esse lado será ignorado na serialização
    Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sacola_produtos", joinColumns = @JoinColumn(name = "sacola_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
    Set<Produtos> produtos = new HashSet<>();

    public void addProduto(Produtos produtos){
        this.produtos.add(produtos);
    }

}
