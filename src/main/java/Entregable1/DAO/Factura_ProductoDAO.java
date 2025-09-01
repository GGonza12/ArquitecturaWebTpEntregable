package Entregable1.DAO;

import Entregable1.entities.Factura;
import Entregable1.entities.Factura_Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Factura_ProductoDAO {

    private Connection conn;

    public Factura_ProductoDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertFacturaProducto(Factura_Producto facturaProducto) {
        String query = "INSERT INTO Factura_Producto (idFactura, idProducto,cantidad) VALUES (?,?, ?)";


        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, facturaProducto.getIdFactura()); // idFactura
            ps.setInt(2, facturaProducto.getIdProducto()); // idProducto
            ps.setInt(3, facturaProducto.getCantidad()); // cantidad
            ps.executeUpdate();
            conn.commit();

            System.out.println("Factura_Producto insertada exitosamente.");
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Se hizo rollback por error en Factura_Producto.");
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();

        }
    }
}
