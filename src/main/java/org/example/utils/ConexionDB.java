/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mateo
 */
public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/red_sismica_db";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    private static Connection con;

    public static Connection getConnection() {
        if (ConexionDB.con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                ConexionDB.con = DriverManager.getConnection(ConexionDB.URL, ConexionDB.USER, ConexionDB.PASSWORD);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ConexionDB.con;
    }
}

