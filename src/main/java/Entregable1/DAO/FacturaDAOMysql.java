package Entregable1.DAO;

import Entregable1.entities.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAOMysql implements FacturaDAO {
    private Connection conn;
    private static FacturaDAOMysql instance;
    public FacturaDAOMysql(Connection conn) {
        this.conn = conn;
    }

    public static synchronized FacturaDAOMysql getInstance(Connection conn) {
        if (instance == null) {
            instance = new FacturaDAOMysql(conn);
        }
        return instance;
    }

    @Override
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

    @Override
    public void updateFactura(Factura factura) {
        String query = "UPDATE Factura SET idCliente = ?, idFactura = ? WHERE idFactura = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, factura.getIdCliente());
            ps.setInt(2, factura.getIdFactura());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFactura(int id) {
        String query = "DELETE FROM Factura WHERE idFactura = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Factura> getAllFacturas() {
        List<Factura> facturas = new ArrayList<>();
        String query = "SELECT * FROM Factura";
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("idFactura");
                int idCliente = rs.getInt("idCliente");
                Factura factura = new Factura(id, idCliente);
                facturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturas;
    }

    @Override
    public Factura getFactura(int id) {
        String query = "SELECT * FROM Factura WHERE idFactura = ?";
        Factura factura = null;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idCliente = rs.getInt("idCliente");
                    factura = new Factura(id, idCliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return factura;
    }

    private void rollback() {
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
