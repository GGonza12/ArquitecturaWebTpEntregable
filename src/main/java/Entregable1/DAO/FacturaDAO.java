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
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, factura.getIdFactura()); // idFactura
            ps.setInt(2, factura.getIdCliente()); // idCliente
            ps.executeUpdate();
            System.out.println("Factura insertada exitosamente.");
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
