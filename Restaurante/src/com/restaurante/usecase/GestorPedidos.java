package com.restaurante.usecase;

import com.restaurante.domain.Pedido;
import com.restaurante.domain.Producto;
import com.restaurante.service.CartaService;
import com.restaurante.service.PedidoService;
import java.util.List;

// Maneja la logica de negocio de los pedidos
public class GestorPedidos {
    private CartaService cartaService;
    private PedidoService pedidoService;

    public GestorPedidos() {
        this.cartaService = new CartaService();
        this.pedidoService = new PedidoService();
    }

    public void mostrarCarta() {
        cartaService.mostrarCartaClasificada();
    }
    // Se agrega este metodo para que Main.java pueda acceder a la carta
    public List<Producto> obtenerCarta() {
        return cartaService.obtenerCarta();
    }
    public void realizarPedido(int mesa, List<Producto> productos, double descuento) {
        Pedido pedido = new Pedido(mesa, productos, descuento);
        pedidoService.agregarPedido(pedido);
        System.out.println("Pedido agregado correctamente.");
    }

    public void verPedidosActivos() {
        List<Pedido> pedidos = pedidoService.obtenerPedidosActivos();
        pedidos.forEach(System.out::println);
    }

    public void cerrarPedido(int mesa) {
        if (pedidoService.cerrarPedido(mesa)) {
            System.out.println("Pedido cerrado con exito.");
        } else {
            System.out.println("No se pudo cerrar el pedido.");
        }
    }

    public void cancelarPedido(int mesa) {
        if (pedidoService.cancelarPedido(mesa)) {
            System.out.println("Pedido cancelado.");
        } else {
            System.out.println("No se puede cancelar el pedido.");
        }
    }
}
