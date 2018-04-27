/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.bank.ui;

import src.bank.BLL.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Brandon Fernandez
 */
public class UI {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out;
    static Controller controller = new Controller();
    private static String numeroCuenta;

    private static Cliente client;

    public static void main(String[] args) throws IOException, Exception {
        int opc;
        boolean noSalir = true;

        do {
            mostrarMenu();
            opc = leerOpcion();
            noSalir = ejecutarAccion(opc);
        } while (noSalir);

    }

    static void mostrarMenu() {
        out.println();
        out.println("Menu de opciones del Sistema Software Celestial");
        out.println();
        out.println("1.  Login.");
        out.println();
        out.println("2.  Crear Cuenta.");
        out.println();
    }

    static int leerOpcion() throws IOException {
        int opcion;

        out.print("Selecciones su opcion: ");
        opcion = Integer.parseInt(in.readLine());
        out.println();

        return opcion;
    }

    public static boolean ejecutarAccion(int popcion) throws IOException, Exception {
        boolean noSalir = true;
        switch (popcion) {

            case 1:
                login();

                break;

            case 2:
                registarCliente();
                break;

            case 6:
                noSalir = false;

            default:
                out.println("Opcion Invalidad");
                out.println();
                break;
        }
        return noSalir;
    }

    public static void menuModificar() throws IOException, Exception {

        int opc;
        boolean noSalir = true;

        do {
            mostrarMenuMod();
            opc = leerOpcionMod();
            noSalir = ejecutarAccionMod(opc);
        } while (noSalir);

    }

    static void mostrarMenuMod() {
        out.println();
        out.println("Menu de opciones del sistema");
        out.println();
        //out.println("1.  Registrar nuevo cliente");
        out.println("2.  Registrar nueva cuenta.");
        out.println("3.  Ver mis cuentas personales.");
        out.println("4.  Registar cuenta Fav");
        out.println("5.  Realizar un deposito");
        out.println("6.  Realizar una Transferencia");
        out.println("7.  Listar Movimientos");
        out.println("8.  Volver al Login");
        out.println("9.  Salir.");
        out.println();
    }

    static int leerOpcionMod() throws IOException {
        int opcion;

        out.print("Selecciones su opcion: ");
        opcion = Integer.parseInt(in.readLine());
        out.println();

        return opcion;
    }

    public static boolean ejecutarAccionMod(int popcion) throws IOException, Exception {
        boolean noSalir = true;
        switch (popcion) {

            case 1:

                //registarCliente();

                break;

            case 2:
                registarCuenta();

                break;

            case 3:
                listCuenta();
                in.readLine();

                break;

            case 4:
                actualizarCuentaFavorita();
                break;

            case 5:
                realizarDepo();
                break;

            case 6:
                transferencia();
                break;

            case 7:
                listMove();
                break;

            case 8:
                break;
                
            case 9:
                noSalir = false;
                break;

            default:
                out.println("Opcion Invalidad");
                out.println();
                break;
        }
        return noSalir;
    }

    public static void login() throws IOException, Exception {

        String nombreUsuario, password;
        out.println("Ingrese el nombre de usuario ");
        nombreUsuario = in.readLine();
        out.println("Ingrese el password ");
        password = in.readLine();

        TreeMap resultadoLogin = controller.clienteLogin(nombreUsuario, password);
        if (resultadoLogin.get("exists").equals(1)) {

            if (resultadoLogin.get("logged").equals(1)) {

                out.println("Loggin Exitoso!!");
                client = (Cliente) resultadoLogin.get("client");
                menuModificar();

            } else {
                out.println("Loggin Fail!!");

            }

        } else {
            out.println("Debe registrar");
        }

    }

    public static void registarCliente() {

        try {

            String identificacion, nombre, nombreUsuario, password;

            out.println("Ingrese la identificacion: ");
            identificacion = in.readLine();
            out.println("ingre el nombre completo: ");
            nombre = in.readLine();
            out.println("Ingrese el nombre de usuario :");
            nombreUsuario = in.readLine();
            out.println("ingrese su contraseña :");
            password = in.readLine();

            controller.addCliente(identificacion, nombre, nombreUsuario, password);

        } catch (Exception e) {
        }

    }

