package Entregable1.utils;

import Entregable1.entities.Cliente;
import Entregable1.entities.Factura;
import Entregable1.entities.Factura_Producto;
import Entregable1.entities.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelperMySQL {
    private Connection conn;

    public HelperMySQL() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/Entregable1";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dropTables() throws SQLException {
        // Desactivar temporalmente las foreign keys para borrar las tablas
        conn.createStatement().execute("SET FOREIGN_KEY_CHECKS = 0");
        conn.commit();
        //Drop Factura_Producto
        String dropFactura_Producto = "DROP TABLE IF EXISTS Factura_Producto";
        conn.prepareStatement(dropFactura_Producto).execute();
        conn.commit();
        //Drop Factura
        String dropFactura = "DROP TABLE IF EXISTS Factura";
        conn.prepareStatement(dropFactura).execute();
        conn.commit();
        //Drop Producto
        String dropProducto = "DROP TABLE IF EXISTS Producto";
        conn.prepareStatement(dropProducto).execute();
        conn.commit();
        //Drop Cliente
        String dropCliente = "DROP TABLE IF EXISTS Cliente";
        conn.prepareStatement(dropCliente).execute();
        conn.commit();

        // Volver a activar las foreign keys
        conn.createStatement().execute("SET FOREIGN_KEY_CHECKS = 1");

        conn.commit();
    }

    public void createTables() throws SQLException {
        String tableCLiente = "CREATE TABLE IF NOT EXISTS Cliente(" +
                "idCliente INT," +
                "nombre VARCHAR(500) NOT NULL," +
                "email VARCHAR(150) NOT NULL," +
                "PRIMARY KEY (idCliente))";
        conn.createStatement().execute(tableCLiente);
        conn.commit();

        //tabla Factura
        String tableFactura = "CREATE TABLE IF NOT EXISTS Factura(" +
                "idFactura INT NOT NULL," +
                "idCliente INT NOT NULL," +
                "PRIMARY KEY (idFactura), FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente))";
        conn.createStatement().execute(tableFactura);
        conn.commit();

        //tabla Producto
        String tableProducto = "CREATE TABLE IF NOT EXISTS Producto(" +
                "idProducto INT NOT NULL," +
                "nombre VARCHAR(45) NOT NULL," +
                "valor FLOAT NOT NULL," +
                "PRIMARY KEY (idProducto))";
        conn.createStatement().execute(tableProducto);
        conn.commit();

        //tabla Factura_Producto
        String tableFacturaProducto = "CREATE TABLE IF NOT EXISTS Factura_Producto (" +
                "idFactura INT NOT NULL," +
                "idProducto INT NOT NULL," +
                "cantidad INT NOT NULL," +
                "PRIMARY KEY (idFactura, idProducto)," +
                "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura)," +
                "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
        conn.createStatement().execute(tableFacturaProducto);
        conn.commit();
        System.out.println("Tablas Creadas");
    }

    //Obtengo los datos de un archivo CSV
    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\main\\java\\Entregable1\\resources\\" + archivo;
        Reader in = new FileReader(path);
        CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void populateDB() throws Exception {
        try {
            System.out.println("Populating DB...");
            //Clientes
            for (CSVRecord row : getData("clientes.csv")) {

                int idCliente = Integer.parseInt(row.get("idCliente"));
                String nombre = row.get("nombre");
                String email = row.get("email");
                Cliente cliente = new Cliente(idCliente, nombre, email);
                insertCliente(cliente, conn);

            }

            System.out.println("Clientes insertados");

            //Facturas
            for (CSVRecord row : getData("facturas.csv")) {
                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idClientef = Integer.parseInt(row.get("idCliente"));
                Factura factura = new Factura(idFactura, idClientef);
                insertFactura(factura, conn);

            }

            System.out.println("Facturas insertadas");

            //Productos
            for (CSVRecord row : getData("productos.csv")) {
                int idProducto = Integer.parseInt(row.get("idProducto"));
                String nombre = row.get("nombre");
                Float valor = Float.parseFloat(row.get("valor"));
                Producto producto = new Producto(idProducto, nombre, valor);
                insertProducto(producto, conn);

            }

            System.out.println("Productos insertados");

            //Facturas_Productos
            for (CSVRecord row : getData("facturas-productos.csv")) {
                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idProducto = Integer.parseInt(row.get("idProducto"));
                int cantidad = Integer.parseInt(row.get("cantidad"));
                Factura_Producto factura_Producto = new Factura_Producto(idFactura, idProducto, cantidad);
                insertFactura_Producto(factura_Producto, conn);

            }

            System.out.println("Facturas-Productos insertados");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int insertCliente(Cliente cliente, Connection conn) throws Exception {
        String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insert)) {

            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar el cliente.");
            }
            conn.commit();
            return 0;
        }
    }


    public int insertFactura(Factura factura, Connection conn) throws Exception {
        String query = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, factura.getIdFactura()); // idFactura
            ps.setInt(2, factura.getIdCliente()); // idCliente
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar la factura.");
            }
        }
        conn.commit();
        return 0;
    }

    public int insertProducto(Producto producto, Connection conn) throws Exception {
        String query = "INSERT INTO Producto (idProducto,nombre,valor) VALUES (?,?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, producto.getIdProducto()); // idProducto
            ps.setString(2, producto.getNombre()); // nombre
            ps.setFloat(3, producto.getValor()); // valor
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar el producto.");
            }
        }
        conn.commit();
        return 0;
    }

    public int insertFactura_Producto(Factura_Producto facturaProducto, Connection conn) throws Exception {
        String query = "INSERT INTO Factura_Producto (idFactura, idProducto,cantidad) VALUES (?,?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, facturaProducto.getIdFactura()); // idFactura
            ps.setInt(2, facturaProducto.getIdProducto()); // idProducto
            ps.setInt(3, facturaProducto.getCantidad()); // cantidad

            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar la Factura_Producto.");
            }
        }
        conn.commit();
        return 0;
    }

    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        if (conn != null) {
            try {
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
