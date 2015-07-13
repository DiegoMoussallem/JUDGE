/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DIEG0
 */
 package helpdesk.view.managedbean;
   
import helpdesk.bean.Attendant;
import helpdesk.bean.User;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
   
  @SessionScoped
  @ManagedBean(name = "attendantMb")
  public class AttendantBean implements Serializable {
   
   @ManagedProperty(value = "#{managerLocatorMb}")
   private HelpDeskManagerLocatorBean managerLocator;
   
   @ManagedProperty(value = "#{chatMb}")
   private ChatBean chat;
   
   private Attendant attendant = new Attendant();
   
   private boolean authenticated;

    public HelpDeskManagerLocatorBean getManagerLocator() {
        return managerLocator;
    }

    public void setManagerLocator(HelpDeskManagerLocatorBean managerLocator) {
        this.managerLocator = managerLocator;
    }

    public ChatBean getChat() {
        return chat;
    }

    public void setChat(ChatBean chat) {
        this.chat = chat;
    }

    public Attendant getAttendant() {
        return attendant;
    }

    public void setAttendant(Attendant attendant) {
        this.attendant = attendant;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
   
    // métodos get e set omitidos...
   
   public void signIn() {
   
    if (getAttendant().getUsername().equals(getAttendant().getPassword())) {
     managerLocator.getManager().registerAttendant(attendant);
     setAuthenticated(true);
    } else {
     FacesMessage message = new FacesMessage("Login/senha inválidos.");
     FacesContext.getCurrentInstance().addMessage(null, message);
    }
   }
   
   public void signOut() {
    managerLocator.getManager().unregisterAttendant(attendant);
    attendant = new Attendant();
    setAuthenticated(false);
   }
   
   public void startChat() {
    User user = managerLocator.getManager().getNextUser();
    String chatId = user.getEmail();
    managerLocator.getManager().createChat(chatId);
    chat.reset(chatId, attendant.getUsername(), attendant.getTipo());
   }
  }