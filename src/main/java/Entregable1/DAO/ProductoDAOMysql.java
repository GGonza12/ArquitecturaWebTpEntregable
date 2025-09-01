package Entregable1.DAO;

import Entregable1.DTO.ProductoRecaudacionDTO;
import Entregable1.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOMysql extends ProductoDAO {
    private Connection conn;

    public ProductoDAOMysql(Connection conn) {
        this.conn = conn;
    }

    @Override
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

    @Override
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

    @Override
    public List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Producto";
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                productos.add(new Producto(rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getFloat("valor")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public Producto getProducto(int id) {
        String query = "SELECT * FROM Producto WHERE idProducto = ?";
        Producto producto = null;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto(rs.getInt("idProducto"),
                            rs.getString("nombre"),
                            rs.getFloat("valor"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }


    @Override
    public void updateProducto(Producto producto) {
        String query = "UPDATE Producto SET nombre = ?, valor = ? WHERE idProducto = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            ps.setFloat(2, producto.getValor());
            ps.setInt(3, producto.getIdProducto());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProducto(int id) {
        String query = "DELETE FROM Producto WHERE idProducto = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
    }

    private void rollback() {
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
