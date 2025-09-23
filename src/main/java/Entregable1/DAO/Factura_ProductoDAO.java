package Entregable1.DAO;

import Entregable1.entities.Factura_Producto;
import java.util.List;

public interface Factura_ProductoDAO {

    public  void insertFacturaProducto(Factura_Producto facturaProducto);
    public  Factura_Producto getFacturaProducto(int idFactura, int idProducto);
    public  List<Factura_Producto> getAllFacturaProductos();
    public  void updateFacturaProducto(Factura_Producto fp);
    public  void deleteFacturaProducto(int idFactura, int idProducto);


}
