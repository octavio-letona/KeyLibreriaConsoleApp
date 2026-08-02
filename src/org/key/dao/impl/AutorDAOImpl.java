/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.key.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.key.model.Autor;
import org.key.util.Conexion;

/**
 *
 * @author informatica
 */
public class AutorDAOImpl implements AutorDAO {

    public List<Autor> listarTodos() {
        // crear lista
        List<Autor> autores = new ArrayList<>();
        // crear nuestra consulta
        String consulta = "{call sp_listarautores()}";
        
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta);
             ResultSet tablaResultado = consultaCall.executeQuery()) {
            
            while (tablaResultado.next()) {
                autores.add(new Autor(
                        tablaResultado.getInt("id_autor"),
                        tablaResultado.getString("nombre_autor"),
                        tablaResultado.getString("apellido_autor"),
                        tablaResultado.getString("nacionalidad"),
                        tablaResultado.getString("biografia")
                ));
            }
        } catch (SQLException e) {
            System.err.print("Error al listar Autores: " + e.getMessage());
        }
        
        // retornamos la lista
        return autores;
    }

    public boolean crear(Autor autor) {
        return false;
    }

    public Autor buscarPorId(int idAutor) {
        Autor autor = new Autor();

        // consulta
        String consultaSQL = "{call sp_buscarautor(?)}";
        
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consultaCall = conexion.prepareCall(consultaSQL)) {
            
            consultaCall.setInt(1, idAutor);
            ResultSet tablaResultado = consultaCall.executeQuery();
            
            if (tablaResultado.next()) {
                autor.setIdAutor(tablaResultado.getInt("id_autor"));
                autor.setNombreAutor(tablaResultado.getString("nombre_autor"));
                autor.setApellidoAutor(tablaResultado.getString("apellido_autor"));
                autor.setNacionalidad(tablaResultado.getString("nacionalidad"));
                autor.setBiografia(tablaResultado.getString("biografia"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar Autor: " + e.getMessage());
        }
        
        // retornamos el objeto
        return autor;
    }

    public boolean actualizar(Autor autor) {
        return false;
    }

    public boolean eliminar(int idAutor) {
        return false;
    }
}