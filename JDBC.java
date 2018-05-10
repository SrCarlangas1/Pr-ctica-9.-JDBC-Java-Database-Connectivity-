/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author infor05
 */
public class JDBC {

    public static void main(String[] args) {
        try {
            boolean salir = false;
            Connection conexion = DriverManager.getConnection("jdbc:mysql://loc"
                    + "alhost:3306/beer", "root", "12345");
            conexion.setAutoCommit(false);
            while (!salir) {
                JOptionPane.showMessageDialog(null, "Bienvenidos");
                System.out.println("1. Consultar la Base de Datos");
                System.out.println("2. Update en la Base de Datos");
                System.out.println("3. Insert en la Base de Datos");
                System.out.println("4. Salir");
                System.out.print("Que opción deseas");
                Scanner sc = new Scanner(System.in);
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        JDBC.Consultar(conexion);
                        break;
                    case 2:
                        JDBC.Update(conexion);
                        break;
                    case 3:
                        JDBC.Insert(conexion);
                        break;
                    case 4:
                        salir = true;
                        JOptionPane.showMessageDialog(null, "Gracias por confia"
                                + "r en nosotros");
                }
            }
        } catch (SQLException ex) {
            System.out.println("La conexión no se ha establecido correctament"
                    + "e");
        }
    }

    public static void Consultar(Connection conexion) {
        try(Statement st = conexion.createStatement()) {
            System.out.println("1. Consultar la Base de Datos");
            ResultSet resultado;
                Scanner sc = new Scanner(System.in);
                System.out.println("Indicame la tabla");
                String tabla = sc.nextLine();
                System.out.println("El numero de campos que deseas ver");
                int campos = sc.nextInt();
                
                for (int i = 0; i < campos; i++) {                    
                    int n_campo=1;
                    System.out.println("El nombre del campo nº"+ n_campo);
                    resultado = st.executeQuery("SELECT");
                    String nombre;
                    String direccion;
                    while (resultado.next()) {
                    nombre = resultado.getString("name");
                    direccion = resultado.getString("address");
                    System.out.println(nombre);
                    System.out.println(direccion);
                    n_campo++;
                    }                    

                }
                

            conexion.commit();
            conexion.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null,
                    "La consulta no se ha encontro correctamente");
        }

    }

    public static void Update(Connection conexion) {
        System.out.println("2. Update en la Base de Datos");
        ResultSet resultado;
        try(Statement st = conexion.createStatement();) {
            
            System.out.println("Indicame el update que deseas hacer");
            Scanner sc = new Scanner(System.in);
            String query = sc.nextLine();
            int row = st.executeUpdate(query);
            conexion.commit();
            conexion.rollback();
            System.out.println("El numero de filas afectadas ha sido" + row);
            
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null,
                    "No se ha actualizado correctamente");
        }
    }

    public static void Insert(Connection conexion) {
        System.out.println("3. Insert en la Base de Datos");
        ResultSet resultado;
        try(Statement st = conexion.createStatement();) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Indicame la query que deseas hacer");
            String query = sc.nextLine();
            int update = st.executeUpdate(query);
            conexion.commit();
            conexion.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
}