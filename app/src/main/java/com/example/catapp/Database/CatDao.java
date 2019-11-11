package com.example.catapp.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CatDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(List<Cat> cats);

    @Update
    public void update(Cat... cats);

    @Delete
    public void delete(Cat cats);

    @Query("SELECT * FROM Cat")
    public List<Cat> getAllCats();

    @Query("SELECT * FROM Cat WHERE id = :id")
    public Cat getCat(String id);
}
