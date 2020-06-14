package com.ritier.crud_tutoring.View.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ritier.crud_tutoring.Dao.PostDaoImpl;
import com.ritier.crud_tutoring.Models.Post;
import com.ritier.crud_tutoring.R;
import com.ritier.crud_tutoring.Utils;

import io.realm.Realm;

public class CreateFragment extends Fragment {

    private EditText evTitle;
    private EditText evDesc;
    private Button btnSubmit;
    private PostDaoImpl postDao;
    private Realm realm = Realm.getDefaultInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);

        evTitle = view.findViewById(R.id.ev_title);
        evDesc = view.findViewById(R.id.ev_desc);
        btnSubmit = view.findViewById(R.id.btn_submit);
        postDao = new PostDaoImpl(realm);

        //디비에 데이터 추가
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        });

        return view;
    }

    private void submitPost() {
        if (evTitle.getText().toString().isEmpty() || evDesc.getText().toString().isEmpty()) {
            Toast.makeText(this.getContext(), "빈칸을 채워주세요", Toast.LENGTH_SHORT).show();
        } else {
            Post post = new Post(
                    Utils.getRealmLastId(realm),
                    evTitle.getText().toString(),
                    evDesc.getText().toString()
            );

            postDao.createPost(post);

            evTitle.getText().clear();
            evDesc.getText().clear();

            Toast.makeText(this.getContext(), "업로드에 성공했습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
