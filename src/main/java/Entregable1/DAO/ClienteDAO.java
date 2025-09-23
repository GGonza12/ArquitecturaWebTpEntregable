package Entregable1.DAO;

import Entregable1.DTO.ClienteFacturacionDTO;
import Entregable1.entities.Cliente;

import java.util.List;

public interface ClienteDAO {

    public  void insertCliente(Cliente cliente);

    public  List<ClienteFacturacionDTO> getClientesOrdenadosPorFacturacion();

    public  List<Cliente> getClientes();

    public  Cliente getCliente(int id);

    public  void updateCliente(Cliente cliente);

    public  void deleteCliente(int id);
}
