package com.example.casestudy3.DAO;

import com.example.casestudy3.connection.MyConnection;
import com.example.casestudy3.model.Playlist;
import com.example.casestudy3.model.PlaylistDetail;
import com.example.casestudy3.model.Song;
import com.example.casestudy3.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDAO {
    private Connection connection;
    private final String SELECT_SONG_BY_SEARCH ="select s.id as id, s.name  as name, s.description as description," +
                                                " s.price as price, s.singerId as singerId, s.status as status \n" +
                                                "from song s join singer on s.singerId = singer.id\n" +
                                                "where p.name = ? and singer.status = 1;";
    private final String SELECT_SONG_BY_PLAYLIST = "select s.id as id, s.name  as name, s.description as description," +
                                                    " s.price as price, s.singerId as singerId, s.status as status \n" +
                                                    "from singer join song s on singer.id = s.singerId\n" +
                                                    "join playlistdetail dtl on s.id = dtl.songId\n" +
                                                    "join playlist p on dtl.playlistId = p.id " +
                                                    "where p.name = ? and singer.status = 1;";
    private final String SELECT_ALL_SONGS = "select s.id as id, s.name  as name, s.description as description," +
                                            " s.price as price, s.singerId as singerId, s.status as status \n" +
                                            "from song s join singer on s.singerId = singer.id\n" +
                                            "where singer.status = 1;";
    private final String INSERT_PLAYLIST = "insert into playlist (name,date,userId) value ( ? , ? , ? );";
    private final String USER_BY_SONG = "update users set wallet = ? where id = ?;";
    private final String SINGER_SELL_SONG = "update singer set income = ? where id = ?;";
    private final String INSERT_PLAYLISTDETAIL = "insert into playlistdetail (playlistId,songID,date) value ( ? , ? , ? );";
    private final String SELECT_USER_PLAYLIST = "select p.id, p.name, p.avatar, p.date \n" +
                                                "from users u join playlist p on u.id = p.userId\n" +
                                                "where u.id = ? ;";
    private final String SELECT_SONG_BY_USER = "select s.id as id, s.name  as name, s.description as description," +
                                                " s.price as price, s.singerId as singerId, s.status as status \n" +
                                                "from users u join playlist p on u.id = p.userId\n" +
                                                "join playlistdetail dtl on p.id = dtl.playlistId\n" +
                                                "join song s on dtl.songId = s.id\n" +
                                                "where u.id = ?;";
    private final String SELECT_SUM_PRICE_USER ="select sum(s.price) as sumPrice\n" +
                                                "from users u join playlist p on u.id = p.userId\n" +
                                                "join playlistdetail dtl on p.id = dtl.playlistId\n" +
                                                "join song s on dtl.songId = s.id\n" +
                                                "where u.id = ?;";
    private final String DELETE_SONG_PLAYLIST = "update playlistdetail set status = 0 where playlistId = ? & songId = ?;";
    private final String SELECT_PLAYLIST = "select * from playlistdetail where status = 1;";
    private final String UPDATE_WALLET = "update users set wallet = ? where id = ?;";

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
    public List<Song> findSearchSongByName(String search){
        List<Song> songs = new ArrayList<Song>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_SONG_BY_SEARCH+" name like "+"%"+search+"% ;")){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                addListSong(songs, resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return songs;
    }
    public List<Song> findSearchSongBySinger(long singerId){
        List<Song> songs = new ArrayList<Song>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_SONG_BY_SEARCH+" singerId = "+singerId+" ;")){
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
            preparedStatement.setDate(2,playlist.getDate());
            preparedStatement.setLong(3,playlist.getUserId());
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
    public boolean addPlayList(long playListId,long songId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PLAYLISTDETAIL)){
            preparedStatement.setLong(1,playListId);
            preparedStatement.setLong(2,songId);
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
            return preparedStatement.executeUpdate()>0;
        }
    }
    public boolean buySong(double wallet,double income,long playListId,long userId,long singerId,long songId){
        boolean flag = false;
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
            preparedStatementPlayList.setDate(3, Date.valueOf(LocalDate.now()));
            if (preparedStatementUser.executeUpdate()>0 &&
                    preparedStatementSinger.executeUpdate()> 0 &&
                    preparedStatementPlayList.executeUpdate() > 0) {
                flag = true;
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException sql) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
        return flag;
    }
    public List<Playlist> playlistUser( long userId) {
        List<Playlist> playList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_PLAYLIST)){
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                long id = resultSet.getLong("p.id");
                String name = resultSet.getString("p.name");
                Date date = resultSet.getDate("p.date");
                playList.add(new Playlist(id, name, date));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playList;
    }
    public List<Song> songByUser(long userId){
        List<Song> songs = new ArrayList<Song>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SONG_BY_USER)){
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                addListSong(songs, resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return songs;
    }
    public double sumPriceBuySongUser(long userId){
        double sumPrice =0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUM_PRICE_USER)){
            preparedStatement.setLong(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                sumPrice = resultSet.getDouble("sumPrice") ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sumPrice;
    }
    public boolean deleteSongByPlayList(long playlistId, long songId){
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SONG_PLAYLIST)){
            preparedStatement.setLong(1,playlistId);
            preparedStatement.setLong(2,songId);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<PlaylistDetail> findAllPlayListDetail() {
        List<PlaylistDetail> playlistDetails = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PLAYLIST)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long songId = rs.getLong("songId");
                long playListId = rs.getLong("playListId");
                int status = rs.getInt("status");
                Date date = rs.getDate("date");
                playlistDetails.add(new PlaylistDetail(songId, playListId,date,status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistDetails;
    }
    public boolean recharge(long userId, double wallet){
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_WALLET)){
            preparedStatement.setDouble(1, wallet);
            preparedStatement.setLong(2, userId);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
