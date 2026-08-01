/**
 *
 * @author Juan Carlos Estrada
 */
package org.key.model;

//POJO: Nombre, atributos, constructores, getters y setters
public class Categoria {



    //atributos de clase
    int Id;
    String nombre_categoria;


    //constructores: asignación de datos, instanciar objetos
    //vacio
    public Categoria() {
    }
    //lleno o con parametros
    public Categoria(int Id, String nombre_categoria) {
        this.Id = Id;
        this.nombre_categoria = nombre_categoria;

    }
    //personalizador
    
    
    //getter and setters

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
}
