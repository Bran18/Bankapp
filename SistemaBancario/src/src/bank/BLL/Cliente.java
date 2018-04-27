/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.bank.BLL;

import java.util.ArrayList;

/**
 *
 * @author Brandon Fernandez
 */
public class Cliente {

    private String identificacion, nombre, nombreUsuario, password;
    private int id;
    private ArrayList<Cuenta> listaCuenta;

    public Cliente() {

    }

    public Cliente(String pnombre, int id) {
        setNombre(pnombre);
        setId(id);

    }

    public Cliente(int id, String identificacion, String nombre, String nombreUsuario, String password) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.id = id;
    }

    public Cliente(String identificacion, String nombre, String nombreUsuario, String password) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.listaCuenta = new ArrayList<>();

    }

    @Override
    public String toString() {
        return "Cliente{" + "identificacion=" + identificacion + ", nombre=" + nombre + ", nombreUsuario=" + nombreUsuario + ", password=" + password + ", cuentas=" + '}';
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCuentas(Cuenta cuentas) {
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Cuenta> getCuentas() {
        return listaCuenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Cuenta> getListaCuenta() {
        return listaCuenta;
    }

    public void setListaCuenta(ArrayList<Cuenta> listaCuenta) {
        this.listaCuenta = listaCuenta;
    }

}

//Del cliente se tiene la siguiente información la identificación, el nombre (incluye el nombre 
//completo), el nombre de usuario (que es con el 
//que se identificará en la aplicación al hacer 
//login) y la clave.  Un cliente puede tener más de una cuenta.
