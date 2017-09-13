/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neng M'Anis
 */
public class KoneksiDB {
    private Connection Koneksi;
    private ResultSet rs;
    private PreparedStatement ps;
    
public Connection getKoneksi(){
    if(Koneksi == null){
        try {
                Class.forName("com.mysql.jdbc.Driver");
                try {
                    String url = "jdbc:mysql://localhost:3306/dbperpustakaan";
                    Koneksi = DriverManager.getConnection(url,"root","");
                    System.out.println("Koneksi sukses");
                    } catch (SQLException ex) {
                        System.out.println("Koneksi gagal "+ex);
                        System.exit(0);
                }
            } catch (ClassNotFoundException ex){
                System.out.println("Kelas tidak ditemukan" +ex);
            }
    }
    return Koneksi;    
}

public ResultSet getRs(){
    return rs;
}

public boolean eksekusiQuery (String query, boolean status){
    try {
          ps = Koneksi.prepareStatement(query);
            if(status){
                   rs = ps.executeQuery();
            } else {
                ps.executeUpdate();
            }
                return true;
          } catch (SQLException ex){
              System.out.println("error : "+ex.getMessage());
              return false;
          }
    }

public static void main(String[] args){
    new KoneksiDB().getKoneksi();
}

}

