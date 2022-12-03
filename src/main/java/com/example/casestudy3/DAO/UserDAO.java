package com.example.casestudy3.DAO;

import com.example.casestudy3.connection.MyConnection;
import com.example.casestudy3.model.Playlist;
import com.example.casestudy3.model.Song;
import com.example.casestudy3.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAO {
    private Connection connection;
    private final String SELECT_SONG_BY_SEARCH = "select * from song where  ";
    private final String SELECT_SONG_BY_PLAYLIST = "select s.id, s.name, s.description, s.price, s.singerId, s.status \n" +
                                                    "from song s join playlistdetail dtl on s.id = dtl.songId\n" +
                                                    "join playlist p on dtl.playlistId = p.id where p.name = ?;";
    private final String SELECT_ALL_SONGS = "select * from Song ;";
    private final String INSERT_PLAYLIST = "insert into playlist (name,avatar,date,userId) value ( ? , ? , ? , ? );";
    private final String USER_BY_SONG = "update users set wallet = ? where id = ?;";
    private final String SINGER_SELL_SONG = "update singer set income = ? where id = ?;";
    private final String INSERT_PLAYLISTDETAIL = "insert into playlistdetail (playlistId,songID) value ( ? , ? );";
    private final String SELECT_USER_PLAYLIST = "select p.id, p.name\n" +
                                                "from users u join playlist p on u.id = p.userId\n" +
                                                "where u.id = ? ;";

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
    public boolean createPlayList(Playlist playlist){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PLAYLIST)){
            preparedStatement.setString(1,playlist.getName());
            preparedStatement.setString(2,playlist.getAvatar());
            preparedStatement.setDate(3,playlist.getDate());
            preparedStatement.setLong(4,playlist.getUserId());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    public boolean userBuySong(double wallet,long id) throws SQLException {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(USER_BY_SONG)){
//            preparedStatement.setDouble(1,wallet);
//            preparedStatement.setLong(2,id);
//            return preparedStatement.executeUpdate()>0;
//        }
//    }
//    public boolean singerSellSong(double income,long id) throws SQLException {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SINGER_SELL_SONG)){
//            preparedStatement.setDouble(1,income);
//            preparedStatement.setLong(2,id);
//            return preparedStatement.executeUpdate()>0;
//        }
//    }
//    public boolean addPlayList(long playListId,long songId) throws SQLException {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PLAYLISTDETAIL)){
//            preparedStatement.setLong(1,playListId);
//            preparedStatement.setLong(2,songId);
//            return preparedStatement.executeUpdate()>0;
//        }
//    }
    public void buySong(double wallet,double income,long playListId,long userId,long singerId,long songId){
        try (PreparedStatement preparedStatementUser = connection.prepareStatement(USER_BY_SONG);
             PreparedStatement preparedStatementSinger = connection.prepareStatement(SINGER_SELL_SONG);
             PreparedStatement preparedStatementPlayList = connection.prepareStatement(INSERT_PLAYLISTDETAIL)
        ){
            connection.setAutoCommit(false);
            preparedStatementUser.setDouble(1,wallet);
            preparedStatementUser.setLong(2,userId);
            preparedStatementSinger.setDouble(1,income);
            preparedStatementSinger.setLong(2,singerId);
            preparedStatementPlayList.setLong(1,playListId);
            preparedStatementPlayList.setLong(2,songId);
            preparedStatementUser.executeUpdate();
            preparedStatementSinger.executeUpdate();
            preparedStatementPlayList.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException sql) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    public HashMap<Long,String> playlistUser( long userId) throws SQLException {
        HashMap<Long,String> playList = new HashMap<Long,String>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_PLAYLIST)){
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                playList.put(id,name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playList;
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
