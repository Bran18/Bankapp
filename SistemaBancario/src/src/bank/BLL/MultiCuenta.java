/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.bank.BLL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import src.bank.BD.Conector;

/**
 *
 * @author Brandon Fernandez
 */
public class MultiCuenta {

    public Cuenta insertCuenta(String numeroCuenta, String tipoMoneda, double saldo, int idCliente) throws java.sql.SQLException, Exception {
        Cuenta cuenta = null;
        String sql;

        sql = "INSERT INTO cuenta(numeroCuenta,tipoMoneda,saldo,Cliente_idCliente,favorita) "
                + "VALUES ('" + numeroCuenta + "','" + tipoMoneda + "'," + saldo + "," + idCliente + ",0 );";

        try {

            Conector.getConector().ejecutarSQL(sql);
            ResultSet rs = Conector.getConector().ejecutarSQL("Select LAST_INSERT_ID() as idCliente;", true);
            rs.next(); //para posicionar el puntero en la primer fila
            int id = Integer.parseInt(rs.getString("idCliente"));
            cuenta = new Cuenta(numeroCuenta, tipoMoneda, saldo, id);

            //con.close();
        } catch (Exception ex) {
            throw new Exception("No ha sido posible ingresar la moneda, por favor ingrese otro");
        }
        return cuenta;
    }

    public Cuenta buscarCuenta(String numeroCuenta) throws java.sql.SQLException, Exception {
        Cuenta cuent = null;
        String sql;

        sql = "select idCuenta,numeroCuenta,tipoMoneda,saldo from Cuenta where numeroCuenta ="
                + "'" + numeroCuenta + "';";

        try {

            Conector.getConector().ejecutarSQL(sql, true);
            ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
            if (rs.next()) {
                cuent = new Cuenta(
                        rs.getInt("idCuenta"),
                        rs.getString("numeroCuenta"),
                        rs.getString("tipoMoneda"),
                        rs.getInt("saldo"));

                //con.close();
            }

        } catch (Exception ex) {
            throw new Exception("No ha sido encontrada la cuenta, por favor registrela");
        }

        return cuent;
    }

    public int getClienteID() {
        int Cliente_idCliente;

        Cliente cliente = new Cliente();

        Cliente_idCliente = cliente.getId();

        return Cliente_idCliente;
    }

    public List<Map<String, String>> listCuenta(int idCliente) {
        List<Map<String, String>> listCuenta = new ArrayList<>();
        
        try {

            String sql;
            java.sql.ResultSet rs;
            sql = "SELECT idCuenta,numeroCuenta,saldo,tipoMoneda,Cliente_idCliente,favorita FROM cuenta WHERE Cliente_idCliente = "
                     + idCliente + ";";

            rs = Conector.getConector().ejecutarSQL(sql, true);
           // List<Map<String, String>> listCuenta = new ArrayList<>();
            while (rs.next()) {

                Map<String, String> dicCuenta = new HashMap<>();
                dicCuenta.put("idCuenta",  rs.getString("idCuenta"));
                dicCuenta.put("numeroCuenta", rs.getString("numeroCuenta"));
                dicCuenta.put("saldo", rs.getString("saldo"));
                
                dicCuenta.put("tipoMoneda", rs.getString("tipoMoneda"));
                dicCuenta.put("Cliente_idCliente", rs.getString("Cliente_idCliente"));
                dicCuenta.put("favorita", rs.getString("favorita"));

                listCuenta.add(dicCuenta);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return listCuenta;

    }
    
    public void actualizaCuentaFavorita(String numeroCuenta, int idCliente) throws java.sql.SQLException, Exception {
        String sqlBorrarCuentaFavorita, sqlActualizarCuentaFavorita;

        sqlBorrarCuentaFavorita = "update cuenta set favorita = 0 where Cliente_idCliente = " + idCliente + ";";
        sqlActualizarCuentaFavorita = "update cuenta set favorita = 1 where numeroCuenta = '" + numeroCuenta + "';";

        try {

            Conector.getConector().ejecutarSQL(sqlBorrarCuentaFavorita);
            Conector.getConector().ejecutarSQL(sqlActualizarCuentaFavorita);
            

            //con.close();
        } catch (Exception ex) {
            throw new Exception("No ha sido posible ingresar la moneda, por favor ingrese otro");
        }
    }

}
