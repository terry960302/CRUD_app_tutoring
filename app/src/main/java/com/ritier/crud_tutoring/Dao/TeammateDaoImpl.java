package com.ritier.crud_tutoring.Dao;

import android.util.Log;

import com.ritier.crud_tutoring.Models.Post;
import com.ritier.crud_tutoring.Models.Teammate;
import com.ritier.crud_tutoring.Utils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

public class TeammateDaoImpl implements TeammateDao {

    private Realm realm;
    private String tag = "TeammateDaoImpl";

    public TeammateDaoImpl(Realm realm){
        this.realm = realm;
    }

    @Override
    public List<Teammate> getAllTeammates() {

        realm.beginTransaction();
        RealmQuery<Teammate> query = realm.where(Teammate.class);
        RealmResults<Teammate> teammates = query.findAll().sort("id", Sort.DESCENDING);
        realm.commitTransaction();

        Log.d(tag, "getTeammates success");

        return realm.copyFromRealm(teammates);
    }

    @Override
    public Teammate getTeammate(int id) {
//        realm.beginTransaction();
//        RealmQuery<Post> query = realm.where(Post.class);
//        Post post = query.equalTo("id", id).findFirst();
//        realm.commitTransaction();
//
//        Log.d(tag, "getPostById success");
//
//        return post;
        return null;
    }

    @Override
    public void createTeammate(final Teammate teammate) {
//        realm.beginTransaction();
//        realm.copyToRealm(teammate);
//        realm.commitTransaction();

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
//        realm.beginTransaction();
//
//        RealmQuery<Post> query = realm.where(Post.class);
//        Post post = query.equalTo("id", id).findFirst();
//
//        if(post != null){
//            post.deleteFromRealm();
//        }
//
//
//        realm.commitTransaction();
//
//        Log.d(tag, "deletePost success");
    }

    @Override
    public void updateTeammate(Teammate teammate) {

    }
}
