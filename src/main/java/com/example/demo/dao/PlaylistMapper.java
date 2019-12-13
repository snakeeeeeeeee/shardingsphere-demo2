package com.example.demo.dao;

import com.example.demo.domain.PlaylistDemo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version 1.0  on 2019/12/11 16:03
 */
@Mapper
public interface PlaylistMapper {

    PlaylistDemo selectById(@Param("playlistId") String playlistId);

    List<PlaylistDemo> selectAll();

    void insertPlaylist(@Param("playlist") PlaylistDemo playlist);
}
