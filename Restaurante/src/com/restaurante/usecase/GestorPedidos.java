package com.restaurante.usecase;

import com.restaurante.domain.Pedido;
import com.restaurante.domain.Producto;
import com.restaurante.service.CartaService;
import com.restaurante.service.PedidoService;
import java.util.List;

// maneja la logica de negocio de los pedidos
public class GestorPedidos {
    private CartaService cartaService; // servicio para manejar la carta del restaurante
    private PedidoService pedidoService; // servicio para manejar los pedidos

    // constructor que inicializa los servicios
    public GestorPedidos() {
        this.cartaService = new CartaService();
        this.pedidoService = new PedidoService();
    }

    // muestra la carta clasificada por categorias
    public void mostrarCarta() {
        cartaService.mostrarCartaClasificada();
    }

    // devuelve la lista de productos de la carta
    public List<Producto> obtenerCarta() {
        return cartaService.obtenerCarta();
    }

    // crea un nuevo pedido y lo agrega al sistema
    public void realizarPedido(int mesa, List<Producto> productos, double descuento) {
        Pedido pedido = new Pedido(mesa, productos, descuento);
        pedidoService.agregarPedido(pedido);
    }

    // muestra todos los pedidos activos
    public void verPedidosActivos() {
        List<Pedido> pedidos = pedidoService.obtenerPedidosActivos();
        System.out.println("\nPEDIDOS EN CURSO");
        if (pedidos.isEmpty()){
            System.out.println("No hay ningún pedido en curso");
        } else{
            pedidos.forEach(System.out::println);
        }
    }

    // cierra un pedido si aun no ha sido entregado
    public boolean cerrarPedido(int mesa) {
        return pedidoService.cerrarPedido(mesa);
    }

    // cancela un pedido si aun no ha sido entregado
    public boolean cancelarPedido(int mesa) {
        return pedidoService.cancelarPedido(mesa);
    }
}
