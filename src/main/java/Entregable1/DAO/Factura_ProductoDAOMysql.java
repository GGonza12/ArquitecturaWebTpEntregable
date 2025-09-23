package Entregable1.DAO;

import Entregable1.entities.Factura_Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Factura_ProductoDAOMysql implements Factura_ProductoDAO {
    private Connection conn;
    private static Factura_ProductoDAOMysql instance;

    public Factura_ProductoDAOMysql(Connection conn) {
        this.conn = conn;
    }


    public static synchronized Factura_ProductoDAOMysql getInstance(Connection conn) {
        if (instance == null) {
            instance = new Factura_ProductoDAOMysql(conn);
        }
        return instance;
    }

    @Override
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
            rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Factura_Producto getFacturaProducto(int idFactura, int idProducto) {
        String query = "SELECT * FROM Factura_Producto WHERE idFactura = ? AND idProducto = ?";
        Factura_Producto fp = null;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    fp = new Factura_Producto(rs.getInt("idFactura"),
                            rs.getInt("idProducto"),
                            rs.getInt("cantidad"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fp;
    }

    @Override
    public List<Factura_Producto> getAllFacturaProductos() {
        List<Factura_Producto> list = new ArrayList<>();
        String query = "SELECT * FROM Factura_Producto";
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Factura_Producto(rs.getInt("idFactura"),
                        rs.getInt("idProducto"),
                        rs.getInt("cantidad")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateFacturaProducto(Factura_Producto fp) {
        String query = "UPDATE Factura_Producto SET cantidad = ? WHERE idFactura = ? AND idProducto = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, fp.getCantidad());
            ps.setInt(2, fp.getIdFactura());
            ps.setInt(3, fp.getIdProducto());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFacturaProducto(int idFactura, int idProducto) {
        String query = "DELETE FROM Factura_Producto WHERE idFactura = ? AND idProducto = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);
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
