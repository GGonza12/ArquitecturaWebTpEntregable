package Entregable1.DTO;

public class ProductoRecaudacionDTO {
    private int idProducto;
    private String nombre;
    private double totalRecaudado;

    public ProductoRecaudacionDTO() {}

    public ProductoRecaudacionDTO(int idProducto, String nombre, double totalRecaudado) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.totalRecaudado = totalRecaudado;
    }


    public int getIdProducto() {
        return idProducto;
    }
    public String getNombre() {
        return nombre;
    }
    public double getTotalRecaudado() {
        return totalRecaudado;
    }

    @Override
    public String toString() {
       return "ProductoRecaudacionDTO{"+
               " idProducto=" + idProducto +
               ", nombre=" + nombre +
               ", totalRecaudado=" + totalRecaudado +
               " }";
    }
}
