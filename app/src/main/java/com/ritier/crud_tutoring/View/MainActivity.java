package com.ritier.crud_tutoring.View;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ritier.crud_tutoring.R;
import com.ritier.crud_tutoring.View.Fragments.CreateFragment;
import com.ritier.crud_tutoring.View.Fragments.ReadFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    public BottomNavigationView navBar;
    public ReadFragment readFragment;
    public CreateFragment createFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 하단 nav bar 세팅
        setBottomNav();
    }


    private void setBottomNav(){
        navBar = findViewById(R.id.nav_bar);

        readFragment = new ReadFragment();
        createFragment = new CreateFragment();

        //부모 프레임에 프래그먼트 위치(첫 화면)
        FragmentTransaction transaction = fragmentManager.beginTransaction(); // 화면 변환 필요시 변환(지정) 이전에 호출
        transaction.replace(R.id.fl_frame, readFragment); // read가 첫화면
//        transaction.replace(R.id.fl_frame, readFragment).commitAllowingStateLoss(); // commit 까지 자동 처리
        transaction.commit(); // 변경을 commit 후엔 객체 재사용 불가

        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.item_read:
                        transaction.replace(R.id.fl_frame, readFragment).commitAllowingStateLoss();
                        break; // 작동을 마침

                    case R.id.item_create:
                        transaction.replace(R.id.fl_frame, createFragment).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });
    }

}
