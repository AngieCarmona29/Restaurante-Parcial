package com.restaurante.service;

import com.restaurante.domain.Producto;
import java.io.*;
import java.util.*;

// Maneja la carga de productos desde un archivo
public class CartaService {
    private List<Producto> productos;

    public CartaService() {
        this.productos = new ArrayList<>();
        cargarCartaDesdeArchivo("carta.txt");
    }

    private void cargarCartaDesdeArchivo(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    double precio = Double.parseDouble(partes[1].trim());
                    String categoria = partes[2].trim();
                    productos.add(new Producto(nombre, precio, categoria));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar la carta: " + e.getMessage());
        }
    }

    public List<Producto> obtenerCarta() {
        return productos;
    }

    public void mostrarCartaClasificada() {
        System.out.println("\n--- MENÚ DEL RESTAURANTE ---");
        System.out.println("\nENTRADAS:");
        productos.stream().filter(p -> p.getCategoria().equalsIgnoreCase("Entrada")).forEach(System.out::println);
        System.out.println("\nPLATOS FUERTES:");
        productos.stream().filter(p -> p.getCategoria().equalsIgnoreCase("Plato fuerte")).forEach(System.out::println);
        System.out.println("\nPOSTRES:");
        productos.stream().filter(p -> p.getCategoria().equalsIgnoreCase("Postre")).forEach(System.out::println);
    }
}
