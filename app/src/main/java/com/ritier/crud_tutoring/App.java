package com.ritier.crud_tutoring;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Realm 데이터베이스 초기화(싱글톤으로 만들기 위함)
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("crud.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}
