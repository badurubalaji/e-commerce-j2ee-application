/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import java.util.List;

/**
 *
 * @author dangkhoa
 */
public class testSessionBean {
    public static void main(String args[]){
        CategoryFacade categoryFacade = new CategoryFacade();
        List list = categoryFacade.findAll();
        System.out.println(list.size());
    }
}
