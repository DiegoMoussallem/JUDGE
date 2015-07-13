package helpdesk.bean;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DIEG0
 */

import java.io.*;
import java.net.*;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.sun.media.sound.InvalidFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DIEG0
 */
public class Dicionario {
    
    public String DicionarioPt(String palavra) throws IOException, ParseException {
        String resultado = "";
	POSModel model = new POSModelLoader()	
		.load(new File("/home/aksw/NetBeansProjects/HelpDesk/pt-pos-perceptron.bin"));
	PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
	POSTaggerME tagger = new POSTaggerME(model);
	ObjectStream<String> lineStream = new PlainTextByLineStream(
			new StringReader(palavra));
 
	perfMon.start();
	String line;
	while ((line = lineStream.read()) != null) {
 
		String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
				.tokenize(line);
		String[] tags = tagger.tag(whitespaceTokenizerLine);
 
		POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
                resultado = tags[0];

		perfMon.incrementCounter();
	
	perfMon.stopAndPrintFinalResult();   
        
}
        return resultado;
    }
        
    public String DicionarioEn(String palavra) throws IOException, ParseException {

        String resultado = "";
	POSModel model = new POSModelLoader()	
		.load(new File("/home/aksw/NetBeansProjects/HelpDesk/en-pos-maxent.bin"));
	PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
	POSTaggerME tagger = new POSTaggerME(model);
	ObjectStream<String> lineStream = new PlainTextByLineStream(
			new StringReader(palavra));
 
	perfMon.start();
	String line;
	while ((line = lineStream.read()) != null) {
 
		String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
				.tokenize(line);
		String[] tags = tagger.tag(whitespaceTokenizerLine);
 
		POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
                resultado = tags[0];

		perfMon.incrementCounter();
	
	perfMon.stopAndPrintFinalResult();   
        
}
        return resultado;
              
}
}
    
