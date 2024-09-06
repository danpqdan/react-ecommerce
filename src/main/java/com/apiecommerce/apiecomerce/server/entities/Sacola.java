package com.apiecommerce.apiecomerce.server.entities;

import java.util.HashSet; // Import the HashSet class
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    Usuario usuario;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sacola_produtos", joinColumns = 
    @JoinColumn(name = "sacola_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
    Set<Produtos> produtos = new HashSet<>();

}
