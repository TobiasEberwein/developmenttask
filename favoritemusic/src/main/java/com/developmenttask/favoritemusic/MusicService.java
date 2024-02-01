package com.developmenttask.favoritemusic;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class MusicService {

    private final MusicRepository musicRepository;

    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<Music> getMusic() {
        return musicRepository.findAllByOrderByIdAsc();
    }

    public Music getMusicById(Long musicId) {
        return musicRepository.findById(musicId).orElseThrow(()->new IllegalStateException("music with id " + musicId + " does not exist"));
    }

    public void addNewMusic(Music music) {
        musicRepository.save(music);
    }

    public void deleteMusic(Long musicId) {
        boolean exists = musicRepository.existsById(musicId);
        if(!exists) {
            throw new IllegalStateException("music with id " + musicId + " does not exist");
        }
        musicRepository.deleteById(musicId);
    }

    @Transactional
    public void updateMusic(Long musicId, String name, String artist, Integer year, String format) {
        musicRepository.findById(musicId)
          .map((music -> {
            music.setName(name);
            music.setArtist(artist);
            music.setYear(year);
            music.setFormat(format);
            return musicRepository.save(music);
          })).orElseThrow(()->new IllegalStateException("music with id " + musicId + " does not exist"));
    }
}
