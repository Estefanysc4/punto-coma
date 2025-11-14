package src;
import java.util.Scanner;

import src.dominio.Anillado;
import src.dominio.Cliente;
import src.dominio.ImpresionBN;
import src.dominio.ImpresionColor;
import src.dominio.Pedido;
import src.servicios.PedidoService;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PedidoService service = new PedidoService();

        System.out.println("=== Papelería Punto & Coma ===");
        System.out.print("Nombre del cliente: ");
        String nombre = sc.nextLine();
        System.out.print("Teléfono: ");
        String tel = sc.nextLine();

        Cliente cliente = new Cliente(nombre, tel);
        Pedido pedido = new Pedido(cliente);

       
        int bn = pedirCantidad(sc, "Impresión B/N ($200 o $150 si >=100): ");
        if (bn > 0) pedido.addItem(new ImpresionBN(bn));

        int color = pedirCantidad(sc, "Impresión Color ($500 o $400 si >=50): ");
        if (color > 0) pedido.addItem(new ImpresionColor(color));

        int anillado = pedirCantidad(sc, "Anillado ($3.000 c/u): ");
        if (anillado > 0) pedido.addItem(new Anillado(anillado));

        if (pedido.getItems().isEmpty()) {
            System.out.println("No se agregaron ítems. Saliendo...");
            return;
        }

      
        pedido.calcularTotales();
        service.calcularDescuento(pedido);

        
        System.out.println(pedido.generarResumen());

       
        System.out.print("¿Confirmar pedido? (S/N): ");
        String resp = sc.nextLine();
        if (resp.equalsIgnoreCase("S")) {
            pedido.confirmar();
            System.out.println("Pedido confirmado con éxito ✅");
        } else {
            System.out.println("Pedido cancelado ❌");
        }
    }

    private static int pedirCantidad(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int cant = Integer.parseInt(sc.nextLine());
                if (cant < 0) System.out.println("Cantidad no puede ser negativa.");
                else return cant;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intenta nuevamente.");
            }
        }
    }
}