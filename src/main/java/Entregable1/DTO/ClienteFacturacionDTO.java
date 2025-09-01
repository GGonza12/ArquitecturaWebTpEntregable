package Entregable1.DTO;

public class ClienteFacturacionDTO {
    private int idCliente;
    private String nombre;
    private float totalFacturado;

    public ClienteFacturacionDTO(int idCliente, String nombre, float totalFacturado) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.totalFacturado = totalFacturado;
    }

    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public float getTotalFacturado() {
        return totalFacturado;
    }
    public void setTotalFacturado(float totalFacturado) {
        this.totalFacturado = totalFacturado;
    }

    @Override
    public String toString() {
        return " ClienteFactiracion {" +
                " idCliente: "+ idCliente +
                ", nombre: "+nombre+
                ", Total Facturado: "+totalFacturado+
                " }";
    }
}
