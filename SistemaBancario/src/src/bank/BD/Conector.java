/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.bank.BD;

/**
 *
 * @author Brandon Fernandez
 */
public class Conector {
    //atributo de la clase	

    private static AccesoBD conectorBD = null;

    /**
     *
     * @param driver
     * @param conexion
     * @return
     * @throws java.mysql.SQLException
     * @throws Exception
     */
    public static AccesoBD getConector() throws java.sql.SQLException, Exception {
        if (conectorBD == null) {
            conectorBD = new AccesoBD("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/sistemaBancario?" + "user=root&password=");
        }
        return conectorBD;
    }

}
