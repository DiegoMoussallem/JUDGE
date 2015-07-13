/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DIEG0
 */
package helpdesk.bean;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;


public class Traducao {
   
    public String TraducaoPt(String mensagem) throws Exception{
        Translate.setClientId("Moussallem");
        Translate.setClientSecret("Sq2m3JmTIcdoA5diD2Mw4Clh6sNpoRp5e4uxcXpMwT8=");
        String translatedText = Translate.execute(mensagem, Language.PORTUGUESE, Language.ENGLISH);
  
        return translatedText;
  
   }
        public String TraducaoEn(String mensagem) throws Exception{
        Translate.setClientId("Moussallem");
        Translate.setClientSecret("Sq2m3JmTIcdoA5diD2Mw4Clh6sNpoRp5e4uxcXpMwT8=");
        String translatedText = Translate.execute(mensagem, Language.ENGLISH, Language.PORTUGUESE);

        return translatedText;

   }
}