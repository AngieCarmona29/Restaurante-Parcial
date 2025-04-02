package com.restaurante.Application;
import com.restaurante.domain.Producto;
import java.util.Random;
import com.restaurante.usecase.GestorPedidos;
import java.util.*;

// Punto de entrada de la aplicación
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorPedidos gestor = new GestorPedidos();


        int opcion;
        do {
            System.out.println("\n Bienvenido a Las Delicias");
            System.out.println("1. Mostrar carta del restaurante");
            System.out.println("2. Crear un nuevo pedido para una mesa");
            System.out.println("3. Ver todos los pedidos en curso");
            System.out.println("4. Marcar un pedido como entregado");
            System.out.println("5. Cancelar un pedido (solo si no ha sido entregado)");
            System.out.println("6. Salir del sistema");
            System.out.print("Selecciona una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> gestor.mostrarCarta();
                case 2 -> {
                    System.out.print("Ingresa el numero de mesa: ");
                    int mesa = scanner.nextInt();
                    scanner.nextLine();  // Limpiar el buffer

                    List<Producto> carta = gestor.obtenerCarta();

                    if (carta.isEmpty()) {
                        System.out.println("La carta esta vacia. No se pueden realizar pedidos.");
                        break;
                    }

                    List<Producto> productosPedido = new ArrayList<>();
                    boolean agregarMas;
                    do {
                        System.out.println("Seleccione un producto de la carta:");
                        for (int i = 0; i < carta.size(); i++) {
                            System.out.println((i + 1) + ". " + carta.get(i));
                        }
                        System.out.print("Ingrese el numero del producto: ");
                        int indice = scanner.nextInt() - 1;
                        scanner.nextLine();  // Limpiar el buffer

                        if (indice >= 0 && indice < carta.size()) {
                            productosPedido.add(carta.get(indice));
                            System.out.println("Producto agregado al pedido.");
                        } else {
                            System.out.println("Opcion invalida.");
                        }

                        System.out.print("¿Desea agregar otro producto? (true/false): ");
                        agregarMas = scanner.nextBoolean();
                        scanner.nextLine();  // Limpiar el buffer
                    } while (agregarMas);

                    //Generar un descuento aleatorio entre 0% y 10%
                    Random random = new Random();
                    double descuento = random.nextInt(11);  // Numero aleatorio entre 0 y 10

                    System.out.println("Se aplicara un descuento aleatorio de: " + descuento + "%");

                    gestor.realizarPedido(mesa, productosPedido, descuento);
                }

                case 3 -> gestor.verPedidosActivos();  // Ver los pedidos en curso

                case 4 -> {
                    System.out.print("Ingrese el numero de mesa a cerrar: ");
                    int mesa = scanner.nextInt();
                    scanner.nextLine();
                    gestor.cerrarPedido(mesa);
                }

                case 5 -> {
                    System.out.print("Ingrese el numero de mesa a cancelar: ");
                    int mesa = scanner.nextInt();
                    scanner.nextLine();
                    gestor.cancelarPedido(mesa);
                }

                case 6 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 6);
    }
}
