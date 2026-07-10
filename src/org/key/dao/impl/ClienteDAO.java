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
public interface ClienteDAO {
    //firmas de metodos
    //CRUD
    boolean insertar(Cliente cliente);
    List<Cliente> listar();
    Cliente buscar(long cui);
    boolean crear(Cliente cliente);
    List<Cliente> listarTodos();
    Cliente buscarPorId(long cui);
    boolean actualizar(Cliente cliente);
}