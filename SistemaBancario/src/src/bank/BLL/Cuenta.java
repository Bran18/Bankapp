/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.bank.BLL;

import java.sql.ResultSet;
import java.util.ArrayList;
import src.bank.BD.Conector;

/**
 *
 * @author Brandon Fernandez
 */
public class Cuenta {

    @Override
    public String toString() {
        return "Cuenta{" + "numeroCuenta=" + numeroCuenta + ", tipoMoneda=" + tipoMoneda + ", saldo=" + saldo + ", id=" + id + ", movimientoCuenta=" + movimientoCuenta + ", movimientos=" + movimientos + ", cliente=" + cliente + '}';
    }

    String numeroCuenta, tipoMoneda;
    private double saldo;
    private int id;
    Movimientos movimientoCuenta;
    private ArrayList<Movimientos> movimientos = new ArrayList<>();
    private Cliente cliente = null;

    public Cuenta() {

    }

    Cuenta(String numeroCuenta, String tipoMoneda, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.tipoMoneda = tipoMoneda;
        this.saldo = saldo;

    }

    Cuenta(int id, String numeroCuenta, String tipoMoneda, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.tipoMoneda = tipoMoneda;
        this.saldo = saldo;
        this.id = id;

    }

    Cuenta(String numeroCuenta, String tipoMoneda, double saldo, int id) {
        this.numeroCuenta = numeroCuenta;
        this.tipoMoneda = tipoMoneda;
        this.saldo = saldo;
        this.id = id;

    }

    public Cliente getCliente() throws Exception {
        if (cliente == null) {
            //setCliente((new MultiCliente()).clienteBuscarID(int id));
        }
        return cliente;
    }

    public void setCliente(Cliente pcliente) {
        cliente = pcliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setMovimientoCuenta(Movimientos movimientoCuenta) {
        this.movimientoCuenta = movimientoCuenta;
    }

    public void setMovimientos(ArrayList<Movimientos> movimientos) {
        this.movimientos = movimientos;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public double getSaldo() {
        return saldo;
    }

    public Movimientos getMovimientoCuenta() {
        return movimientoCuenta;
    }

    public ArrayList<Movimientos> getMovimientos() {
        return movimientos;
    }

}

//De la cuenta debe ingresar la moneda, el número de cuenta, el saldo. Una cuenta solo puede tener un cliente como dueño 
//y solo puede tener una moneda a la cuenta se le pueden hacer transacciones o movimientos, que son de dos 
//tipos:depósitos y transferencias.  Las transferencias sonla única forma de sacar plata de la cuenta que existe

