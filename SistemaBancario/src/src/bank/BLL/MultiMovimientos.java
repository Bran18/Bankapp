/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.bank.BLL;

import src.bank.BD.Conector;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Brandon Fernandez
 */
public class MultiMovimientos {

    public void insertMovimientos(double monto, String descripcion, int idMoneda, int idCuenta) throws
            java.sql.SQLException, Exception {
        String sqlInsertarMovimiento, sqlActualizarSaldoCuenta;

        sqlInsertarMovimiento = "insert into movimiento(monto,fecha,descripcion,Moneda_idMoneda,Cuenta_idCuenta) values(" + monto + ",now(),'" + descripcion + "'," + idMoneda + "," + idCuenta + ");";
        sqlActualizarSaldoCuenta = "update cuenta set saldo = saldo + "+monto+" where idCuenta = "+ idCuenta + ";";
        //String sqlw = String.format(sql, monto,monedaTipo,fecha,descripcion,idMoneda);
        try {

            Conector.getConector().ejecutarSQL(sqlInsertarMovimiento);
            Conector.getConector().ejecutarSQL(sqlActualizarSaldoCuenta);
        } catch (Exception ex) {
            throw new Exception("No ha sido posible ingresar la moneda, por favor ingrese otro");
        }
    }

    public static List<Map<String, String>> listMove(int idCliente, int idCuenta) {
        List<Map<String, String>> listMove = new ArrayList<>();
        try {

            String sql;
            java.sql.ResultSet rs;
            sql = "select idMovimiento,monto,fecha,descripcion,c.numeroCuenta,c.tipoMoneda from Movimiento m " +
                    "inner join Cuenta c on c.idCuenta = m.Cuenta_idCuenta " +
                    "inner join Cliente cl on cl.idCliente = c.Cliente_idCliente " +
                    "where cl.idCliente = " + idCliente + " and c.idCuenta = "+ idCuenta + ";";
            rs = Conector.getConector().ejecutarSQL(sql, true);
            
            while (rs.next()) {

                Map<String, String> dicMove = new HashMap<>();
                dicMove.put("idMovimiento", rs.getString("idMovimiento"));
                dicMove.put("monto", rs.getString("monto"));
                dicMove.put("fecha", rs.getString("fecha"));
                dicMove.put("descripcion", rs.getString("descripcion"));
                dicMove.put("numeroCuenta", rs.getString("numeroCuenta"));
                dicMove.put("tipoMoneda", rs.getString("tipoMoneda"));

                listMove.add(dicMove);

            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listMove;
    }
    
    public void insertTranferencia(int idCuentaOrigen,int idMonedaOrigen, int idCuentaDestino, int idMonedaDestino, double montoDestino, double montoOrigen) throws
            java.sql.SQLException, Exception {
        String sqlInsertarMovimientoDebito,sqlInsertarMovimientoTransferencia, sqlActualizarSaldoCuentaOrigen, sqlActualizarSaldoCuentaDestino;

        sqlInsertarMovimientoDebito = "insert into movimiento(monto,fecha,descripcion,Moneda_idMoneda,Cuenta_idCuenta) values(" + montoOrigen + ",now(),'Debito'," + idMonedaOrigen + "," + idCuentaOrigen + ");";
        sqlInsertarMovimientoTransferencia = "insert into movimiento(monto,fecha,descripcion,Moneda_idMoneda,Cuenta_idCuenta) values(" + montoOrigen + ",now(),'Transferencia'," + idMonedaOrigen + "," + idCuentaDestino + ");";
        sqlActualizarSaldoCuentaOrigen = "update cuenta set saldo = saldo - "+montoOrigen+" where idCuenta = "+ idCuentaOrigen + ";";
        sqlActualizarSaldoCuentaDestino = "update cuenta set saldo = saldo + "+montoDestino+" where idCuenta = "+ idCuentaDestino + ";";
        //String sqlw = String.format(sql, monto,monedaTipo,fecha,descripcion,idMoneda);
        try {

            Conector.getConector().ejecutarSQL(sqlInsertarMovimientoDebito);
            Conector.getConector().ejecutarSQL(sqlInsertarMovimientoTransferencia);
            Conector.getConector().ejecutarSQL(sqlActualizarSaldoCuentaOrigen);
            Conector.getConector().ejecutarSQL(sqlActualizarSaldoCuentaDestino);
        } catch (Exception ex) {
            throw new Exception("No ha sido posible ingresar la moneda, por favor ingrese otro");
        }
    }

}
