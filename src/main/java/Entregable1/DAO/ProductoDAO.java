package Entregable1.DAO;

import Entregable1.DTO.ProductoRecaudacionDTO;
import Entregable1.entities.Producto;
import java.util.List;

public interface ProductoDAO {

    public abstract void InsertProducto(Producto producto);

    public ProductoRecaudacionDTO getProductoMasRecaudado();
    public List<Producto> getAllProductos();
    public Producto getProducto(int idProducto);
    public void  updateProducto(Producto producto);
    public void deleteProducto(int idProducto);


}
