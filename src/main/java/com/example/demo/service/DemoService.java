package com.example.demo.service;

import com.example.demo.dao.PlaylistMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.domain.PlaylistDemo;
import com.example.demo.domain.UserDemo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0 created  on 2019/12/11 16:19
 */
@Service
@AllArgsConstructor
public class DemoService {

    private UserMapper userMapper;
    private PlaylistMapper playListMapper;

    public UserDemo selectUserById(Integer id){
        return userMapper.selectById(id);
    }

    public List<UserDemo> selectUsers(){
        return userMapper.selectAll();
    }

    public void insertUser(UserDemo user){
        userMapper.insertUser(user);
    }

    public PlaylistDemo selectPlaylistById(String id){
        return playListMapper.selectById(id);
    }

    public List<PlaylistDemo> selectPlaylists(){
        return playListMapper.selectAll();
    }

    public void insertPlaylist(PlaylistDemo playlist){
        playListMapper.insertPlaylist(playlist);
    }
}
