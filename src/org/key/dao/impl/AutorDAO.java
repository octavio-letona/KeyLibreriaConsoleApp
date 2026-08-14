/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.key.dao.impl;

import java.util.List;
import org.key.model.Autor;
import org.key.model.Cliente;

/**
 *
 * @author sheyl
 */
public interface AutorDAO {

    boolean eliminar(int idAutor);        
    boolean actualizar(Autor autor);
    boolean crear(Autor autor);    
    List<Autor> listarTodos();
    Autor buscarPorId(int idAutor);
    
}