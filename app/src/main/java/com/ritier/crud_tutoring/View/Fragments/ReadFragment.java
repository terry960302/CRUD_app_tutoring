package com.ritier.crud_tutoring.View.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ritier.crud_tutoring.Dao.TeammateDaoImpl;
import com.ritier.crud_tutoring.Models.Teammate;
import com.ritier.crud_tutoring.R;
import com.ritier.crud_tutoring.Utils;
import com.ritier.crud_tutoring.View.Adapters.PostRecyAdapter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class ReadFragment extends Fragment {

    private RecyclerView rvPosts;
    private PostRecyAdapter adapter;
    private TeammateDaoImpl teammateDao;
    private Realm realm = Realm.getDefaultInstance();
    private List<Teammate> teammates = new ArrayList<Teammate>();

    private EditText evName;
    private EditText evNumber;
    private EditText evLR;
    private EditText evAge;
    private Button btnSubmit;
    private FloatingActionButton fbCreate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);

        fbCreate = view.findViewById(R.id.fb_create);

        //Dao init
        teammateDao = new TeammateDaoImpl(realm);

        initRecyclerView(view);

        fbCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateDialog();
            }
        });

        return view;
    }

    private void initRecyclerView(View view) {
        rvPosts = view.findViewById(R.id.rv_posts);
        rvPosts.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));
        adapter = new PostRecyAdapter(this.getContext(), teammates, teammateDao);

        //Put data to recyclerview
        addTeammates2Adapter();

        rvPosts.setAdapter(adapter);
    }

    private void showCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_team, null);
        builder.setView(view);

        evName = view.findViewById(R.id.ev_name);
        evNumber = view.findViewById(R.id.ev_number);
        evAge = view.findViewById(R.id.ev_age);
        evLR = view.findViewById(R.id.ev_lr);
        btnSubmit = view.findViewById(R.id.btn_submit);

        final AlertDialog dialog = builder.create();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int id = Utils.getRealmLastId(realm);
                String name = evName.getText().toString();
                String backNumberStr = evNumber.getText().toString();
                String age = evAge.getText().toString();
                String leftRight = evLR.getText().toString();

                Teammate teammateObj = new Teammate(id , name, backNumberStr, leftRight, age);

                teammateDao.createTeammate(teammateObj);

                Toast.makeText(getActivity(), "추가되었습니다.",Toast.LENGTH_LONG).show();
                dialog.dismiss();

                adapter.clearItems();
                addTeammates2Adapter();
            }
        });

        dialog.show();

    }

    //Put data to recyclerview
    private void addTeammates2Adapter() {
        adapter.teammates = new ArrayList<>(); // 데이터 초기화(창을 불러올 때마다 데이터 중복을 없애기 위함.)
        for (int i = 0; i < getTeammatesFromRealm().size(); i++) {
            Teammate teammate = getTeammatesFromRealm().get(i);
            adapter.addItem(teammate);
        }
    }

    //Get data from realm(Local DB)
    private List<Teammate> getTeammatesFromRealm() {
        teammates = teammateDao.getAllTeammates();
        return teammates;
    }
}
