package src.dominio;


public class Anillado extends Item {
    private static final long PRECIO = 3000L;

    public Anillado(int cantidad) {
        super("Anillado", cantidad);
        this.precioUnitario = PRECIO;
    }

    @Override
    public void calcularSubtotal() {
        subtotal = precioUnitario * cantidad;
    }
}