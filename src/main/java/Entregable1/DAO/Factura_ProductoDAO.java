package Entregable1.DAO;

import Entregable1.entities.Factura;
import Entregable1.entities.Factura_Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class Factura_ProductoDAO {

    public abstract void insertFacturaProducto(Factura_Producto facturaProducto);
    public abstract Factura_Producto getFacturaProducto(int idFactura, int idProducto);
    public abstract List<Factura_Producto> getAllFacturaProductos();
    public abstract void updateFacturaProducto(Factura_Producto fp);
    public abstract void deleteFacturaProducto(int idFactura, int idProducto);


}
