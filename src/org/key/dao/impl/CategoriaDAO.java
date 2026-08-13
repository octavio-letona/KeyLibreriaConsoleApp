
package org.key.dao.impl;

import java.util.List;
import org.key.model.Categoria;

/**
 *
 * @author informatica
 */
//Interfaz  no se pude crear como objeto, contrato 
public interface CategoriaDAO {
    //firmas de metodos
    //CRUD
    boolean eliminar(int Id);        
    boolean actualizar(Categoria categoria);
    boolean crear(Categoria categoria);    
    List<Categoria> listarTodos();
    Categoria buscarPorId(int Id);
}
