/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.bank.BLL;

/**
 *
 * @author Brandon Fernandez
 */
public class Movimientos {

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(int idMoneda) {
        this.idMoneda = idMoneda;
    }

    private String monedaTipo, fecha, descripcion;
    private double monto;
    private int id,idMoneda,idCuenta;
    Moneda moneda;

    public Movimientos() {

    }

    public Movimientos(String monedaTipo, String fecha, String descripcion, double monto) {
        this.monedaTipo = monedaTipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;

    }
    public Movimientos(int id,String monedaTipo, String fecha, String descripcion, double monto) {
        this.monedaTipo = monedaTipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
        this.id=id;

    }

   
    @Override
    public String toString() {
        return "Movimientos{" + "monedaTipo=" + monedaTipo + ", fecha=" + fecha + ", descripcion=" + descripcion + ", monto=" + monto + ", moneda=" + moneda + '}';
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonedaTipo() {
        return monedaTipo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMonedaTipo(String monedaTipo) {
        this.monedaTipo = monedaTipo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

}

//.De los movimientos se ocupa saber el monto, monedatipo, fecha y descripción.
