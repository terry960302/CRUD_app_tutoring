package com.ritier.crud_tutoring;

import com.ritier.crud_tutoring.Models.Post;
import com.ritier.crud_tutoring.Models.Teammate;

import io.realm.Realm;

public class Utils {

    //Make auto-increment in Realm
    public static int getRealmLastId(Realm realm){
        Number maxId = realm.where(Teammate.class).max("id");

        if(maxId == null){
            return 0;
        }else{
            return maxId.intValue() + 1;
        }
    }
}
