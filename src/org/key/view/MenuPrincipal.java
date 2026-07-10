/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.key.view;


import java.util.Scanner;
import org.octavioletona.controller.ClienteController;

public class MenuPrincipal {
    Scanner leer = new Scanner(System.in);
    
    public void iniciar(){
        int opcion = 0;
        do {
            System.out.println("Bienvenido, selecciones una opcion!");
            System.out.println("1. Modulo Cliente");
            System.out.println("2. Modulo Autores");
            System.out.println("3. Modulo Categorias");
            System.out.println("5. Modulo Editoriales");
            System.out.println("4. Salir");
            opcion = Integer.parseInt(leer.nextLine());
            
            switch (opcion) {
                case 1:
                        System.out.println("Cliente");
                        ClienteConsoleView vistaCliente = new ClienteConsoleView();
                        ClienteController controladorCliente = new ClienteController(vistaCliente);
                        controladorCliente.iniciar();
                    break;
                case 2:
                    System.out.println("Autor");
                    break;
                case 3:
                    System.out.println("Categoria");
                    break;
                case 4:
                    System.out.println("Adiós Vaquero!");
                    break;   
                case 5:
                    System.out.println("Editoriales");
                    break;
                default:
                    System.out.println("NO existe esta opción");
            }
            
        } while (opcion != 4);
    }
}