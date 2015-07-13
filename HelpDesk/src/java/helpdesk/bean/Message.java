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
import java.util.Date;
   
  public class Message implements Serializable {
   
   private String author;
   private String content;
   private Date date;
   public String tipo;
   private String original;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setOriginal(String original){
        this.original = original;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
    
    public String getTipo() {
        return tipo;
    }

    public Date getDate() {
        return date;
    }
    public String getOriginal(){
        return original;
    }
   
   // métodos get e set omitidos...
  }