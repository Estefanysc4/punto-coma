package src.servicios;

import src.dominio.Anillado;
import src.dominio.ImpresionBN;
import src.dominio.ImpresionColor;
import src.dominio.Item;
import src.dominio.Pedido;

public class PedidoService {

    public void calcularDescuento(Pedido pedido) {
        long totalBruto = pedido.getTotalBruto();
        int totalImpresiones = 0;
        int totalAnillados = 0;

        for (Item i : pedido.getItems()) {
            if (i instanceof ImpresionBN || i instanceof ImpresionColor)
                totalImpresiones += i.getCantidad();
            if (i instanceof Anillado)
                totalAnillados += i.getCantidad();
        }

        long descuento = 0;

        if (totalAnillados >= 1 && totalImpresiones >= 30)
            descuento = Math.round(totalBruto * 0.10);
        else if (totalBruto > 4000)
            descuento = Math.round(totalBruto * 0.05);

        pedido.setDescuento(descuento);
    }
}