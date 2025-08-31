package Entregable1;

import Entregable1.utils.HelperMySQL;

public class Main {

    public static void main(String[] args) throws Exception {
        HelperMySQL dbMySQL = new HelperMySQL();
        dbMySQL.dropTables();
        dbMySQL.createTables();
        dbMySQL.populateDB();
        dbMySQL.closeConnection();
    }
}
