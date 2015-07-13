/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DIEG0
 */
  package helpdesk.bean;
  import java.io.Serializable;
   
  public class Attendant implements Serializable {

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
   
   private String username;
   private String password;
   private String tipo = "atendente";

    public String getTipo() {
        return tipo;
    }
   
   // métodos get e set omitidos...

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
  }