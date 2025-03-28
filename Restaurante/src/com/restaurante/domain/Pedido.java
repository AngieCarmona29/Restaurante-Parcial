package com.restaurante.domain;

import java.util.List;

// Representa un pedido de una mesa en el restaurante
public class Pedido {
    private int numeroMesa;
    private List<Producto> productos;
    private boolean entregado;
    private double descuento;

    public Pedido(int numeroMesa, List<Producto> productos, double descuento) {
        this.numeroMesa = numeroMesa;
        this.productos = productos;
        this.entregado = false;
        this.descuento = descuento;
    }

    public int getNumeroMesa() { return numeroMesa; }
    public List<Producto> getProductos() { return productos; }
    public boolean isEntregado() { return entregado; }
    public void marcarEntregado() { this.entregado = true; }
    public double getDescuento() { return descuento; }

    //aplicar el descuento
    public double calcularTotal() {
        double total = productos.stream().mapToDouble(Producto::getPrecio).sum();
        double totalConDescuento = total - (total * (descuento / 100));
        return Math.round(totalConDescuento * 100.0) / 100.0;  // Redondear a 2 decimales
    }

    @Override
    public String toString() {
        return "Mesa " + numeroMesa + " - Total: $" + calcularTotal() + " (Descuento aplicado: " + descuento + "%) - Entregado: " + entregado;
    }
}
