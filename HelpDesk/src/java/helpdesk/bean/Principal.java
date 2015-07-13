/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpdesk.bean;

import java.io.IOException;
import org.json.simple.parser.ParseException;

/**
 *
 * @author DIEG0
 */
public class Principal {

    public String VetorPt(String mensagem) throws IOException, ParseException, Exception {
        String mensagem2 = "";
        mensagem = mensagem.concat(".");
        Integer verificarverbo = 0;
        String[] partes = mensagem.split("[.,; ]+", -1);
        String[] verificacao = mensagem.split("[.,; ]+", -1);
        Dicionario dicionario = new Dicionario();

        for (int i = 0; i < partes.length-1; i++) {
            verificacao[i] = dicionario.DicionarioPt(partes[i]);
            if (verificacao[i].contains("v")) {
                verificarverbo = verificarverbo + 1;
            }
        }

        for (int i = 0; i < partes.length-1; i++) {

            if ("da".equals(partes[i]) && verificarverbo > 1) {

                verificacao[i] = "prp";

            }
            
            if ("do".equals(partes[i]) && verificarverbo > 1) {

                verificacao[i] = "prp";

            }
            
            if ("na".equals(partes[i])) {

                verificacao[i] = "prp";

            }
    }
        
        for (int i = 0; i < partes.length-1; i++) {

            if ("n".equals(verificacao[i])) {
                
                Onto onto = new Onto();

                String temp = onto.OntoPt(partes[i]);

                if (!(temp.equals(partes[i]))) {

                    if (i + 2 < partes.length) {

                        if (verificacao[i + 1].equals("prp") && verificacao[i + 2].equals("n")) {
                            partes[i] = partes[i];
                            continue;
                        } else if (verificacao[i + 1].equals("prp") && verificacao[i + 2].contains("pron") && verificacao[i + 3].equals("n")) {
                            partes[i] = partes[i];
                            continue;
                        }
                           
                    }
                }
                partes[i] = temp;   
            }
        }

        StringBuilder sb = new StringBuilder();
        if (partes.length > 0) {
            sb.append(partes[0]);
            for (int i = 1; i < partes.length; ++i) {
                sb.append(" ").append(partes[i]);
            }
            mensagem2 = sb.toString();

        }
        return mensagem2;
    }

    public String VetorEn(String mensagem) throws IOException, ParseException, Exception {

        String mensagem2 = "";
        mensagem = mensagem.concat(".");
        Integer verificarverbo = 0;
        String[] partes = mensagem.split("[.,; ]+", -1);
        String[] verificacao = mensagem.split("[.,; ]+", -1);
        Dicionario dicionario = new Dicionario();

        for (int i = 0; i < partes.length - 1; i++) {
            verificacao[i] = dicionario.DicionarioEn(partes[i]);

            if ("verb".equals(verificacao[i]) || "phrase".equals(verificacao[i])) {
                verificarverbo = verificarverbo + 1;
            }
        }

        for (int i = 0; i < partes.length - 1; i++) {

            if ("da".equals(partes[i]) && verificarverbo > 1) {

                verificacao[i] = "preposition";

            }

            if ("noun".equals(verificacao[i])) {
                Onto onto = new Onto();

                String temp = onto.OntoEn(partes[i]);

                if (!(temp.equals(partes[i]))) {

                    if (i + 2 < partes.length) {

                        if (verificacao[i + 1].equals("preposition") && verificacao[i + 2].equals("noun")) {
                            partes[i] = partes[i];
                            continue;
                        } else if (verificacao[i + 1].equals("preposition") && verificacao[i + 2].equals("pronoun") && verificacao[i + 3].equals("noun")) {
                            partes[i] = partes[i];
                            continue;
                        }
                    }
                }
                partes[i] = temp;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (partes.length > 0) {
            sb.append(partes[0]);
            for (int i = 1; i < partes.length; ++i) {
                sb.append(" ").append(partes[i]);
            }
            mensagem2 = sb.toString();

        }
        return mensagem2;
    }
}
