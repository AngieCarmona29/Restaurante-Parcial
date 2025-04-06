package com.restaurante.domain;

import java.util.List;

// representa un pedido de una mesa en el restaurante
public class Pedido {
    private int numeroMesa; // numero de la mesa que realizo el pedido
    private List<Producto> productos; // lista de productos incluidos en el pedido
    private boolean entregado; // indica si el pedido ha sido entregado
    private double descuento; // porcentaje de descuento aplicado al pedido

    // constructor que inicializa un pedido con numero de mesa, productos y descuento
    public Pedido(int numeroMesa, List<Producto> productos, double descuento) {
        this.numeroMesa = numeroMesa;
        this.productos = productos;
        this.entregado = false; // por defecto, el pedido no esta entregado
        this.descuento = descuento;
    }

    // obtiene el numero de mesa
    public int getNumeroMesa() { return numeroMesa; }

    // verifica si el pedido ha sido entregado
    public boolean isEntregado() { return entregado; }

    // marca el pedido como entregado
    public void marcarEntregado() { this.entregado = true; }

    // obtiene el porcentaje de descuento aplicado
    public double getDescuento() { return descuento; }

    // calcula el total del pedido aplicando el descuento correspondiente
    public double calcularTotal() {
        double total = productos.stream().mapToDouble(Producto::getPrecio).sum(); // suma el precio de todos los productos
        double totalConDescuento = total - (total * (descuento / 100)); // aplica el descuento
        return Math.round(totalConDescuento * 100.0) / 100.0;  // redondea a 2 decimales
    }

    //Devuelve una representacion en texto del pedido
    @Override
    public String toString() {
        return "Mesa " + numeroMesa + " - Total a pagar: $" + calcularTotal() +
                " (Descuento aplicado: " + descuento + "%, Valor inicial: $"+ productos.stream().mapToDouble(Producto::getPrecio).sum() + ") - Entregado: " + entregado;
    }
}
