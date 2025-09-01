package Entregable1.DAO;

import Entregable1.entities.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class FacturaDAO {

    public abstract void insertFactura(Factura factura);
    public abstract void updateFactura(Factura factura);
    public abstract void deleteFactura(int id);
    public abstract List<Factura> getAllFacturas();
    public abstract Factura getFactura(int id);
}
