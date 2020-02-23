package com.example.marveltestapplication.domain.model;

import java.util.Calendar;

public class CharacterModel {
    private long id;
    private String name;
    private Calendar modified;
    private ThumbnailModel modified;
    private String resourceURI;
    private int comicsAvailable;

    public CharacterModel(){
    }
}

/*data class CharacterModel(
    val id: Long,
    val name: String,
    val modified: Calendar,
    val thumbnail: ThumbnailModel,
    val resourceURI: String,
    val comicsAvailable: Int
)*/
