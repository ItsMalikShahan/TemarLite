package com.example.practise.Fragment.CreateProfileFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.practise.DB.DBManager;
import com.example.practise.R;

public class Contact extends Fragment {
      //  implements View.OnClickListener {

    Spinner country, state, city;
    Context context;
    Button save;
    EditText email, mobileNumber, address, telephone;
    View v;
    DBManager dbManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_contact, container, false);
        v = inflater.inflate(R.layout.fragment_create_contact, container, false);
        
        
        this.country = v.findViewById(R.id.spinner1);
        this.state = v.findViewById(R.id.spinner2);
        this.city = v.findViewById(R.id.spinner3);
        email = v.findViewById(R.id.et_cEmail);
        mobileNumber = v.findViewById(R.id.et_cphone);
        address = v.findViewById(R.id.et_caddress);
        save = v.findViewById(R.id.btn_save);
        telephone = v.findViewById(R.id.et_tele);
        
        
        String[] country = new String[]{"Pakistan", "Afghanistan"};
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, country);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        this.country.setAdapter(countryAdapter);
        String[] state = new String[]{"Punjab", "Sindh", "Balochistan"};
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, state);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        this.state.setAdapter(stateAdapter);
        String[] city = new String[]{"Rawalpindi", "Islamabad", "Karachi", "Lahore"};
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, city);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        this.city.setAdapter(cityAdapter);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.practise.Fragment.ProfileViewFragments.Contact contact = new com.example.practise.Fragment.ProfileViewFragments.Contact();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.rl_finalContact, contact).addToBackStack("personal").commit();
            }
        });
        return v;


    }

    

}

















