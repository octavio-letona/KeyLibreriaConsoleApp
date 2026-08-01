package org.key.dao.impl;

import java.util.ArrayList;
import org.key.util.Conexion;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.key.model.Categoria;

/**
 *
 * @author informatica
 */
public class CategoriaDAOImpl implements CategoriaDAO {

    @Override
    public List<Categoria> listarTodos() {
        //crear lista
        List<Categoria> categoria = new ArrayList<>();
        //crear nuestra consulta
        String consulta = "{call sp_listarcategorias()}";
        
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consultaCall = conexion.prepareCall(consulta);
                ResultSet tablaResultado = consultaCall.executeQuery();) {
            
            while (tablaResultado.next()) {
                categoria.add(new Categoria(
                        tablaResultado.getInt("id_categoria"),
                        tablaResultado.getString("nombre_categoria")
                ));
            }
        } catch (SQLException e) {
            System.err.print("Error al listar Categorias: " + e.getMessage());
        }
        
        //retornamos la lista
        return categoria;
    }

    @Override
    public boolean crear(Categoria categoria) {
        String consulta = "{call sp_insertarcategoria(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta)) {
            consultaCall.setString(1, categoria.getNombre_categoria());
            return consultaCall.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.print("Error al crear Categoria: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Categoria categoria) {
        String consulta = "{call sp_actualizarcategoria(?, ?)}";
        
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta)) {
            
            consultaCall.setInt(1, categoria.getId());
            consultaCall.setString(2, categoria.getNombre_categoria());
            
            return consultaCall.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.print("Error al actualizar Categoria: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String consulta = "{call sp_eliminarcategoria(?)}";
        
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta)) {
            
            consultaCall.setInt(1, id);
            
            return consultaCall.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.print("Error al eliminar Categoria: " + e.getMessage());
            return false;
        }
    }
      
    @Override   
    public Categoria buscarPorId(int id) {
        String consultaSQL = "{call sp_buscarcategoria(?)}";
        Categoria categoria = null;

        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consultaCall = conexion.prepareCall(consultaSQL)) {

            consultaCall.setInt(1, id);

            try (ResultSet tablaResultado = consultaCall.executeQuery()) {
                if (tablaResultado.next()) {
                    categoria = new Categoria(
                        tablaResultado.getInt("id_categoria"),
                        tablaResultado.getString("nombre_categoria")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar Categoria: " + e.getMessage());
        }

        return categoria;
    }
}
