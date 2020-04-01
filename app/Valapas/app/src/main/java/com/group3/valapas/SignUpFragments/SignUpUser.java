package com.group3.valapas.SignUpFragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.group3.valapas.SignUpActivity;
import com.group3.valapas.R;

public class SignUpUser extends Fragment
{
    private Button btnSignUpUser;
    private Button btnSignUpCompany;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup signingin, @Nullable final Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.sign_up_user, signingin, false);

        btnSignUpUser = (Button) view.findViewById(R.id.sign_up_user_btn);
        btnSignUpCompany = (Button) view.findViewById(R.id.sign_up_company_btn);

        btnSignUpUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ((SignUpActivity)getActivity()).setViewPager(0);
            }
        });

        btnSignUpCompany.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                ((SignUpActivity)getActivity()).setViewPager(1);
            }
        });

        return view;
    }
}
