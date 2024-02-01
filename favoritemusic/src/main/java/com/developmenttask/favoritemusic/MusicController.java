package com.developmenttask.favoritemusic;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/favoritemusic")
public class MusicController {

    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping
    public List<Music> getMusic() {
        return musicService.getMusic();
    }

    @GetMapping("/{musicId}")
    public Music getMusicById(@PathVariable Long musicId) {
        return musicService.getMusicById(musicId);
    }
    
    @PostMapping
    public Long registerNewMusic(@RequestBody Music music) {
        musicService.addNewMusic(music);
        return music.getId();
    }

    @DeleteMapping("/{musicId}")
    public void deleteMusic(@PathVariable("musicId") Long musicId) {
        musicService.deleteMusic(musicId);
    }

    @PutMapping("/{musicId}")
    public void updateMusic(@RequestBody Music newMusic, @PathVariable("musicId") Long musicId) {
        musicService.updateMusic(musicId, newMusic.getName(), newMusic.getArtist(), newMusic.getYear(), newMusic.getFormat());
    }
}
