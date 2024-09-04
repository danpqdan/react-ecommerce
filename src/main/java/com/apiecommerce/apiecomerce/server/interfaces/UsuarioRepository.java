package com.apiecommerce.apiecomerce.server.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.apiecommerce.apiecomerce.server.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    UserDetails findByUsername(String username);

    //Optional<Usuario> loadByUsername(String username);
    
}
