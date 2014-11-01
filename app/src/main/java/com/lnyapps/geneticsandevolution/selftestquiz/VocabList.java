package com.lnyapps.geneticsandevolution.selftestquiz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jonathan Tseng on 10/31/2014.
 */
public class VocabList {

    private int myIndex;
    private List<VocabTerm> mTerms;
    private List<VocabQuestion> mQuestions;

    public VocabList(Collection<VocabTerm> terms) {
        mTerms = new ArrayList<VocabTerm>(terms);
        shuffle();
    }

    public void shuffle() {
        createQuestions();
        randomize();
        myIndex = -1;
    }

    public VocabQuestion getNextQuestion() {
        myIndex = (myIndex == mQuestions.size() - 1) ? 0 : myIndex + 1;
        return mQuestions.get(myIndex);
    }

    private void createQuestions() {
        mQuestions = new ArrayList<VocabQuestion>();
        for (VocabTerm term : mTerms) {
            mQuestions.add(createQuestion(term));
        }
    }

    private VocabQuestion createQuestion(VocabTerm term) {
        List<String> choices = new ArrayList<String>(term.getSimilarTerms());
        if (choices.size() > 3) {
            Collections.shuffle(choices);
            choices = choices.subList(0, 3);
        } else {
            int size = choices.size();
            for (int i = 0; i < 3 - size; i ++) {
                String randomTerm;
                while (true) {
                    randomTerm = mTerms.get((int) (Math.random() * mTerms.size())).getName();
                    if (!choices.contains(randomTerm)) {
                        choices.add(randomTerm);
                        break;
                    }
                }
            }
        }
        choices.add(term.getName());
        Collections.shuffle(choices);
        VocabQuestion question = new VocabQuestion(term.getDefinition(), term.getName(), choices);
        return question;
    }

    private void randomize() {
        Collections.shuffle(mQuestions);
    }

}
