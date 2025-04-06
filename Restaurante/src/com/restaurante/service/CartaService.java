package com.restaurante.service;

import com.restaurante.domain.Producto;
import java.io.*;
import java.util.*;

// maneja la carga de productos desde un archivo y proporciona metodos para acceder a la carta del restaurante
public class CartaService {
    private List<Producto> productos; // lista de productos disponibles en la carta

    // constructor que inicializa la lista de productos y carga la carta desde un archivo
    public CartaService() {
        this.productos = new ArrayList<>();
        cargarCartaDesdeArchivo("carta.txt"); // carga los productos desde el archivo de texto
    }

    // carga la carta desde un archivo de texto
    private void cargarCartaDesdeArchivo(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) { // lee cada linea del archivo
                String[] partes = linea.split(","); // separa los valores por coma
                if (partes.length == 3) { // verifica que haya tres valores por linea
                    String nombre = partes[0].trim(); // obtiene el nombre del producto
                    double precio = Double.parseDouble(partes[1].trim()); // convierte el precio a double
                    String categoria = partes[2].trim(); // obtiene la categoria del producto
                    productos.add(new Producto(nombre, precio, categoria)); // agrega el producto a la lista
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar la carta: " + e.getMessage()); // maneja errores de lectura del archivo
        }
    }

    // devuelve la lista de productos en la carta
    public List<Producto> obtenerCarta() {
        return productos;
    }

    // muestra la carta clasificada por categorias
    public void mostrarCartaClasificada() {
        System.out.println("\nNUESTRO MENU");
        System.out.println("ENTRADAS:");
        productos.stream().filter(p -> p.getCategoria().equalsIgnoreCase("Entrada")).forEach(System.out::println);
        System.out.println("\nPLATOS FUERTES:");
        productos.stream().filter(p -> p.getCategoria().equalsIgnoreCase("Plato fuerte")).forEach(System.out::println);
        System.out.println("\nPOSTRES:");
        productos.stream().filter(p -> p.getCategoria().equalsIgnoreCase("Postre")).forEach(System.out::println);
    }
}
