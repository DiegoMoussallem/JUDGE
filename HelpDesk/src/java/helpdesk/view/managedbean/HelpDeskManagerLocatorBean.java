/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DIEG0
 */
   
package helpdesk.view.managedbean;
   
import helpdesk.business.HelpDeskManager;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
   
  @ManagedBean(name = "managerLocatorMb")
  @ApplicationScoped
  public class HelpDeskManagerLocatorBean {
   
   private HelpDeskManager manager;
   
   public HelpDeskManagerLocatorBean() {
    manager = new HelpDeskManager();
   }
   
   public HelpDeskManager getManager() {
    return manager;
   }
  }