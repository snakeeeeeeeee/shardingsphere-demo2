package com.example.demo.controller;

import com.example.demo.domain.PlaylistDemo;
import com.example.demo.domain.UserDemo;
import com.example.demo.service.DemoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0 created  on 2019/12/11 16:21
 */
@RestController
@AllArgsConstructor
public class DemoController {
    private DemoService demoService;

    @GetMapping("/demo/user/{uid}")
    public UserDemo selectUserById(@PathVariable("uid") Integer uid) {
        return demoService.selectUserById(uid);
    }

    @GetMapping("/demo/user")
    public List<UserDemo> selectUsers() {
        return demoService.selectUsers();
    }

    @PostMapping("/demo/user/insert")
    public void insert(UserDemo user) {
        demoService.insertUser(user);
    }

    @GetMapping("/demo/playlist/{uid}")
    public PlaylistDemo selectPlaylistById(@PathVariable("playlistId") String playlistId) {
        return demoService.selectPlaylistById(playlistId);
    }

    @GetMapping("/demo/playlist")
    public List<PlaylistDemo> selectPlaylists() {
        return demoService.selectPlaylists();
    }

    @PostMapping("/demo/playlist/insert")
    public void insert(PlaylistDemo playlist) {
        demoService.insertPlaylist(playlist);
    }
}
