package com.example.taller_4.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taller_4.Models.Persona;
import com.example.taller_4.R;

public class My_Persona extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my__persona, container, false);
        setupUI(view);
        return view;
    }

    public void setupUI (final View view)
    {
        final EditText wEditNombre = (EditText)  view.findViewById(R.id.edit_nombre);
        final EditText wEditApellido = (EditText)  view.findViewById(R.id.edit_apellido);
        final EditText wEditMail = (EditText)  view.findViewById(R.id.edit_email);
        final EditText wEditTelefono = (EditText)  view.findViewById(R.id.edit_telefono);

        Button btnNext = (Button) view.findViewById(R.id.button_tomardatos);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui va el codigo
                String wNombre = wEditNombre.getText().toString();
                String wApellido = wEditApellido.getText().toString();
                String wMail = wEditMail.getText().toString();
                String wTelefono = wEditTelefono.getText().toString();

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                FrgListView lstPersonas = new FrgListView();
                Bundle bundle = new Bundle();

                Persona objPersona = new Persona(wNombre,wApellido,wMail,wTelefono);   //Our Persona Object
                bundle.putSerializable("Persona", objPersona);
                lstPersonas.setArguments(bundle);
                ft.replace(android.R.id.content, lstPersonas);
                ft.addToBackStack(null);     //Add fragment in back stack
                ft.commit();

            }
        });
    }
}
