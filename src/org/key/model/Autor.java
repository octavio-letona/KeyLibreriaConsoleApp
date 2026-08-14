package org.key.model;
public class Autor {

    /*
    create table autores(
        id_autor int primary key auto_increment,
        nombre_autor varchar(100) not null,
        apellido_autor varchar(100) not null,
        nacionalidad varchar(100),
        biografia text
    );
     */

    //atributos de clase
    int idAutor;
    String nombreAutor;
    String apellidoAutor;
    String nacionalidad;
    String biografia;

    //constructores: asignación de datos, instanciar objetos
    //vacio
    public Autor() {
    }
    
    //lleno o con parametros
    public Autor(int idAutor, String nombreAutor, String apellidoAutor, String nacionalidad, String biografia) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.apellidoAutor = apellidoAutor;
        this.nacionalidad = nacionalidad;
        this.biografia = biografia;
    }
    
    //personalizador
    
    
    //getter and setters

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {        
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        //formatear a Mayusucual
        String nombreMayusculas = nombreAutor.toUpperCase();
        //formatear a Inicia con Mayusuculas
        this.nombreAutor = nombreMayusculas;
    }

    public String getApellidoAutor() {
        return apellidoAutor;
    }

    public void setApellidoAutor(String apellidoAutor) {
        this.apellidoAutor = apellidoAutor;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    
}
