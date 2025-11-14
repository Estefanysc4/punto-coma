package src.dominio;

public class ImpresionColor extends Item {
    private static final long PRECIO_NORMAL = 500L;
    private static final long PRECIO_VOLUMEN = 400L;
    private static final int UMBRAL_VOLUMEN = 50;

    public ImpresionColor(int cantidad) {
        super("Impresión Color", cantidad);
    }

    @Override
    public void calcularSubtotal() {
        precioUnitario = (cantidad >= UMBRAL_VOLUMEN) ? PRECIO_VOLUMEN : PRECIO_NORMAL;
        subtotal = precioUnitario * cantidad;
    }
}