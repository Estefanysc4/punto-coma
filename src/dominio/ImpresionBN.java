package src.dominio;

public class ImpresionBN extends Item {
    private static final long PRECIO_NORMAL = 200L;
    private static final long PRECIO_VOLUMEN = 150L;
    private static final int UMBRAL_VOLUMEN = 100;

    public ImpresionBN(int cantidad) {
        super("Impresión B/N", cantidad);
    }

    @Override
    public void calcularSubtotal() {
        precioUnitario = (cantidad >= UMBRAL_VOLUMEN) ? PRECIO_VOLUMEN : PRECIO_NORMAL;
        subtotal = precioUnitario * cantidad;
    }
}