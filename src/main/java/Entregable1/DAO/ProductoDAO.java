package Entregable1.DAO;

import Entregable1.DTO.ProductoRecaudacionDTO;
import Entregable1.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {


    private Connection conn;

    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }

    public void InsertProducto(Producto producto) {
        String query = "INSERT INTO Producto (idProducto,nombre,valor) VALUES (?,?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, producto.getIdProducto()); // idProducto
            ps.setString(2, producto.getNombre()); // nombre
            ps.setFloat(3, producto.getValor()); // valor
            ps.executeUpdate();
            System.out.println("Producto insertado exitosamente.");
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Se hizo rollback por error en Producto.");
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();

        }
    }

    public ProductoRecaudacionDTO getProductoMasRecaudado() {
        String query = "SELECT p.idProducto, p.nombre, SUM(fp.cantidad * p.valor) AS total_recaudado " +
                "FROM Factura_Producto fp " +
                "JOIN Producto p ON fp.idProducto = p.idProducto " +
                "GROUP BY p.idProducto, p.nombre " +
                "ORDER BY total_recaudado DESC " +
                "LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                float totalRecaudado = rs.getFloat("total_recaudado");
                return new ProductoRecaudacionDTO(idProducto, nombre, totalRecaudado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // si no hay resultados
    }

}
