package com.example.wahyunainggolan.bola.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wahyunainggolan.bola.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Prediksi.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Prediksi#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.wahyunainggolan.bola.R;
import com.example.wahyunainggolan.bola.adapter.PredictAdapter;
import com.example.wahyunainggolan.bola.model.Predict;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class Prediksi extends Fragment {
    private ListView listview;
    public static String RANK = "rank";
    View rootView;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    //private List<Predict>list_predict = new ArrayList<>();
    ArrayList<Predict> list_predict = new ArrayList<Predict>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_predict, container, false);
        //list_data = (ListView)this.getView();
        initFirebase();
        listview = (ListView)rootView.findViewById(R.id.listview_predict);
        mDatabaseReference.child("predict").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(list_predict.size()>0)
                    list_predict.clear();
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    Predict predict = postSnapshot.getValue(Predict.class);
                    list_predict.add(predict);
                }

                PredictAdapter adapter = new PredictAdapter(getContext(),list_predict);
                listview.setAdapter(adapter);

//                listview.setVisibility(View.VISIBLE);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;
    }



    private void initFirebase(){
        FirebaseApp.initializeApp(getContext());
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

    }

}



