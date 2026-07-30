/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.octavioletona.controller;

import org.key.dao.impl.EditorialDAO;
import org.key.dao.impl.EditorialDAOImpl;
import org.key.model.Editorial;
import org.key.view.EditorialConsoleView;

public class EditorialController {

    private final EditorialDAO dao;
    private final EditorialConsoleView vista;

    public EditorialController(EditorialConsoleView vista) {
        this.dao = new EditorialDAOImpl();
        this.vista = vista;
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    crear();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    modificar();
                    break;
                case 5:
                    eliminar();
                    break;
                case 6:
                    vista.mostrarMensaje("Regresando al menú principal...");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 6);
    }

    private void crear() {
        Editorial nuevaEditorial = vista.solicitarDatosNuevaEditorial();
        
        // Validamos si la editorial ya existe por NIT
        if (dao.buscarPorId(nuevaEditorial.getNit()) != null) {
            vista.mostrarMensaje("Error: Ya existe una editorial registrada con el NIT: " + nuevaEditorial.getNit());
            return;
        }

        if (dao.crear(nuevaEditorial)) {
            vista.mostrarMensaje("Editorial registrada exitosamente.");
        } else {
            vista.mostrarMensaje("Error al intentar registrar la editorial.");
        }
    }

    private void listar() {
        vista.mostrarListaEditoriales(dao.listarTodos());
    }

    private void buscar() {
        String nit = vista.solicitarNIT();
        Editorial editorial = dao.buscarPorId(nit);
        if (editorial != null) {
            vista.mostrarEditorial(editorial);
        } else {
            vista.mostrarMensaje("Editorial no encontrada con el NIT: " + nit);
        }
    }

    private void modificar() {
        String nit = vista.solicitarNIT();
        Editorial editorialExistente = dao.buscarPorId(nit);

        if (editorialExistente == null) {
            vista.mostrarMensaje("No se encontró ninguna editorial registrada con el NIT: " + nit);
            return;
        }

        // Se le pasa la editorial existente a la vista para solicitar los nuevos datos
        Editorial editorialActualizada = vista.solicitarDatosActualizarEditorial(editorialExistente);

        if (dao.actualizar(editorialActualizada)) {
            vista.mostrarMensaje("Editorial actualizada exitosamente.");
        } else {
            vista.mostrarMensaje("Error al intentar actualizar la editorial.");
        }
    }

    private void eliminar() {
        String nit = vista.solicitarNIT();
        Editorial editorial = dao.buscarPorId(nit);

        if (editorial == null) {
            vista.mostrarMensaje("No se encontró ninguna editorial con el NIT: " + nit);
            return;
        }

        if (vista.confirmarEliminacion()) {
            if (dao.eliminar(nit)) {
                vista.mostrarMensaje("Editorial eliminada exitosamente.");
            } else {
                vista.mostrarMensaje("Error al intentar eliminar la editorial.");
            }
        }
    }
}
