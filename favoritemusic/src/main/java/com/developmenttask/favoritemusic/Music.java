package com.developmenttask.favoritemusic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "artist")
    private String artist;
    
    @Column(name = "year")
    private Integer year;

    @Column(name = "format")
    private String format;

    public Music(String name, String artist, Integer year, String format) {
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.format = format;
    }

    public Music(Long id, String name, String artist, Integer year, String format) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.format = format;
    }

    public Music() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
