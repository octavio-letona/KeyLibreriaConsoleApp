/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.key.model;

//POJO: Nombre, atributos, constructores, getters y setters
public class Cliente {

    /*
    create table clientes(
        cui bigint primary key,
        nombre_cliente varchar(100),
        apellido_cliente varchar(100),
        correo_electronico varchar(100)
    );
     */

    //atributos de clase
    long cui;
    String nombre;
    String apellido;
    String correoElectronico;

    //constructores: asignación de datos, instanciar objetos
    //vacio
    public Cliente() {
    }
    //lleno o con parametros
    public Cliente(long cui, String nombre, String apellido, String correoElectronico) {
        this.cui = cui;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
    }
    //personalizador
    
    
    //getter and setters

    public long getCui() {
        return cui;
    }

    public void setCui(long cui) {
        this.cui = cui;
    }

    public String getNombre() {        
        return nombre;
    }

    public void setNombre(String nombre) {
        //formatear a Mayusucual
        String nombreMayusculas = nombre.toUpperCase();
        //formatear a Inicia con Mayusuculas
        this.nombre = nombreMayusculas;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    
}