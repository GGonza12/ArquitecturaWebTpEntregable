package Entregable1;

import Entregable1.DAO.ClienteDAO;
import Entregable1.DAO.FacturaDAO;
import Entregable1.DAO.ProductoDAO;
import Entregable1.DTO.ClienteFacturacionDTO;
import Entregable1.Factory.AbstractFactory;
import Entregable1.entities.Producto;
import Entregable1.utils.HelperMySQL;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
     /*   HelperMySQL dbMySQL = new HelperMySQL();
        dbMySQL.dropTables();
        dbMySQL.createTables();
        dbMySQL.populateDB();
        dbMySQL.closeConnection();

      */
        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        ProductoDAO pr = chosenFactory.getProductoDAO();
        System.out.println(pr.getProductoMasRecaudado());
        ClienteDAO clienteDAO = chosenFactory.getClienteDAO();

        List<ClienteFacturacionDTO> ranking = clienteDAO.getClientesOrdenadosPorFacturacion();
        for (ClienteFacturacionDTO c : ranking) {
            System.out.println(c);
        }

    }
}
