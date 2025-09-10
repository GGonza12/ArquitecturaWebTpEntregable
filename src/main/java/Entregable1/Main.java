package Entregable1;

import Entregable1.DAO.ClienteDAO;
import Entregable1.DAO.FacturaDAO;
import Entregable1.DAO.Factura_ProductoDAO;
import Entregable1.DAO.ProductoDAO;
import Entregable1.DTO.ClienteFacturacionDTO;
import Entregable1.DTO.ProductoRecaudacionDTO;
import Entregable1.Factory.AbstractFactory;
import Entregable1.entities.Producto;
import Entregable1.utils.HelperMySQL;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        //Helper para borrar las tablas, crear las tablas y llenar de datos las tablas con los csv.

        HelperMySQL dbMySQL = new HelperMySQL();
        dbMySQL.dropTables();
        dbMySQL.createTables();
        dbMySQL.populateDB();
        dbMySQL.closeConnection();


        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);// 1 MYSQL - 2 DERBY
        ProductoDAO pr = chosenFactory.getProductoDAO();
        System.out.println("El producto mas recaudado es: ");
        ProductoRecaudacionDTO res = pr.getProductoMasRecaudado();
        System.out.println(" Nombre: "+ res.getNombre());
        System.out.println(" Id del producto: "+ res.getIdProducto());
        System.out.println(" Total recaudado: "+ res.getTotalRecaudado());
        ClienteDAO clienteDAO = chosenFactory.getClienteDAO();
        //FacturaDAO facturaDAO = chosenFactory.getFacturaDAO();
        //ProductoDAO productoDAO = chosenFactory.getProductoDAO();
        //Factura_ProductoDAO facturaProductoDAO = chosenFactory.getFactura_ProductoDAO();
        System.out.println("Cliente ordenados por mayor facturacion: ");
        List<ClienteFacturacionDTO> ranking = clienteDAO.getClientesOrdenadosPorFacturacion();
        for (ClienteFacturacionDTO c : ranking) {
            //System.out.println(c);
            System.out.println(" Cliente: " + c.getNombre() +"\n  Id: "+c.getIdCliente()+"\n  Total facturado: "+c.getTotalFacturado());
        }

        //System.out.println(clienteDAO.getClientes());
        //System.out.println(clienteDAO.getCliente(4));
        //System.out.println(facturaDAO.getFactura(10));
        //System.out.println(facturaDAO.getAllFacturas());
        //System.out.println(productoDAO.getProducto(1));
        //System.out.println(productoDAO.getAllProductos());
        //System.out.println(facturaProductoDAO.getAllFacturaProductos());
       // System.out.println(facturaProductoDAO.getFacturaProducto(4,67));






    }
}
