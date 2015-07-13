
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DIEG0
 */
/**
 * Tutorial 3 Statement attribute accessor methods
 */
package helpdesk.bean;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Onto extends Object {

    static final String inputFileName = "car.owl";

    public String OntoPt(String palavra) {

        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName + " not found");
        }

        model.read(in, "");

        String pesquisa = "vazio";
        String resultado;
        String idioma = "'en'";
        StmtIterator iter = model.listStatements();

        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();         // get next statement
            Resource subject = stmt.getSubject();   // get the subject
            Property predicate = stmt.getPredicate(); // get the predicate
            RDFNode object = stmt.getObject();    // get the object


            if (object.toString().contains(palavra)) {
                pesquisa = "<".concat(subject.toString()).concat(">");
            }

        }

        if (!"vazio".equals(pesquisa)) {

            String queryString;
            queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> SELECT "
                    + "?prefLabel WHERE {".concat(pesquisa) + " skos:prefLabel ?prefLabel. "
                    + "FILTER (lang(?prefLabel) = ".concat(idioma) + ")}";

            Query query = QueryFactory.create(queryString);

            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet results = qe.execSelect();

            resultado = ResultSetFormatter.asText(results);
            String[] partes = resultado.split(" ", -1);

            for (int i = 0; i < partes.length; i++) {
                if (partes[i].contains("@")) {
                    resultado = partes[i];
                }
            }
            qe.close();
            Pattern p = Pattern.compile(".*\\\"(.*)\\\".*");

            Matcher s = p.matcher(resultado);
            String refinado = "";

            while (s.find()) {
                refinado = s.group(1);
            }

            System.out.println(refinado);


            palavra = refinado;
        }
        return palavra;
    }

    public String OntoEn(String palavra) {

        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName + " not found");
        }

        model.read(in, "");

        String pesquisa = "vazio";
        String resultado;
        String idioma = "'pt'";
        StmtIterator iter = model.listStatements();

        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();         // get next statement
            Resource subject = stmt.getSubject();   // get the subject
            Property predicate = stmt.getPredicate(); // get the predicate
            RDFNode object = stmt.getObject();    // get the object


            if (object.toString().contains(palavra)) {
                pesquisa = "<".concat(subject.toString()).concat(">");
            }

        }

        if (!"vazio".equals(pesquisa)) {
            String queryString;
            queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> SELECT "
                    + "?prefLabel WHERE {".concat(pesquisa) + " skos:prefLabel ?prefLabel. "
                    + "FILTER (lang(?prefLabel) = ".concat(idioma) + ")}";

            Query query = QueryFactory.create(queryString);

            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet results = qe.execSelect();

            resultado = ResultSetFormatter.asText(results);
            String[] partes = resultado.split(" ", -1);

            for (int i = 0; i < partes.length; i++) {
                if (partes[i].contains("@")) {
                    resultado = partes[i];
                }
            }
            qe.close();

            Pattern p = Pattern.compile(".*\\\"(.*)\\\".*");

            Matcher s = p.matcher(resultado);
            String refinado = "";

            while (s.find()) {
                refinado = s.group(1);
            }
            palavra = refinado;
        }

        return palavra;
    }
}