/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DIEG0
 */
package helpdesk.view.managedbean;

import helpdesk.bean.Chat;
import helpdesk.bean.Message;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "chatMb")
@SessionScoped
public class ChatBean implements Serializable {

    @ManagedProperty(value = "#{managerLocatorMb}")
    private HelpDeskManagerLocatorBean managerLocator;
    private String message;
    private String chatId;
    private String userName;
    private String tipo;
    private String original;

    public String getTipo() {
        return tipo;
    }
    
    public String getOriginal(){
        return original;
    }

    public void setOriginal(String original){
        this.original = original;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    private int lastMessage = 0;

    public int getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(int lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void retrieveLastMessage() {

        try {
            RequestContext context = RequestContext.getCurrentInstance();
            if (isReady()) {
                int lastMessage2 = getLastMessage();
                Message message2 = getChat().getMessage(lastMessage2);
                if (message2 != null) {
                    context.addCallbackParam("newMessage", true);
                    context.addCallbackParam("message", message2);
                    setLastMessage(lastMessage2 + 1);
                }
            } else {
                context.addCallbackParam("close", true);
            }
        } catch (Throwable e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public HelpDeskManagerLocatorBean getManagerLocator() {
        return managerLocator;
    }

    public void setManagerLocator(HelpDeskManagerLocatorBean managerLocator) {
        this.managerLocator = managerLocator;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // métodos get e set omitidos.
    public boolean isReady() {
        return managerLocator.getManager().hasChat(getChatId());
    }

    public Chat getChat() {
        return managerLocator.getManager().getChat(getChatId());
    }

    public void close() {
        managerLocator.getManager().closeChat(getChatId());
        reset(null, null, null);
    }

    public void sendMessage() throws Exception {
        getChat().addMessage(getMessage(), getUserName(), getTipo(), getOriginal());
        setMessage("");
    }

    public List<Message> getMessages() {

        List<Message> result = null;

        if (isReady()) {
            result = getChat().getMessages();
        }

        return result;
    }

    public void reset(String chatId, String userName, String tipo) {
        setLastMessage(0);
        setChatId(chatId);
        setUserName(userName);
        setMessage("");
        setTipo(tipo);
    }
}