package com.lnyapps.geneticsandevolution.selftestquiz;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.EditText;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lnyapps.geneticsandevolution.R;

import org.json.JSONObject;

/**
 * Created by RussellM on 7/06/2016.
 */
public class InstallDialogFragment extends DialogFragment implements TextView.OnEditorActionListener,UpdateQuestionsListener, HTMLReaderListener {

    private TextView tView;
    private Button   installBut;
    private EditText et;
    private Button   cancelBut;
    private ProgressBar prog;

    private String quizText;

    public InstallDialogListener delegate;

    private TextWatcher filterTextWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (s.length() == 5) {
                checkCode();
            }
            else {
                installBut.setEnabled(false);
                tView.setText(getString(R.string.stq_enterMessage));
           }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_installquiz, container);

        tView = (TextView) view.findViewById(R.id.stq_installQuizNameView);
        installBut = (Button)view.findViewById(R.id.stq_installInstallButton);
        et = (EditText) view.findViewById(R.id.stq_installCodeEditText);
        cancelBut = (Button)view.findViewById(R.id.stq_installCancelButton);
        prog = (ProgressBar)view.findViewById(R.id.stq_installProgressBar);

        tView.setText(getString(R.string.stq_enterMessage));
        getDialog().setTitle(getString(R.string.stq_installDialogTitle));

        et.setOnEditorActionListener(this);
        et.addTextChangedListener(filterTextWatcher);

        et.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        cancelBut.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                hideKeyboard();
                dismiss();
            }

        });

         installBut.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                attemptInstall();
            }

        });

        return view;
    }

    public void checkCode() {
        String part1 = getString(R.string.stq_quizCodeRetrieveURL); //"http://quizupdate.genevol.com/testQuizCode.php?quizCode=";
        String part2 = ""; //String part2 = "&quizOnly=Y";
        String code = et.getText().toString();

        HTMLReader h = new HTMLReader(part1 + code + part2,this);
        String[] res = h.checkConnection(getActivity());
        if (res[0].equals(HTMLReader.CONNECT_OK)) {
            prog.setVisibility(View.VISIBLE);
            tView.setText(getString(R.string.stq_installValidating));
            h.readHTML(getActivity());
       }
       else {
            tView.setText(getString(R.string.stq_connection_error));
        }


    }
    public void attemptInstall() {
        String part1 = getString(R.string.stq_quizCodeRetrieveURL); //"http://quizupdate.genevol.com/testQuizCode.php?quizCode=";
        String part2 = "&quizOnly=Y";
        String code = et.getText().toString();

        UpdateQuestions updateQThread = new UpdateQuestions(getActivity(),part1 + code + part2);
        updateQThread.delegate = InstallDialogFragment.this;
        updateQThread.start();
        prog.setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            hideKeyboard();
            return true;
        }
        return false;
    }

    public void hideKeyboard() {
        InputMethodManager inputManager =
                (InputMethodManager)getActivity().
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                getView().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);


    }

    @Override
    public void onCancel(DialogInterface dialog) {
        hideKeyboard();
    }

    //Callback from HTMLReaderListener
    public void htmlReadFinish(String html,String errCode,String errMsg) {
        prog.setVisibility(View.INVISIBLE);

        if (errCode.equals(HTMLReader.READ_OK)) {

        }
        else {
            tView.setText("Quiz Code not found. error code: " + errCode + " reason: " + errMsg);
            return;
        }

        try {
            JSONObject json = new JSONObject(html);
            String jsonErr = json.getString("error");
            if (jsonErr.equals("")) {
                JSONObject quizItself = json.getJSONObject("quizQuiz");
                quizText = json.getString("quizText");
                installBut.setEnabled(true);
                tView.setText("Found: " + quizText);
            }
            else if (jsonErr.equals("NOK error: code not found"))  {
                tView.setText(jsonErr);
            }
            else {
                tView.setText("Quiz invalid format: " + jsonErr);
            }

        }
        catch (org.json.JSONException e) {
            tView.setText("Finished checking. json exception: " + e.toString() + " html: " + html);
        }
    }


    //Callback from UpdateQuestionsListener
    public void quizUpdated(String errMsg) {
        prog.setVisibility(View.INVISIBLE);

        if (errMsg.equals("")) {
            tView.setText("Quiz Installed ok");
            delegate.quizInstalled(quizText);
            hideKeyboard();
            dismiss();

        }
        else {
            tView.setText("Quiz Install error: " + errMsg);
        }

    }



}
