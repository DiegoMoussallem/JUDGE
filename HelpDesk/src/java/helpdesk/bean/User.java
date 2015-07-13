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

   
  public class User implements Serializable {
      
   private String name;
   private String email = "";
   private String tipo = "usuario";

    public void setName(String name) {
        this.name = name;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   

    public String getTipo() {
        return tipo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
   
   public User() {
   }
   
   public User(String name, String email) {
    this.name = name;
    this.email = email;
   }
   
   // métodos get e set omitidos...
   
  }
