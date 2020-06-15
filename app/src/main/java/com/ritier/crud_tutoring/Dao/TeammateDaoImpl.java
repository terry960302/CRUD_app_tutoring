package com.ritier.crud_tutoring.Dao;

import android.util.Log;

import com.ritier.crud_tutoring.Models.Teammate;

import java.util.List;

import io.realm.Realm;

public class TeammateDaoImpl implements TeammateDao {

    private Realm realm;
    private String tag = "TeammateDaoImpl";

    public TeammateDaoImpl(Realm realm){
        this.realm = realm;
    }

    @Override
    public List<Teammate> getAllTeammates() {
        return null;
    }

    @Override
    public Teammate getTeammate(int id) {
        return null;
    }

    @Override
    public void createTeammate(final Teammate teammate) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(teammate);

                Log.d(tag, "createTeammate success");

            }
        });
    }

    @Override
    public void deleteTeammate(int id) {

    }

    @Override
    public void updateTeammate(Teammate teammate) {

    }
}
