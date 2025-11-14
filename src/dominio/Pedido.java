package src.dominio;

import java.text.NumberFormat;
import java.util.*;

public class Pedido {
    private Cliente cliente;
    private List<Item> items = new ArrayList<>();
    private long totalBruto;
    private long descuento;
    private long totalFinal;
    private boolean confirmado;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void addItem(Item item) {
        if (confirmado) throw new IllegalStateException("Pedido ya confirmado.");
        items.add(item);
    }

    public List<Item> getItems() { return items; }
    public Cliente getCliente() { return cliente; }

    public void calcularTotales() {
        totalBruto = 0;
        for (Item i : items) {
            i.calcularSubtotal();
            totalBruto += i.getSubtotal();
        }
        totalFinal = totalBruto - descuento;
    }

    public void setDescuento(long descuento) {
        this.descuento = descuento;
        this.totalFinal = totalBruto - descuento;
    }

    public long getTotalBruto() { return totalBruto; }
    public long getDescuento() { return descuento; }
    public long getTotalFinal() { return totalFinal; }

    public void confirmar() { confirmado = true; }
    public boolean isConfirmado() { return confirmado; }

    public String generarResumen() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new java.util.Locale("es", "CO"));
        StringBuilder sb = new StringBuilder();
        sb.append("\n===== RESUMEN DEL PEDIDO =====\n");
        sb.append("Cliente: ").append(cliente.getNombre()).append("\n");
        sb.append("Teléfono: ").append(cliente.getTelefono()).append("\n\n");
        sb.append(String.format("%-20s %8s %14s %14s\n", "Item", "Cant.", "P. Unit.", "Subtotal"));
        sb.append("-------------------------------------------------------------\n");
        for (Item i : items)
            sb.append(String.format("%-20s %8d %14s %14s\n", i.getTipo(), i.getCantidad(),
                    nf.format(i.getPrecioUnitario()), nf.format(i.getSubtotal())));
        sb.append("-------------------------------------------------------------\n");
        sb.append(String.format("%-45s %14s\n", "Total bruto:", nf.format(totalBruto)));
        sb.append(String.format("%-45s %14s\n", "Descuento:", nf.format(descuento)));
        sb.append(String.format("%-45s %14s\n", "Total final:", nf.format(totalFinal)));
        sb.append("==============================\n");
        return sb.toString();
    }
}