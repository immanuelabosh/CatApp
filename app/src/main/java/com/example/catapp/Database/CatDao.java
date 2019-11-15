package com.example.catapp.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
//this is my CatDao
//Pretty standard stuff
@Dao
public interface CatDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(List<Cat> cats);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Cat... cats);

    @Update
    public void update(Cat... cats);

    @Delete
    public void delete(Cat cats);

    @Delete
    public void delete(List<Cat> cats);

    @Query("SELECT * FROM Cat")
    public List<Cat> getAllCats();

    @Query("SELECT * FROM Cat WHERE id = :id")
    public Cat getCat(String id);

    //if cat exists in database return 1, else return 0
    @Query("SELECT COUNT(1) FROM Cat WHERE id = :id")
    public int getCatExists(String id);

}
