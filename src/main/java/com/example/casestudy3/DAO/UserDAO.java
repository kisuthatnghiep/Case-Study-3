package com.example.casestudy3.DAO;

import com.example.casestudy3.connection.MyConnection;
import com.example.casestudy3.model.Song;
import com.example.casestudy3.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;
    private final String SELECT_SONG_BY_SEARCH = "select * from song where  ";
    private final String SELECT_SONG_BY_PLAYLIST = "select s.id, s.name, s.description, s.price, s.singerId, s.status \n" +
                                                    "from song s join playlistdetail dtl on s.id = dtl.songId\n" +
                                                    "join playlist p on dtl.playlistId = p.id where p.name = ?;";
    private final String SELECT_ALL_SONGS = "select * from Song ;";

    public UserDAO() {
        connection =  MyConnection.getConnection();
    }
    public List<Song> findAllSong() {
        List<Song> songs = new ArrayList<Song>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SONGS)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                addListSong(songs, resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return songs;
    }

    // tim kiem bai hat theo ten, ca si
    public List<Song> findSearchSong(String column,String search){
        List<Song> songs = new ArrayList<Song>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_SONG_BY_SEARCH+column+" like "+"%"+search+"% ;")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                addListSong(songs, resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return songs;
    }
    //  tim kiem bai hat theo playlist
    public List<Song> songByPlayList(String search){
        List<Song> songs = new ArrayList<Song>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SONG_BY_PLAYLIST)){
            preparedStatement.setString(1,search);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                addListSong(songs, resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return songs;
    }
    // Tao album cua user
    public void createPlayList(){

    }
    private static void addListSong(List<Song> songs, ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String link = resultSet.getString("link");
        String description = resultSet.getString("description");
        double price = resultSet.getDouble("price");
        long singerId =  resultSet.getLong("singerId");
        int status = resultSet.getInt("status");
        songs.add(new Song(id, name,link,description,price,singerId,status ));
    }
}
