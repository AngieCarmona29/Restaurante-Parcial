package com.restaurante.service;

import com.restaurante.domain.Pedido;
import java.util.*;

// Maneja los pedidos realizados en memoria
public class PedidoService {
    private List<Pedido> pedidos;

    public PedidoService() {
        this.pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> obtenerPedidosActivos() {
        return pedidos;
    }

    public boolean cerrarPedido(int numeroMesa) {
        for (Pedido pedido : pedidos) {
            if (pedido.getNumeroMesa() == numeroMesa && !pedido.isEntregado()) {
                pedido.marcarEntregado();
                return true;
            }
        }
        return false;
    }

    public boolean cancelarPedido(int numeroMesa) {
        return pedidos.removeIf(pedido -> pedido.getNumeroMesa() == numeroMesa && !pedido.isEntregado());
    }
}
