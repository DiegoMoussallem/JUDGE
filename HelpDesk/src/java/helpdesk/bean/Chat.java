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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Chat implements Serializable {

    int tempo = 1;
    String[] temporarioPt = new String[5];
    String[] temporarioEn = new String[5];
    String[] temporario = new String[100];
    String[] temporario2 = new String[100];
    String[] temporario3 = new String[100];
    String original = "";
    private List<Message> messages = Collections.synchronizedList(new ArrayList<Message>());

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(String messageText, String author, String tipo, String original) throws Exception {


        if ("usuario".equals(tipo)) {

            if (tempo > 4) {
                tempo = 1;
            }
            temporarioPt[tempo] = messageText;
            original = messageText;

            if (tempo <= 4) {

                temporario = messageText.split("[,;. ]+", -1);
                if (temporarioPt[tempo - 1] == null) {
                    temporario2 = "vetor vazio".split("[,;. ]+", -1);
                } else {
                    temporario2 = temporarioPt[tempo - 1].split("[,;. ]+", -1);
                }
                if (temporarioEn[tempo - 1] == null) {
                    temporario3 = "vetor vazio".split("[,;. ]+", -1);
                } else {
                    temporario3 = temporarioEn[tempo - 1].split("[,;. ]+", -1);
                }

                for (int i = 0; i < temporario.length; i++) {
                    for (int j = 0; j < temporario2.length; j++) {
                        if (temporario[i].equals(temporario2[j])) {
                            temporario[i] = temporario3[j];
                            continue;
                        }
                    }

                }

                StringBuilder sb = new StringBuilder();
                if (temporario.length > 1) {
                    sb.append(temporario[0]);
                    for (int i = 1; i < temporario.length; ++i) {
                        sb.append(" ").append(temporario[i]);
                    }
                    messageText = sb.toString();

                }
            }

            Principal principal = new Principal();
            String consultado = principal.VetorPt(messageText);
            Traducao traducao = new Traducao();
            String traduzido = traducao.TraducaoPt(consultado);
            Message message = new Message();
            message.setContent(traduzido);
            message.setAuthor(author);
            message.setDate(new Date());
            message.setTipo(tipo);
            message.setOriginal(original);

            messages.add(message);
            temporarioEn[tempo] = traduzido;
            tempo = tempo + 1;

        } else if ("atendente".equals(tipo)) {

            if (tempo > 4) {
                tempo = 1;
            }

            temporarioEn[tempo] = messageText;
            original = messageText;

            if (tempo <= 4) {

                temporario = messageText.split("[,;. ]+", -1);
                if (temporarioEn[tempo - 1] == null) {
                    temporario2 = "vetor vazio".split("[,;. ]+", -1);
                } else {
                    temporario2 = temporarioEn[tempo - 1].split("[,;. ]+", -1);
                }
                if (temporarioPt[tempo - 1] == null) {
                    temporario3 = "vetor vazio".split("[,;. ]+", -1);
                } else {
                    temporario3 = temporarioPt[tempo - 1].split("[,;. ]+", -1);
                }

                for (int i = 0; i < temporario.length; i++) {
                    for (int j = 0; j < temporario2.length; j++) {
                        if (temporario[i].equals(temporario2[j])) {
                            temporario[i] = temporario3[j];
                            continue;
                        }
                      
                    }

                }

                StringBuilder sb = new StringBuilder();
                if (temporario.length > 1) {
                    sb.append(temporario[0]);
                    for (int i = 1; i < temporario.length; ++i) {
                        sb.append(" ").append(temporario[i]);
                    }
                    messageText = sb.toString();

                }
            }

            Principal vetor = new Principal();
            String consultado = vetor.VetorEn(messageText);
            Traducao traducao = new Traducao();
            String traduzido = traducao.TraducaoEn(consultado);

            Message message = new Message();
            message.setContent(traduzido);
            message.setAuthor(author);
            message.setDate(new Date());
            message.setTipo(tipo);
            message.setOriginal(original);

            messages.add(message);
            temporarioPt[tempo] = traduzido;
            tempo = tempo + 1;
        }
    }

    public Message getMessage(int id) {

        Message result = null;
        if (messages.size() > id) {
            result = messages.get(id);
        }

        return result;
    }
}