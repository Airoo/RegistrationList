package com.airo.android.registrationlist;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegistration extends Fragment {

    private User user;
    private EditText mFullnameField;
    private TextView mBirthField;
    private EditText mUsernameField;
    private EditText mEmailField;
    private EditText mPasswordField;

    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registration, container, false);

        Animation animationLeftToRight = AnimationUtils.loadAnimation(v.getContext(), R.anim.mytrans_left_to_right);
        Animation animationRightToLeft = AnimationUtils.loadAnimation(v.getContext(), R.anim.mytrans_right_to_left);

        mFullnameField = (EditText) v.findViewById(R.id.fullname);
        mFullnameField.startAnimation(animationLeftToRight);

        mBirthField = (TextView) v.findViewById(R.id.birth);
        mBirthField.startAnimation(animationRightToLeft);

        mEmailField = (EditText) v.findViewById(R.id.email);
        mEmailField.startAnimation(animationLeftToRight);

        mUsernameField = (EditText) v.findViewById(R.id.username);
        mUsernameField.startAnimation(animationRightToLeft);

        mPasswordField = (EditText) v.findViewById(R.id.password);
        mPasswordField.startAnimation(animationLeftToRight);

        /*mFullnameField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityGame.class);
                startActivity(intent);
            }
        });*/

        //mBirthField.setText(android.text.format.DateFormat.format("yyyy-MM-dd", user.getBirth()));
       /* mBirthField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(user.getBirth());
                dialog.setTargetFragment(FragmentRegistration.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });*/
        return v;
    }

}
