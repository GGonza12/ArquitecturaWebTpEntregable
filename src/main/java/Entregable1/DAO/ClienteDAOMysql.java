package Entregable1.DAO;

import Entregable1.DTO.ClienteFacturacionDTO;
import Entregable1.entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOMysql extends ClienteDAO {
    private Connection conn;

    public ClienteDAOMysql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertCliente(Cliente cliente) { //Se puede insertar
        String query = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cliente.getIdCliente()); // idPersona
            ps.setString(2, cliente.getNombre()); // nombre
            ps.setString(3, cliente.getEmail()); // edad
            ps.executeUpdate();
            System.out.println("Cliente insertado exitosamente.");
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
    public List<ClienteFacturacionDTO> getClientesOrdenadosPorFacturacion() {
        String query = "SELECT c.idCliente, c.nombre, SUM(fp.cantidad * p.valor) AS total_facturado " +
                "FROM Cliente c " +
                "JOIN Factura f ON c.idCliente = f.idCliente " +
                "JOIN Factura_Producto fp ON f.idFactura = fp.idFactura " +
                "JOIN Producto p ON fp.idProducto = p.idProducto " +
                "GROUP BY c.idCliente, c.nombre " +
                "ORDER BY total_facturado DESC";

        List<ClienteFacturacionDTO> lista = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                float total = rs.getFloat("total_facturado");

                lista.add(new ClienteFacturacionDTO(id, nombre, total));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<Cliente> getClientes() {
        String query = "SELECT * FROM Cliente";

        List<Cliente> lista = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("idCliente");
                    String nombre = rs.getString("nombre");
                    String email = rs.getString("email");
                    lista.add(new Cliente(id, nombre, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Cliente getCliente(int id) {
        String query = "SELECT * FROM Cliente WHERE idCliente = ?";
        Cliente clienteById = null;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String email = rs.getString("email");
                    clienteById = new Cliente(id, nombre, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienteById;
    }


    @Override
    public void updateCliente(Cliente cliente) {
        String query = "UPDATE Cliente SET nombre = ?, email = ? WHERE idCliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setInt(3, cliente.getIdCliente());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
    }


    @Override
    public void deleteCliente(int id) {
        String query = "DELETE FROM Cliente WHERE idCliente = ?";
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
