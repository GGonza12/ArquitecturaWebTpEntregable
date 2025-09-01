package Entregable1.Factory;

import Entregable1.DAO.ClienteDAO;
import Entregable1.DAO.FacturaDAO;
import Entregable1.DAO.Factura_ProductoDAO;
import Entregable1.DAO.ProductoDAO;
import Entregable1.entities.Factura_Producto;

public abstract class AbstractFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    public abstract ClienteDAO getClienteDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract ProductoDAO getProductoDAO();
    public abstract Factura_ProductoDAO getFactura_ProductoDAO();
    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : {
                return MySQLDAOFactory.getInstance();
            }
            case DERBY_JDBC: return null;
            default: return null;
        }
    }
}
