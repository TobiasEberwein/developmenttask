package com.developmenttask.favoritemusic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MusicUnitTest {

    private Music music;

    @Test
    void testDefaultConstructor() {
        music = new Music();

        assertEquals(null, music.getId());
        assertEquals(null, music.getName());
        assertEquals(null, music.getArtist());
        assertEquals(null, music.getYear());
        assertEquals(null, music.getFormat());
    }

    @Test
    void testConstructorWithId() {
        // all null
        music = new Music(null, null, null, null, null);

        assertEquals(null, music.getId());
        assertEquals(null, music.getName());
        assertEquals(null, music.getArtist());
        assertEquals(null, music.getYear());
        assertEquals(null, music.getFormat());

        // normal values
        music = new Music(1L, "Name", "Artist", 2000, "Format");

        assertEquals(1L, music.getId());
        assertEquals("Name", music.getName());
        assertEquals("Artist", music.getArtist());
        assertEquals(2000, music.getYear());
        assertEquals("Format", music.getFormat());

        // upper limits (Id and year) and empty strings
        music = new Music(Long.MAX_VALUE, "", "", Integer.MAX_VALUE, "");

        assertEquals(Long.MAX_VALUE, music.getId());
        assertEquals("", music.getName());
        assertEquals("", music.getArtist());
        assertEquals(Integer.MAX_VALUE, music.getYear());
        assertEquals("", music.getFormat());

        // lower limits (Id and year) and whitespace strings
        music = new Music(Long.MIN_VALUE, " ", "  ", Integer.MIN_VALUE, "   ");

        assertEquals(Long.MIN_VALUE, music.getId());
        assertEquals(" ", music.getName());
        assertEquals("  ", music.getArtist());
        assertEquals(Integer.MIN_VALUE, music.getYear());
        assertEquals("   ", music.getFormat());
    }

    @Test
    void testConstructorWithoutId() {
        // all null
        music = new Music(null, null, null, null);

        assertEquals(null, music.getId());
        assertEquals(null, music.getName());
        assertEquals(null, music.getArtist());
        assertEquals(null, music.getYear());
        assertEquals(null, music.getFormat());

        // normal values
        music = new Music("Name", "Artist", 2000, "Format");

        assertEquals(null, music.getId());
        assertEquals("Name", music.getName());
        assertEquals("Artist", music.getArtist());
        assertEquals(2000, music.getYear());
        assertEquals("Format", music.getFormat());

        // upper limits (year) and empty strings
        music = new Music("", "", Integer.MAX_VALUE, "");

        assertEquals(null, music.getId());
        assertEquals("", music.getName());
        assertEquals("", music.getArtist());
        assertEquals(Integer.MAX_VALUE, music.getYear());
        assertEquals("", music.getFormat());

        // lower limits (year) and whitespace strings
        music = new Music(" ", "  ", Integer.MIN_VALUE, "   ");

        assertEquals(null, music.getId());
        assertEquals(" ", music.getName());
        assertEquals("  ", music.getArtist());
        assertEquals(Integer.MIN_VALUE, music.getYear());
        assertEquals("   ", music.getFormat());
    }
    
    @Test
    void testSetGetId() {
        music = new Music();

        // default get null
        assertEquals(null, music.getId());

        Long expectedIds[] = {null, 1L, Long.MAX_VALUE, Long.MIN_VALUE};

        for (Long expectedId : expectedIds) {
            music.setId(expectedId);
            assertEquals(expectedId, music.getId());
        }
    }

    @Test
    void testSetGetName() {
        music = new Music();

        // default get null
        assertEquals(null, music.getName());

        String expectedNames[] = {null, "Name", "", " ", new String("Test")};

        for (String expectedName : expectedNames) {
            music.setName(expectedName);
            assertEquals(expectedName, music.getName());
        }
    }

    @Test
    void testSetGetArtist() {
        music = new Music();

        // default get null
        assertEquals(null, music.getArtist());

        String expectedArtists[] = {null, "Artist", "", " ", new String("Test")};

        for (String expectedArtist : expectedArtists) {
            music.setArtist(expectedArtist);
            assertEquals(expectedArtist, music.getArtist());
        }
    }
    
    @Test
    void testSetGetYear() {
        music = new Music();

        // default get null
        assertEquals(null, music.getYear());

        Integer expectedYears[] = {null, 2020, Integer.MAX_VALUE, Integer.MIN_VALUE};

        for (Integer expectedYear : expectedYears) {
            music.setYear(expectedYear);
            assertEquals(expectedYear, music.getYear());
        }
    }

    @Test
    void testSetGetFormat() {
        music = new Music();

        // default get null
        assertEquals(null, music.getFormat());

        String expectedFormats[] = {null, "Format", "", " ", new String("Test")};

        for (String expectedFormat : expectedFormats) {
            music.setFormat(expectedFormat);
            assertEquals(expectedFormat, music.getFormat());
        }
    }
}
