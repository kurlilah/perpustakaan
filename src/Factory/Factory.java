/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Implement.ImplementAdmin;
import Interface.InterfaceAdmin;

/**
 *
 * @author Neng M'Anis
 */
public class Factory {
    private static InterfaceAdmin adminDAO;
    
    public static InterfaceAdmin getAdminDAO(){
       adminDAO = new ImplementAdmin();
       return adminDAO;
    }
    
}
