package com.restaurante.service;

import com.restaurante.domain.Pedido;
import java.util.*;

// maneja los pedidos realizados en memoria
public class PedidoService {
    private List<Pedido> pedidos; // lista de pedidos activos

    // constructor que inicializa la lista de pedidos
    public PedidoService() {
        this.pedidos = new ArrayList<>();
    }

    // agrega un nuevo pedido a la lista
    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // devuelve la lista de pedidos activos
    public List<Pedido> obtenerPedidosActivos() {
        return pedidos;
    }

    // cierra un pedido marcandolo como entregado si aun no ha sido entregado
    public boolean cerrarPedido(int numeroMesa) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNumeroMesa() == numeroMesa && !pedido.isEntregado()) {
                //Muestra el valor que debe pagar el cliente
                System.out.println("Valor total que debe pagar el cliente: $" + pedido.calcularTotal()+ " Descuento aplicado: "+ pedido.getDescuento());
                pedido.marcarEntregado(); // marca el pedido como entregado
                return true;
            }
        }
        return false; // devuelve falso si el pedido ya estaba entregado o no existia
    }

    // cancela un pedido si aun no ha sido entregado
    public boolean cancelarPedido(int numeroMesa) {
        return pedidos.removeIf(pedido -> pedido.getNumeroMesa() == numeroMesa && !pedido.isEntregado());
    }
}
