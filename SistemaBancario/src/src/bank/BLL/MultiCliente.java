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
public class MultiCliente {

    public Cliente insertClient(String identificacion, String nombre, String nombreUsuario, String password) throws java.sql.SQLException, Exception {
        Cliente client;
        //cuenta.setNumeroCuenta(numeroCuenta);
        //cuenta.setSaldo(saldo);
        //cuenta.setTipoMoneda(tipoMoneda);
        client = new Cliente(identificacion, nombre, nombreUsuario, password);

        String sql;

        sql = "INSERT INTO cliente(identificacion,nombre,nombreUsuario,password)"
                + " VALUES ('" + identificacion + "','" + nombre + "','" + nombreUsuario + "','" + password + "');";

        try {

            Conector.getConector().ejecutarSQL(sql);
            client = new Cliente(identificacion, nombre, nombreUsuario, password);
            ResultSet rs = Conector.getConector().ejecutarSQL("Select LAST_INSERT_ID() as id;", true);
            rs.next(); //para posicionar el puntero en la primer fila
            int id = Integer.parseInt(rs.getString("id"));
            client.setId(id);

            //con.close();
        } catch (Exception ex) {
            throw new Exception("No ha sido posible ingresar la moneda, por favor ingrese otro");
        }
        return client;
    }

    public static void listClient() {
        try {

            String sql;
            java.sql.ResultSet rs;
            sql = "SELECT * FROM cliente";
            rs = Conector.getConector().ejecutarSQL(sql, true);
            List<Map<String, String>> listClient = new ArrayList<>();
            while (rs.next()) {

                Map<String, String> dicClient = new HashMap<>();
                dicClient.put("identificacion", rs.getString("identificacion"));
                dicClient.put("nombre", rs.getString("nombre"));
                dicClient.put("nombreUsuario", rs.getString("nombreUsuario"));
                dicClient.put("password", rs.getString("password"));

                listClient.add(dicClient);

                for (Map<String, String> map : listClient) {
                    System.out.println("ID :");
                    System.out.println(rs.getString("identificacion"));
                    System.out.println(" Nombre ");
                    System.out.println(rs.getString("nombre"));
                    System.out.println("Uuserio ");
                    System.out.println(rs.getString("nombreUsuario"));
                    System.out.println("Contraseña:");
                    System.out.println(rs.getString("password"));

                }
            }
            String prueba = "it works!!";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Cliente clienteBuscarID(String identificacion) throws java.sql.SQLException, Exception {
        Cliente client = null;
        String sql;

        sql = "select identificacion,nombre,nombreUsuario,password from Cliente where identificacion ="
                + "'" + identificacion + "';";

        try {

            Conector.getConector().ejecutarSQL(sql, true);
            ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
            if (rs.next()) {
                client = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("identificacion"),
                        rs.getString("nombre"),
                        rs.getString("nombreUsuario"),
                        rs.getString("password"));

                //con.close();
            }

        } catch (Exception ex) {
            throw new Exception("No ha sido posible ingresar la moneda, por favor ingrese otro");
        }

        return client;
    }

    public Cliente clienteBuscarUsuario(String nombreUsuario) throws java.sql.SQLException, Exception {
        Cliente client = null;
        String sql;

        sql = "select idCliente,identificacion,nombre,nombreUsuario,password from Cliente where nombreUsuario ="
                + "'" + nombreUsuario + "';";

        try {

            Conector.getConector().ejecutarSQL(sql, true);
            ResultSet rs = Conector.getConector().ejecutarSQL(sql, true);
            if (rs.next()) {
                client = new Cliente(
                        rs.getInt("idCliente"),
                        rs.getString("identificacion"),
                        rs.getString("nombre"),
                        rs.getString("nombreUsuario"),
                        rs.getString("password"));

                //con.close();
            }

        } catch (Exception ex) {
            throw new Exception("No ha sido posible ingresar la moneda, por favor ingrese otro");
        }

        return client;
    }

}
