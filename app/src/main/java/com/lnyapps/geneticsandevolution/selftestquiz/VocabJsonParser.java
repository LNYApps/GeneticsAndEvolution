package com.lnyapps.geneticsandevolution.selftestquiz;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Tseng on 10/31/2014.
 */
public class VocabJsonParser {

    public List<VocabTerm> parse(InputStream jsonStream) throws Exception {
        byte[] buffer = new byte[jsonStream.available()];
        jsonStream.read(buffer);
        jsonStream.close();
        JSONObject json = new JSONObject(new String(buffer, "UTF-8"));
        ArrayList<VocabTerm> terms = new ArrayList<VocabTerm>(readTerms(json));
        return terms;
    }

    private List<VocabTerm> readTerms(JSONObject json) throws Exception {
        ArrayList<VocabTerm> terms = new ArrayList<VocabTerm>();
        JSONArray termArray = json.getJSONArray("TermArray");

        for (int i = 0; i < termArray.length(); i++) {
            JSONObject termObject = termArray.getJSONObject(i);

            String name = (String) termObject.get("name");
            String definition = (String) termObject.get("definition");

            JSONArray similar = termObject.getJSONArray("similarTerms");
            List<String> similarTerms = new ArrayList<String>();
            for (int j = 0; j < similar.length(); j ++) {
                similarTerms.add((String) similar.get(j));
            }
            VocabTerm term = new VocabTerm(name, definition, similarTerms);
            terms.add(term);
        }
        return terms;
    }
}
