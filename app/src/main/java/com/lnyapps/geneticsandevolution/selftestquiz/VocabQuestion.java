package com.lnyapps.geneticsandevolution.selftestquiz;

import java.util.List;

/**
 * Created by Jonathan Tseng on 10/31/2014.
 */
public class VocabQuestion {

    private String mQuestion;
    private String mAnswer;
    private String[] mChoices;

    public VocabQuestion(String question, String answer, List<String> choices) {
        mQuestion = question;
        mAnswer = answer;
        mChoices = new String[4];
        for (int i = 0; i < 4; i ++) {
            mChoices[i] = "";
        }
        for (int i = 0; i < choices.size(); i ++) {
            if (i < 4) {
                mChoices[i] = choices.get(i);
            } else {
                mChoices[3] = choices.get(i);
            }
        }
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public String[] getChoices() {
        return mChoices;
    }

}
