/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.key.view;

import java.util.List;
import java.util.Scanner;
import org.key.model.Editorial;

public class EditorialConsoleView {

    private final Scanner leer = new Scanner(System.in);

    // método para mostrar las opciones de este menú
    public int mostrarMenu() {
        int opcion = 0;
        // todo el menú
        System.out.println("--- GESTIÓN DE EDITORIALES ---");
        System.out.println("-1 CREAR nueva Editorial ---");
        System.out.println("-2 LISTAR todas las Editoriales ---");
        System.out.println("-3 BUSCAR Editorial por NIT ---");
        System.out.println("-4 MODIFICAR Editorial ---");
        System.out.println("-5 ELIMINAR Editorial ---");
        System.out.println("-6 REGRESAR a menú PRINCIPAL ---");
        System.out.print("SELECCIONE UNA OPCIÓN --> ");
        opcion = Integer.parseInt(leer.nextLine());
        return opcion;
    }

    public String solicitarNIT() {
        System.out.println("Ingrese el NIT de la editorial: ");
        return leer.nextLine();
    }

    // nombreEditorial
    public String solicitarNombreEditorial() {
        System.out.println("Ingrese el NOMBRE de la editorial: ");
        return leer.nextLine();
    }

    // telefonoEditorial
    public String solicitarTelefonoEditorial() {
        System.out.println("Ingrese el TELÉFONO de la editorial: ");
        return leer.nextLine();
    }

    // direccionEditorial
    public String solicitarDireccionEditorial() {
        System.out.println("Ingrese la DIRECCIÓN de la editorial: ");
        return leer.nextLine();
    }

    // mostrar el detalle de una EDITORIAL
    public void mostrarEditorial(Editorial editorial) {
        System.out.println("--- DATOS DE LA EDITORIAL ---");
        System.out.println("NIT: " + editorial.getNit());
        System.out.println("NOMBRE: " + editorial.getNombreEditorial());
        System.out.println("TELÉFONO: " + editorial.getTelefonoEditorial());
        System.out.println("DIRECCIÓN: " + editorial.getDireccionEditorial());
    }

    // mostrar la lista de EDITORIALES -- lista de objeto List<Editorial>
    public void mostrarListaEditoriales(List<Editorial> editoriales) {
        System.out.println("--- LISTA DE EDITORIALES ---");
        // tabla usando la propiedad %-[tamaño de columna]s
        System.out.printf("%-15s %-25s %-15s %-30s\n", "NIT", "NOMBRE", "TELÉFONO", "DIRECCIÓN");

        for (Editorial editorial : editoriales) {
            System.out.printf("%-15s %-25s %-15s %-30s\n",
                    editorial.getNit(),
                    editorial.getNombreEditorial(),
                    editorial.getTelefonoEditorial(),
                    editorial.getDireccionEditorial());
        }
        System.out.println("LISTA DE EDITORIALES FINALIZADA");
    }

    // para mostrar mensaje personalizado
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

public boolean confirmarEliminacion() {
        System.out.print("¿Está seguro de que desea eliminar esta editorial? (S/N): ");
        String respuesta = scanner.nextLine().trim();
        return respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("SI");
    }

    public Editorial solicitarDatosNuevaEditorial() {
        System.out.println("\n=== REGISTRAR NUEVA EDITORIAL ===");
        
        System.out.print("Ingrese el NIT: ");
        String nit = scanner.nextLine().trim();

        System.out.print("Ingrese el nombre de la editorial: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine().trim();

        System.out.print("Ingrese la dirección: ");
        String direccion = scanner.nextLine().trim();

        // Se retorna el nuevo objeto Editorial con los datos ingresados
        return new Editorial(nit, nombre, telefono, direccion);
    }

    public Editorial solicitarDatosActualizarEditorial(Editorial editorialExistente) {
        System.out.println("\n=== ACTUALIZAR EDITORIAL ===");
        System.out.println("NIT: " + editorialExistente.getNit() + " (No modificable)");

        System.out.print("Nuevo nombre [" + editorialExistente.getNombreEditorial() + "] (Presione Enter para mantener): ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            nombre = editorialExistente.getNombreEditorial();
        }

        System.out.print("Nuevo teléfono [" + editorialExistente.getTelefonoEditorial() + "] (Presione Enter para mantener): ");
        String telefono = scanner.nextLine().trim();
        if (telefono.isEmpty()) {
            telefono = editorialExistente.getTelefonoEditorial();
        }

        System.out.print("Nueva dirección [" + editorialExistente.getDireccionEditorial() + "] (Presione Enter para mantener): ");
        String direccion = scanner.nextLine().trim();
        if (direccion.isEmpty()) {
            direccion = editorialExistente.getDireccionEditorial();
        }

        return new Editorial(editorialExistente.getNit(), nombre, telefono, direccion);
    }
}
