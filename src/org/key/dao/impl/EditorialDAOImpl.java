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

import org.key.model.Editorial;
import org.key.util.Conexion;

/**
 *
 * @author informatica
 */
public class EditorialDAOImpl implements EditorialDAO {

    @Override
    public List<Editorial> listarTodos() {
        // crear lista
        List<Editorial> editoriales = new ArrayList<>();
        // crear nuestra consulta
        String consulta = "{call sp_listareditoriales()}";
        
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta);
             ResultSet tablaResultado = consultaCall.executeQuery()) {
            
            while (tablaResultado.next()) {
                editoriales.add(new Editorial(
                        tablaResultado.getString("nit"),
                        tablaResultado.getString("nombre_editorial"),
                        tablaResultado.getString("telefono_editorial"),
                        tablaResultado.getString("direccion_editorial")
                ));
            }
        } catch (SQLException e) {
            System.err.print("Error al listar Editoriales: " + e.getMessage());
        }
        
        // retornamos la lista
        return editoriales;
    }

    @Override
    public boolean crear(Editorial editorial) {
         String consulta = "{call sp_insertareditorial(?, ?, ?, ?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta)) {
            consultaCall.setString(1, editorial.getNit());
            consultaCall.setString(2, editorial.getNombreEditorial());
            consultaCall.setString(3, editorial.getTelefonoEditorial());
            consultaCall.setString(4, editorial.getDireccionEditorial());
            return consultaCall.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.print("Error al crear Editorial: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Editorial buscarPorId(String nit) {
        Editorial editorial = new Editorial();

        // consulta
        String consultaSQL = "{call sp_buscareditorial(?)}";
        
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consultaCall = conexion.prepareCall(consultaSQL)) {
            
            consultaCall.setString(1, nit);
            ResultSet tablaResultado = consultaCall.executeQuery();
            
            if (tablaResultado.next()) {
                editorial.setNit(tablaResultado.getString("nit"));
                editorial.setNombreEditorial(tablaResultado.getString("nombre_editorial"));
                editorial.setTelefonoEditorial(tablaResultado.getString("telefono_editorial"));
                editorial.setDireccionEditorial(tablaResultado.getString("direccion_editorial"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar Editorial: " + e.getMessage());
        }
        
        // retornamos el objeto
        return editorial;
    }

    @Override
    public boolean actualizar(Editorial editorial) {
        return false;
    }

    @Override
    public boolean eliminar(String nit) {
        return false;
    }
}