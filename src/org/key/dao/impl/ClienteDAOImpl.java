/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.key.dao.impl;

import java.util.ArrayList;
import org.key.model.Cliente;
import org.key.dao.impl.ClienteDAO;

import org.key.util.Conexion;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author informatica
 */
public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public List<Cliente> listarTodos() {
        //crear lista
        List<Cliente> clientes = new ArrayList<>();
        //crear nuestra consulta
        String consulta = "{call sp_listarclientes()}";
        
        try (Connection conexion = Conexion.getInstancia().conectar();
                CallableStatement consultaCall = conexion.prepareCall(consulta);
                ResultSet tablaResultado = consultaCall.executeQuery();) {
            
            while (tablaResultado.next()) {
                clientes.add(new Cliente(
                        tablaResultado.getLong("cui"),
                        tablaResultado.getString("nombre_cliente"),
                        tablaResultado.getString("apellido_cliente"),
                        tablaResultado.getString("correo_electronico")
                ));
            }
        } catch (SQLException e) {
            System.err.print("Error al listar Clientes: " + e.getMessage());
        }
        
        //retornamos la lista
        return clientes;
    }


    @Override
    public boolean crear(Cliente cliente) {
        return false;
    }


    @Override
    public Cliente buscarPorId(long cui) {
        // Se quitó el return null que bloqueaba el código 🌟
        Cliente cliente = new Cliente();

        //consulta
        String consultaSQL = "{call sp_buscarcliente(?)}";
        
        try (Connection conexion = Conexion.getInstancia().conectar(); 
             CallableStatement consultaCall = conexion.prepareCall(consultaSQL);) {
            
            consultaCall.setLong(1, cui);
            ResultSet tablaResultado = consultaCall.executeQuery();
            
            if (tablaResultado.next()) {
                cliente.setCui(tablaResultado.getLong("cui"));
                cliente.setNombre(tablaResultado.getString("nombre_cliente"));
                cliente.setApellido(tablaResultado.getString("apellido_cliente"));
                cliente.setCorreoElectronico(tablaResultado.getString("correo_electronico"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar Cliente: " + e.getMessage());
        }
        
        //retornamos el objeto
        return cliente;
    }

    @Override
    public boolean actualizar(Cliente cliente) {
        // Se cerró el método correctamente con su retorno temporal 🌟
        return false;
    }

    public boolean eliminar(long cui) {
        return false;
    }

    
}