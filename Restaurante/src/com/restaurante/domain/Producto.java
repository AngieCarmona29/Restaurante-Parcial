package com.restaurante.domain;

// representa un producto del menu
public class Producto {
    private String nombre; // nombre del producto
    private double precio; // precio del producto
    private String categoria; // categoria a la que pertenece el producto

    // constructor que inicializa un producto con su nombre, precio y categoria
    public Producto(String nombre, double precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    // obtiene el precio del producto
    public double getPrecio() { return precio; }

    // obtiene la categoria del producto
    public String getCategoria() { return categoria; }

    // devuelve una representacion en texto del producto
    @Override
    public String toString() {
        return categoria + " - " + nombre + " - $" + precio;
    }
}
