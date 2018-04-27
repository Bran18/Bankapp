/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.bank.BLL;

import src.bank.BD.Conector;


/**
 *
 * @author Brandon Fernandez
 */
public class MultiMoneda {
    
    
        public Moneda insertMoneda (String nombreMone,String simbolo ) throws
            java.sql.SQLException, Exception {
        Moneda mone = null;
        String sql;

        sql = "INSERT INTO moneda "
                + "VALUES ('" + nombreMone + "','" + simbolo + "');";

        try {

            Conector.getConector().ejecutarSQL(sql);
            mone = new Moneda(nombreMone,simbolo);

            //con.close();
        } catch (Exception ex) {
            throw new Exception("No ha sido posible ingresar la moneda, por favor ingrese otro");
        }
        return mone;
    }

    
}
