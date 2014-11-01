package com.lnyapps.geneticsandevolution.selftestquiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Tseng on 10/31/2014.
 */
public class VocabTerm {

    private String mName;
    private String mDefinition;
    private List<String> mSimilarTerms;

    public VocabTerm(String name, String definition, List<String> similarTerms) {
        mName = name;
        mDefinition = definition;
        mSimilarTerms = new ArrayList<String>(similarTerms);
    }

    public String getName() { return mName; }

    public String getDefinition() {return mDefinition; }

    public List<String> getSimilarTerms() { return mSimilarTerms; }

    public String toString() {
        return getName() + " " + getDefinition() + " " + getSimilarTerms().toString();
    }

}
