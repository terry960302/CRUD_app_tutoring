package com.ritier.crud_tutoring.Dao;

import com.ritier.crud_tutoring.Models.Teammate;

import java.util.List;

public interface TeammateDao {

    //Read all
    List<Teammate> getAllTeammates();

    //Read one
    Teammate getTeammate(int id);

    //Create
    void createTeammate(Teammate teammate);

    //Delete
    void deleteTeammate(int id);

    //Update
    void updateTeammate(Teammate teammate);
}
