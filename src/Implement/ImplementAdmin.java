/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implement;

import Entity.Admin;
import Interface.InterfaceAdmin;
import Koneksi.KoneksiDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neng M'Anis
 */
public class ImplementAdmin implements InterfaceAdmin {
    private String query;
    private KoneksiDB koneksi;
    private boolean status;
    private ResultSet rsAdmin, rsLogin, rsKode;
    private List<Admin> listAdmin;
    
public ImplementAdmin(){
    koneksi = new KoneksiDB();
    koneksi.getKoneksi();
}

    @Override
    public boolean insertAdmin(Admin a) {
        status = false;
        query  = "insert into admin values ('" +a.getNik()+ "', '"+a.getNama()+"', "
                + "'"+a.getUsername()+"',('"+a.getPassword()+"'))";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public boolean updateAdmin(Admin a) {
        status = false;
        query  = "update admin set nama = '"+a.getNama()+"', "
                + "username = '"+a.getUsername()+"', password = ('"+a.getPassword()+"') where nik = '"+a.getNik()+"'";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public boolean deleteAdmin(String nik) {
        status = false;
        query  = "delete from admin where nik = '"+nik+"'";
        status = koneksi.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public List selectAdmin(String nik, String nama, String user) {
        query  = "select * from admin"+
                " where nik like '%"+nik+"%' or nama like '%"+nama+"%' or username like '%"+user+"%'"+
                " order by nik ASC";
        status = koneksi.eksekusiQuery(query, true);
        if (status) {
            rsAdmin = koneksi.getRs();
            listAdmin = new ArrayList<Admin>();
            try {
                while (rsAdmin.next()) {                    
                    Admin a = new Admin();
                    a.setNik(rsAdmin.getString(1));
                    a.setNama(rsAdmin.getString(2));
                    a.setUsername(rsAdmin.getString(3));
                    a.setPassword(rsAdmin.getString(4));
                    listAdmin.add(a);
                }
                rsAdmin.close();
                return listAdmin;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean LoginAdmin(String nik, String password) {
        String pass = null;
        query  = "select * from admin where nik = '"+nik+"' and password = ('"+password+"')";
        status = koneksi.eksekusiQuery(query, true);
        if (status) {
            rsLogin = koneksi.getRs();
            try {
                rsLogin.next();
                Admin a = new Admin();
                a.setNik(rsLogin.getString(1));
                a.setPassword(rsLogin.getString(4));
                pass = rsLogin.getString(4);
                rsLogin.close();
                
                if (pass.equals("")) {
                    status = false;
                }else{
                    status = true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return status;
    }

    @Override
    public String kodeAdmin() {
        String kode = "";
        int angka = 0;
        query = "select max(right(nik,4)) as kode from admin";
        status = koneksi.eksekusiQuery(query, true);
        if (status) {
            rsKode = koneksi.getRs();
            try {
                rsKode.next();
                angka = rsKode.getInt(1);
                //B0001
                if (angka < 10) {
                    kode = "AD000" + (angka + 1);
                } else if (angka < 100) {
                    kode = "AD00" + (angka + 1);
                } else if (angka < 1000) {
                    kode = "AD0" + (angka + 1);
                } else{
                    kode = "AD" + (angka + 1);
                }
            } catch (SQLException ex) {
                return null;
            }
        }
        return kode;
    
    
    }
    
}
