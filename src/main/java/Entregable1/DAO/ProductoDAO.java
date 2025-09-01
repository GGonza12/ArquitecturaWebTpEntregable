package Entregable1.DAO;

import Entregable1.DTO.ProductoRecaudacionDTO;
import Entregable1.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class ProductoDAO {

    public abstract void InsertProducto(Producto producto);

    public abstract ProductoRecaudacionDTO getProductoMasRecaudado();
    public abstract List<Producto> getAllProductos();
    public abstract Producto getProducto(int idProducto);
    public abstract void  updateProducto(Producto producto);
    public abstract void deleteProducto(int idProducto);


}
