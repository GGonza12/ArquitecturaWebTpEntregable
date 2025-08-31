package Entregable1.DAO;

import Entregable1.entities.Factura_Producto;
import Entregable1.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoDAO {


    private Connection conn;

    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }

    public void InsertProducto(Producto producto) {
        String query = "INSERT INTO Producto (idProducto,nombre,valor) VALUES (?,?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, producto.getIdProducto()); // idProducto
            ps.setString(2, producto.getNombre()); // nombre
            ps.setFloat(2, producto.getValor()); // valor
            ps.executeUpdate();
            System.out.println("Producto insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
