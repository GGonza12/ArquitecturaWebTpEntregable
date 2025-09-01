package Entregable1.DAO;

import Entregable1.entities.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturaDAO {
    private Connection conn;

    public FacturaDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertFactura(Factura factura) {
        String query = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, factura.getIdFactura()); // idFactura
            ps.setInt(2, factura.getIdCliente()); // idCliente
            ps.executeUpdate();
            System.out.println("Factura insertada exitosamente.");
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
}
