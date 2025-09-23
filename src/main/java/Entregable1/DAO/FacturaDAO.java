package Entregable1.DAO;

import Entregable1.entities.Factura;
import java.util.List;

public interface FacturaDAO {

    public void insertFactura(Factura factura);
    public void updateFactura(Factura factura);
    public void deleteFactura(int id);
    public List<Factura> getAllFacturas();
    public Factura getFactura(int id);
}
