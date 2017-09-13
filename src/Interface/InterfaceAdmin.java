/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entity.Admin;
import java.util.List;

/**
 *
 * @author Neng M'Anis
 */
public interface InterfaceAdmin {
    boolean insertAdmin (Admin a);
    boolean updateAdmin (Admin a);
    boolean deleteAdmin (String nik);
    List selectAdmin (String nik, String nama, String user);
    boolean LoginAdmin (String nik, String password);
    String kodeAdmin();
    
}
