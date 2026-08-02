/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.key.dao.impl;

import java.util.List;
import org.key.model.Cliente;

/**
 *
 * @author informatica
 */
//Interfaz  no se pude crear como objeto, contrato 
public interface ClienteDAO {
    //firmas de metodos
    //CRUD
    boolean eliminar(long cui);        
    boolean actualizar(Cliente cliente);
    boolean crear(Cliente cliente);    
    List<Cliente> listarTodos();
    Cliente buscarPorId(long cui);
}