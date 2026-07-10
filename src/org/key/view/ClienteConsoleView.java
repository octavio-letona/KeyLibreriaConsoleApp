/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.key.view;

import java.util.List;
import java.util.Scanner;
import org.key.model.Cliente;

public class ClienteConsoleView {

    private final Scanner leer = new Scanner(System.in);

    //metodo para mostrar las opcion de este menu
    public int mostrarMenu() {
        int opcion = 0;
        // todo el menu
        System.out.println("--- GESTION DE CLIENTES ---");
        System.out.println("-1 CREAR nuevo Cliente ---");
        System.out.println("-2 LISTAR todos los Clientes ---");
        System.out.println("-3 BUSCAR Cliente por ID ---");
        System.out.println("-4 MODIFICAR Cliente ---");
        System.out.println("-5 ELIMINAR nuevo Cliente ---");
        System.out.println("-6 REGRESAR a menú PRIMCIPAL ---");
        System.out.print("SELECCIONE UNA OPCION -->");
        opcion = Integer.parseInt(leer.nextLine());
        return opcion;
    }

    public long solicitarCUI() {
        System.out.println("Ingrese el CUI del cliente: ");
        return Long.parseLong(leer.nextLine());
    }

    //nombreCliente
    public String solicitarNombreCliente() {
        String nombre;
        System.out.println("Ingres el NOMBRE del cliente");
        nombre = leer.nextLine();
        return nombre;
        //return leer.nextLine();
    }

    //apellidoCliente
    public String solicitarApellidoCliente() {
        System.out.println("Ingres el APELLIDO del cliente");
        return leer.nextLine();
    }

    //correoEctronico
    public String solicitarCorreoElectronico() {
        System.out.println("Ingres el CORREO electrónico del cliente");
        return leer.nextLine();
    }
    
    //mostrar el detalle de un CLIENTE
    public void mostrarCliente(Cliente cliente){
        System.out.println("--- DATOS DEL CLIENTE ---");
        System.out.println("CUI: " + cliente.getCui());
        System.out.println("NOMBRE: " + cliente.getNombre());
        System.out.println("APELLIDO: " + cliente.getApellido());
        System.out.println("CORREO E: "+ cliente.getCorreoElectronico());
    }
    
    //mostrar la lista de CLIENTES -- lista de objeto List<T>, ArrayList<Cliente>
    public void mostrarListaClientes(List<Cliente> clientes){
        System.out.println("--- LISTA DE CLIENTES ---");
        //tabla usando la propiedad %-[tamaño de columa]s
        System.out.printf("%-10s %-10s %-10s %-10s", "CUI","NOMBRE","APELLIDO","CORREO");
        
        for (Cliente cliente : clientes) {
            System.out.printf("%-10s %-10s %-10s %-10s",
                    cliente.getCui(), cliente.getNombre(), cliente.getApellido(), cliente.getCorreoElectronico());
        }
    }
    
    //para mostrar mensaje personalizado
    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
    
}