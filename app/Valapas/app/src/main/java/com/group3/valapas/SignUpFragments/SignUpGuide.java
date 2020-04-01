package com.group3.valapas.SignUpFragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.group3.valapas.Activities.Customers.UserBrowseTab;
import com.group3.valapas.SignUpActivity;
import com.group3.valapas.R;

public class SignUpGuide extends Fragment
{
    private Button btnSignUp;
    private Button btnBrowse;
    private Button btnSignIn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup homepage, @Nullable final Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.sign_up_guide, homepage, false);

        btnSignUp = (Button) view.findViewById(R.id.sign_up_btn);
        btnBrowse = (Button) view.findViewById(R.id.browse_btn);
        btnSignIn = (Button) view.findViewById(R.id.sign_in_btn);

        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getActivity(), "Going to Sign Up Page!", Toast.LENGTH_SHORT).show();
                ((SignUpActivity)getActivity()).setViewPager(0);
            }
        });

        btnBrowse.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Toast.makeText(getActivity(), "Starting Valapas", Toast.LENGTH_SHORT).show();
                ((SignUpActivity)getActivity()).setViewPager(1);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getActivity(), "Signing In...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), UserBrowseTab.class);
                startActivity(intent);
            }
        });

        return view;
    }
}