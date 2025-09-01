package Entregable1.DAO;

import Entregable1.DTO.ClienteFacturacionDTO;
import Entregable1.entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ClienteDAO {
    private Connection conn;

    public abstract void insertCliente(Cliente cliente);

    public abstract List<ClienteFacturacionDTO> getClientesOrdenadosPorFacturacion();

    public abstract List<Cliente> getClientes();

    public abstract Cliente getCliente(int id);

    public abstract void updateCliente(Cliente cliente);

    public abstract void deleteCliente(int id);
}
