package com.restaurante.application;

import com.restaurante.domain.Producto; //Importa la clase producto
import com.restaurante.usecase.GestorPedidos; //Importa la clase gestor de pedidos

import java.util.*; //Importa las librerias necesarias

//Punto de entrada de la aplicacion
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Crea un objeto scanner para leer la entrada del usuario
        GestorPedidos gestor = new GestorPedidos(); //Instancia un objeto de gestor de pedidos
        boolean running = true;

        int opcion;
        while (running){
            //Muestra el menu principal
            System.out.println("\nBienvenido a Las Delicias Antioqueñas\nAqui encontrarás las ricuras de nuestra tierra...");
            System.out.println("  1. Muestra al cliente la carta del restaurante");
            System.out.println("  2. Crea un nuevo pedido para una mesa");
            System.out.println("  3. Mira todos los pedidos en curso");
            System.out.println("  4. Marca un pedido como entregado");
            System.out.println("  5. Cancela un pedido (solo si no ha sido entregado)");
            System.out.println("  6. Salir");
            System.out.print("  ¿Que opción deseas elegir hoy?");

            String respuestaMenu = scanner.nextLine(); // lee la opcion ingresada por el usuario

            try{
                // Intentar convertir la entrada a un número entero
                opcion = Integer.parseInt(respuestaMenu);
            } catch (NumberFormatException e){
                // Si la conversión falla, mostrar un mensaje de error y continuar el bucle
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                continue;
            }

            switch (opcion) {
                case 1 -> gestor.mostrarCarta(); // muestra la carta del restaurante
                case 2 -> {
                    System.out.print("\nIngresa el numero de la mesa que estas atendiendo: ");
                    String respuestaMesa = scanner.nextLine(); // lee la respuesta ingresada
                    int mesa;

                    try{
                        // Intentar convertir la entrada a un número entero
                        mesa = Integer.parseInt(respuestaMesa);
                    } catch (NumberFormatException e){
                        // Si la conversión falla, mostrar un mensaje de error y continuar el bucle
                        System.out.println("Entrada no válida. Por favor vuelva a iniciar el pedido.");
                        continue;
                    }
                    while (mesa <= 0 || mesa>15){ //Verifica que la mesa esté dentro de las opciones permitidas
                        System.out.println("Respuesta inválida. Tu número de mesa debe estar entre 1 y 15");
                        System.out.print("Ingresa el numero de la mesa que estas atendiendo: ");
                        mesa = scanner.nextInt();
                    }
                    
                    List<Producto> carta = gestor.obtenerCarta(); // obtiene la carta del restaurante

                    // verifica si la carta esta vacia
                    if (carta.isEmpty()) {
                        System.out.println("La carta esta vacia. No se pueden realizar pedidos.");
                        break;
                    }

                    List<Producto> productosPedido = new ArrayList<>(); // lista para almacenar los productos del pedido
                    boolean agregarMas;
                    do {
                        System.out.println("\n¿Que desea comer hoy el cliente?");
                        // muestra la lista de productos disponibles
                        for (int i = 0; i < carta.size(); i++) {
                            System.out.println((i + 1) + ". " + carta.get(i));
                        }
                        System.out.print("Ingresa el numero del plato: ");
                        int indice = scanner.nextInt() - 1; // obtiene el indice del producto seleccionado
                        scanner.nextLine(); // limpia el buffer

                        // verifica si el indice es valido
                        if (indice >= 0 && indice < carta.size()) {
                            productosPedido.add(carta.get(indice)); // agrega el producto al pedido
                            System.out.println("Plato agregado al pedido :).");
                        } else {
                            System.out.println("Opcion invalida.");
                        }

                        System.out.print("¿Desea agregar otro producto? (si/no): ");
                        String respuesta = scanner.nextLine().trim().toLowerCase();// convierte la respuesta a minusculas
                        //Asegura que la respuesta solo sea si o no
                        while (!(respuesta.equals("si") || respuesta.equals("no"))){
                            System.out.println("Respuesta inválida. Por favor, ingresa 'si' o 'no'.");
                            System.out.print("¿Desea agregar otro producto? (si/no): ");
                            respuesta = scanner.nextLine().trim().toLowerCase();
                        }
                        agregarMas = respuesta.equals("si"); // verifica si el usuario quiere agregar mas productos
                    } while (agregarMas);

                    //Mira si el cliente quiere pagar usando un cupón o si se le otorga uno
                    System.out.println("¿El cliente cuenta con un cupón? (si/no):");
                    String RespuestaCupon = scanner.nextLine().trim().toLowerCase();
                    while (!(RespuestaCupon.equals("si") || RespuestaCupon.equals("no"))){
                        System.out.println("Respuesta inválida. Por favor, ingresa 'si' o 'no'.");
                        System.out.print("¿Desea agregar otro producto? (si/no): ");
                        RespuestaCupon = scanner.nextLine().trim().toLowerCase();
                    }
                    double descuento;
                    if (RespuestaCupon.equals("no")){
                        // generar un descuento aleatorio entre 0% y 10%
                        Random random = new Random();
                        descuento = random.nextInt(11);  // numero aleatorio entre 0 y 10
                        System.out.println("Increible! Dile al cliente que se ha ganado un descuento del: " + descuento + "%");
                    } else {
                        System.out.println("Ingrese el porcentaje del cupón: ");
                        descuento = scanner.nextDouble();
                    }

                    gestor.realizarPedido(mesa, productosPedido, descuento); // registra el pedido en el sistema
                    System.out.println("El pedido se agregó correctamente.");
                }
                case 3 -> gestor.verPedidosActivos(); // muestra los pedidos activos
                case 4 -> {
                    System.out.print("Ingresa el numero de mesa a cerrar: ");
                    int mesa = scanner.nextInt(); // lee el numero de mesa
                    scanner.nextLine(); // limpia el buffer
                    if (gestor.cerrarPedido(mesa)) { // intenta cerrar el pedido
                        System.out.println("Cerraste el pedido.");
                    } else {
                        System.out.println("No se pudo cerrar el pedido.");
                    }
                }
                case 5 -> {
                    System.out.print("Ingresa el numero de mesa a cancelar: ");
                    int mesa = scanner.nextInt(); // lee el numero de mesa
                    scanner.nextLine(); // limpia el buffer
                    if (gestor.cancelarPedido(mesa)) { // intenta cancelar el pedido
                        System.out.println("Pedido cancelado.");
                    } else {
                        System.out.println("No se puede cancelar el pedido.");
                    }
                }
                case 6 -> {
                    System.out.println("Ten un buen día");
                    running=false;
                } // finaliza el programa
                default -> System.out.println("Opcion no valida."); // mensaje para opciones invalidas
            }
        }
    }
}
