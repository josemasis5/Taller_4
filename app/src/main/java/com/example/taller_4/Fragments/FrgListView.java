package com.example.taller_4.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.taller_4.Adapters.PersonaAdapter;
import com.example.taller_4.Models.Persona;
import com.example.taller_4.R;

import java.util.ArrayList;

public class FrgListView extends Fragment {

    ArrayList<Persona> mPersonas = new ArrayList<Persona>();
    String TAG = "persona";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frg_list_view, container, false);
        //FirstList (view);
        //SecondList(view);
        setupUI(view);
        return view;
    }



    public void setupUI (final View view)
    {

        Button btnAgp = (Button) view.findViewById(R.id.AgregaPersona);
        btnAgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui va el codigo
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                My_Persona datosPersona = new My_Persona();
                //Bundle bundle = new Bundle();
                //datosPersona.setArguments(bundle);
                ft.replace(android.R.id.content, datosPersona);
                ft.addToBackStack(null);     //Add fragment in back stack
                ft.commit();
            }
        });
        SecondList(view);
    }

    public void cargarPersonas(){

        Bundle bundle = getArguments();
        Persona objPersona = (Persona) bundle.getSerializable("Persona");

        if (objPersona != null)
        {
            mPersonas = new ArrayList<Persona>();
            mPersonas.add(new Persona(objPersona.getNombre(),objPersona.getApellido(),objPersona.getEmail(),objPersona.getTelefono()));
            PersonaAdapter adapter = new PersonaAdapter(getContext(), R.layout.list_person_element, mPersonas);
            ListView mListview = (ListView) getActivity().findViewById(R.id.lstView2);
            mListview.setAdapter(adapter);

        }
        else
        {
            Toast.makeText(getContext(),"No se agregaron los datos",Toast.LENGTH_SHORT).show();
        }

    }

    private void FirstList(View view)
    {
       ArrayList<String> mItems = new ArrayList<String>();
        for (int i = 1; i <= 2; i++)
        {
            mItems.add("Item : " + i);
        }
        int layoutId = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getContext(), layoutId, mItems);
        ListView mListview = (ListView) view.findViewById(R.id.lstView);
        mListview.setAdapter(mAdapter);
    }

    private void SecondList(View view)
    {
        Bundle bundle = getArguments();

        if (bundle != null)
        {
            Persona clsPersona = (Persona) bundle.getSerializable("Persona");

            String Nombre =   "Nombre:    "+clsPersona.getNombre().toString();
            String Apellido = "Apellidos: "+clsPersona.getApellido().toString();
            String Email =    "Email:      "+clsPersona.getEmail().toString();
            String Telefono = "Telefono:  "+clsPersona.getTelefono().toString();

            if (clsPersona != null)
            {
                PersonaAdapter adapter = new PersonaAdapter(getContext(), R.layout.list_person_element,mPersonas);
                Persona dPersona = new Persona(Nombre, Apellido, Email,Telefono);
                adapter.addAll(dPersona);
                ListView listView = (ListView) view.findViewById(R.id.lstView2);
                listView.setAdapter(adapter);
            }

        }
    }


}
