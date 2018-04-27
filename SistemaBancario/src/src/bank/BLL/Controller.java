/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.bank.BLL;

//import com.sun.xml.internal.ws.transport.Headers;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brandon Fernandez
 */
public class Controller {

    public void addCliente(String identificacion, String nombre, String nombreUsuario, String password) {

        try {
            Cliente client = null;
            client = (new MultiCliente().insertClient(identificacion, nombre, nombreUsuario, password));

        } catch (Exception e) {
        }
    }

    public void addCuenta(String numeroCuenta, String tipoMoneda, double saldo, int idCliente) {
        try {
            // Cliente client = new MultiCliente().clienteBuscarID(identificacion);
            //Cuenta konto = new MultiCuenta().insertCuenta(numeroCuenta, tipoMoneda, saldo, client.getId());
//            Cliente client = null;
//            client.getId();
//            idCliente= client.getId();
            Cuenta cuenta = new Cuenta();

            cuenta = (new MultiCuenta().insertCuenta(numeroCuenta, tipoMoneda, saldo, idCliente));

        } catch (Exception e) {
        }
    }

    public void addDeposito(double monto, String descripcion, int idMoneda, String numeroCuenta) {
        try {
            Cuenta cuen = (new MultiCuenta()).buscarCuenta(numeroCuenta);
            //Checks if it is colones
            if (idMoneda == 1) {
                //Checks account money type
                if (cuen.getTipoMoneda().equals("Dolares") ) {
                    double montoNuevo = monto / 572;
                    monto = montoNuevo;
                }
            }
            else{
                if (cuen.getTipoMoneda().equals("Colones")) {
                    double montoNuevo = monto * 572;
                    monto = montoNuevo;
                }
            }
            (new MultiMovimientos()).insertMovimientos(monto, descripcion, idMoneda, cuen.getId());

        } catch (Exception e) {
        }
    }

    public void addMoneda(String nombreMone, String simbolo) {
        try {
            Moneda mone = null;
            mone = (new MultiMoneda().insertMoneda(nombreMone, simbolo));

        } catch (Exception e) {
        }

    }

    public List<Map<String, String>> listMove(int idCliente, String numeroCuenta) {
        List<Map<String, String>> listMovimientos = null;
        try {
            Cuenta cuen = (new MultiCuenta()).buscarCuenta(numeroCuenta);
            int idCuenta = cuen.getId();
            listMovimientos = (new MultiMovimientos()).listMove(idCliente, idCuenta);
        } catch (Exception e) {
        }
        return listMovimientos;
    }

    //intento de listar
    public List<Map<String, String>> listCuenta(int idCliente) throws Exception {
        
        List<Map<String, String>> listCuen = new MultiCuenta().listCuenta(idCliente);
        
        return listCuen;
    }

    public TreeMap clientBuscar(String numeroCuenta) throws Exception {
        TreeMap datos = null;
        Cuenta cuenta = null;
        String saldo;
        datos = new TreeMap();
        cuenta = (new MultiCuenta()).buscarCuenta(numeroCuenta);
        datos.put("saldo", cuenta.getSaldo());
        datos.put("identificacion", cuenta.getNumeroCuenta());
        return datos;
    }

    public TreeMap clienteLogin(String nombreUsuario, String password) {
        TreeMap returnInformation = new TreeMap();
        try {
            Cliente client = null;
            client = (new MultiCliente()).clienteBuscarUsuario(nombreUsuario);
            
            if (client == null) {
                returnInformation.put("exists", 0);
            }
            else{
                returnInformation.put("exists", 1);
                if (password.equals(client.getPassword())) {
                    returnInformation.put("client",client);
                    returnInformation.put("logged",1);
                }
                else{
                    returnInformation.put("logged",0);
                }
            }
            
            
        } catch (Exception ex) {

        }

        return returnInformation;

    }
    
    public void actualizaCuentaFavorita(String numeroCuenta, int idCliente) throws java.sql.SQLException, Exception {
        try {
            (new MultiCuenta()).actualizaCuentaFavorita(numeroCuenta,idCliente);
        } catch (Exception e) {
        }
    }
    
    public void insertTranferencia(String numeroCuentaOrigen, int idMonedaOrigen, String numeroCuentaDestino, double monto){
        try {
            //(int idCuentaOrigen,int idMonedaOrigen, int idCuentaDestino, int idMonedaDestino, double montoDestino, double montoOrigen)
            
            Cuenta origen = (new MultiCuenta()).buscarCuenta(numeroCuentaOrigen);
            Cuenta destino = (new MultiCuenta()).buscarCuenta(numeroCuentaDestino);
            
            double montoOrigen = 0;
            double montoDestino = 0;
            
            int _idMonedaOrigen = 1;
            int _idMonedaDestino = 1;
            
            if (origen.getTipoMoneda().equals("Dolares")) {
                _idMonedaOrigen = 2;
            }
            
            if (destino.getTipoMoneda().equals("Dolares")) {
                _idMonedaDestino = 2;
            }
            
            if (idMonedaOrigen == 1) {
                if (origen.getTipoMoneda().equals("Dolares")) {
                    montoOrigen = monto/572;
                }
                else{
                    montoOrigen = monto;
                }
            }
            else{
                if (origen.getTipoMoneda().equals("Colones")) {
                    montoOrigen = monto*572;
                }
                else{
                    montoOrigen = monto;
                }
            }
            
            if (idMonedaOrigen == 1) {
                if (destino.getTipoMoneda().equals("Dolares")) {
                    montoDestino = monto/572;
                }
                else{
                    montoDestino = monto;
                }
            }
            else{
                if (destino.getTipoMoneda().equals("Colones")) {
                    montoDestino = monto*572;
                }
                else{
                    montoDestino = monto;
                }
            }
            
            //(int idCuentaOrigen,int idMonedaOrigen, int idCuentaDestino, int idMonedaDestino, double montoDestino, double montoOrigen)
            (new MultiMovimientos()).insertTranferencia(origen.getId(), idMonedaOrigen, destino.getId(), _idMonedaDestino, montoDestino, montoOrigen);
            
        } catch (Exception e) {
        }
    }

}
