/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.key.dao.impl;

import java.util.List;
import org.key.model.Editorial;

/**
 *
 * @author informatica
 */
// Interfaz no se puede crear como objeto, contrato
public interface EditorialDAO {
    // firmas de metodos
    // CRUD
    boolean eliminar(String nit);        
    boolean actualizar(Editorial editorial);
    boolean crear(Editorial editorial);    
    List<Editorial> listarTodos();
    Editorial buscarPorId(String nit);
}
