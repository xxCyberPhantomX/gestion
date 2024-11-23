package proyect.gestioncomputadoras;

import modelo.computadoras;
import servicio.ComputadoraDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ComputadoraDAO dao = new ComputadoraDAO();
        Scanner scanner = new Scanner(System.in);

        try {
            // Ejemplo de insertar
            System.out.println("Insertar computadora:");
            computadoras nueva = new computadoras(0, "Laptop Dell", 1200.50, 10, "Dell");
            dao.insertar(nueva);
            System.out.println("Computadora insertada.");

            // Ejemplo de listar
            System.out.println("Listado de computadoras:");
            dao.listar().forEach(System.out::println);

            // Ejemplo de actualizar
            System.out.println("Actualizar computadora:");
            nueva.setPrecio(1100.00);
            dao.actualizar(nueva);
            System.out.println("Computadora actualizada.");

            // Ejemplo de eliminar
            System.out.println("Eliminar computadora:");
            dao.eliminar(nueva.getId());
            System.out.println("Computadora eliminada.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
