package com.airo.android.registrationlist;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.UUID;


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
    private FloatingActionButton mSendBtn;

    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = OneUser.getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registration, container, false);

        Animation animationLeftToRight = AnimationUtils.loadAnimation(v.getContext(), R.anim.mytrans_left_to_right);
        Animation animationRightToLeft = AnimationUtils.loadAnimation(v.getContext(), R.anim.mytrans_right_to_left);

        mFullnameField = (EditText) v.findViewById(R.id.fullname);
        mFullnameField.startAnimation(animationLeftToRight);
        mFullnameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                user.setFullname(c.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable c) {
            }
        });

        mBirthField = (TextView) v.findViewById(R.id.birth);
        mBirthField.startAnimation(animationRightToLeft);
        mBirthField.setText(android.text.format.DateFormat.format("yyyy-MM-dd", user.getBirth()));
        mBirthField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(user.getBirth());
                dialog.setTargetFragment(FragmentRegistration.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mEmailField = (EditText) v.findViewById(R.id.email);
        mEmailField.startAnimation(animationLeftToRight);
        mEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                user.setEmail(c.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable c) {
            }
        });

        mUsernameField = (EditText) v.findViewById(R.id.username);
        mUsernameField.startAnimation(animationRightToLeft);
        mUsernameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                user.setUsername(c.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable c) {
            }
        });


        mPasswordField = (EditText) v.findViewById(R.id.password);
        mPasswordField.startAnimation(animationLeftToRight);
        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                user.setPassword(c.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable c) {
            }
        });

        mSendBtn = (FloatingActionButton) v.findViewById(R.id.send);
        mSendBtn.startAnimation(animationRightToLeft);
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidName(user.getFullname())) {
                    mFullnameField.setError("Invalid full name");
                    mFullnameField.requestFocus();
                } else if (!isValidEmail(user.getEmail())) {
                    mEmailField.setError("Invalid email");
                    mEmailField.requestFocus();
                } else if (!isValidName(user.getUsername())) {
                    mUsernameField.setError("Invalid full name");
                    mUsernameField.requestFocus();
                } else if (!isValidPassword(user.getPassword())) {
                    mPasswordField.setError("Invalid password");
                    mPasswordField.requestFocus();
                } else {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL, new String[]{user.getEmail()});
                    i.putExtra(Intent.EXTRA_SUBJECT, "Information about " + user.getFullname());
                    i.putExtra(Intent.EXTRA_TEXT, "Username: " + user.getUsername() + ". Password: " + user.getPassword() + ". Birth: " + android.text.format.DateFormat.format("yyyy-MM-dd", user.getBirth()) + ".");
                    try {
                        startActivity(Intent.createChooser(i, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(v.getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;
    }

    public final static boolean isValidName(String name) {
        if (name!=null) {
            return true;
        } else {
            return false;
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public final static boolean isValidPassword(String password) {
        if (password != null && password.length() > 9) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            user.setBirth(date);
            mBirthField.setText(android.text.format.DateFormat.format("yyyy-MM-dd", user.getBirth()));
        }
    }

}