    public static void registarCuenta() {

        try {

            String numeroCuenta, tipoMoneda;
            double saldo;

            out.println("Ingrese el numero de la cuenta : ");
            numeroCuenta = in.readLine();
            out.println("ingre el tipo de moneda : ");
            tipoMoneda = in.readLine();
            out.println("Ingrese el saldo de su cuenta :");
            saldo = Double.parseDouble(in.readLine());
            // out.println("ingrese su identificacion :");
            // identificacion = in.readLine();

            //controller.clienteBuscar(numeroCuenta);
            controller.addCuenta(numeroCuenta, tipoMoneda, saldo, client.getId());

        } catch (Exception e) {
        }

    }

    public static void realizarDepo() {

        try {
            String descripcion = "Deposito";
            int idMoneda;
            double monto;
            
            out.println("Ingrese numero de Cuenta  : ");
            String numeroCuenta = in.readLine();
            out.println("Ingrese 1 para depositar en Colones y 2 para depositar en Dolares  : ");
            idMoneda = Integer.parseInt(in.readLine());
            out.println("Ingrese el monto deseado:");
            monto = Double.parseDouble(in.readLine());

            controller.addDeposito(monto, descripcion, idMoneda, numeroCuenta);
            //listCuentas();

        } catch (Exception e) {
        }

    }

    public static void listCuenta() throws Exception {

        try {
            List<Map<String, String>> listCuen = controller.listCuenta(client.getId());
            for (Map<String, String> cuen : listCuen) {
                out.println("***************************************");
                out.print("Numero de Cuenta: ");
                out.println(cuen.get("numeroCuenta"));
                out.print("Saldo: ");
                out.println(cuen.get("saldo"));
                out.print("Tipo de Moneda: ");
                out.println(cuen.get("tipoMoneda"));                
                out.print("Es Favorita: ");
                if (cuen.get("favorita").equals("1")) {
                    out.println("Si");
                }
                else{
                    out.println("No");
                }                
                out.println("***************************************");
            }
        } catch (Exception e) {
        }

    }

    public static void actualizarCuentaFavorita(){
        try {
            String numeroCuenta;

            out.println("Ingrese el numero de cuenta que quiere convertir a favorita  : ");
            numeroCuenta = in.readLine();
            controller.actualizaCuentaFavorita(numeroCuenta, client.getId());
        } catch (Exception e) {
        }
    }
    
    public static void transferencia(){
        try {
            //(String numeroCuentaOrigen, int idMonedaOrigen, String numeroCuentaDestino, double monto)
            String numeroCuentaOrigen,numeroCuentaDestino;
            int idMoneda;
            double monto;
            
            out.println("Ingrese numero de Cuenta Origen : ");
            numeroCuentaOrigen = in.readLine();
            out.println("Ingrese 1 para depositar en Colones y 2 para depositar en Dolares  : ");
            idMoneda = Integer.parseInt(in.readLine());
            out.println("Ingrese numero de Cuenta Destino : ");
            numeroCuentaDestino = in.readLine();
            out.println("Ingrese el monto deseado:");
            monto = Double.parseDouble(in.readLine());
            
            controller.insertTranferencia(numeroCuentaOrigen,idMoneda,numeroCuentaDestino,monto);
        } catch (Exception e) {
        }
    }

    public static void listMove() throws IOException {
        String numeroCuenta;
        out.println("Ingrese numero de Cuenta : ");
        numeroCuenta = in.readLine();
        List<Map<String, String>> listMovimientos = controller.listMove(client.getId(),numeroCuenta);
        for (Map<String, String> mov : listMovimientos) {
            out.println("***************************************");
            out.print("Numero de Cuenta: ");
            out.println(mov.get("numeroCuenta"));
            out.print("Monto: ");
            out.println(mov.get("monto"));
            out.print("Tipo de Moneda: ");
            out.println(mov.get("tipoMoneda"));                
            out.print("Descripcion: ");
            out.println(mov.get("descripcion"));
            out.print("Fecha: ");
            out.println(mov.get("fecha"));  
            out.println("***************************************");
        }
        in.readLine();

    }

}
