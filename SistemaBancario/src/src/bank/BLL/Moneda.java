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
public class Moneda {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Moneda(String nombreMone, String simbolo) {
        this.nombreMone = nombreMone;
        this.simbolo = simbolo;
        
    }
    
     public Moneda(int id,String nombreMone, String simbolo) {
        this.nombreMone = nombreMone;
        this.simbolo = simbolo;
        this.id=id;
        
    }

    private String nombreMone, simbolo;
    private int id;

    public Moneda() {

    }

    @Override
    public String toString() {
        return "Moneda{" + "nombreMone=" + nombreMone + ", simbolo=" + simbolo + '}';
    }

    public void setNombreMone(String nombreMone) {
        this.nombreMone = nombreMone;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getNombreMone() {
        return nombreMone;
    }

    public String getSimbolo() {
        return simbolo;
    }

}

//De la moneda se sabe el nombre de la moneda, símbolo
//.La aplicación solo va a manejar dos : dólares y colonesy debe poder hacer la conversión de los valores de las 
//transferencias a la monedade la cuenta destino, mediante untipo de cambio.
