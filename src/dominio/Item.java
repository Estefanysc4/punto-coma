package src.dominio;

public abstract class Item {
    protected String tipo;
    protected int cantidad;
    protected long precioUnitario;
    protected long subtotal;

    public Item(String tipo, int cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public String getTipo() { return tipo; }
    public int getCantidad() { return cantidad; }
    public long getPrecioUnitario() { return precioUnitario; }
    public long getSubtotal() { return subtotal; }

    public abstract void calcularSubtotal();
}