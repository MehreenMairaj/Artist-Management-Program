package artistmanagement;

import java.sql.*;

public class dbConnection {

    private Connection conn = null;
    private PreparedStatement smt = null;
    private ResultSet myRs = null;

    public dbConnection(String dbname) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/" + dbname;
        String username = "root";
        String password = "lexicon";

        conn = DriverManager.getConnection(url, username, password);

    }

    public void addArtist(String first_name, String last_name, int age) throws Exception {

        String sql = "insert into artist(first_name,last_name,age)values(?,?,?)";
        smt = conn.prepareStatement(sql);
        smt.setString(1, first_name);
        smt.setString(2, last_name);
        smt.setInt(3, age);
        int results = smt.executeUpdate();
        System.out.println("Records inserted: " + results);

    }

    // Updating artist first name by id
    public void updateFirstName(String first_name, int id) throws Exception {
        String sql = "Update artist set first_name = ? where id = ?";
        smt = conn.prepareStatement(sql);
        smt.setString(1, first_name);
        smt.setInt(2, id);

        int results = smt.executeUpdate();
        System.out.println("Records updated: " + results);
    }

    //Updating artist's age by First Name
    public void updateAge(int age, String first_name) throws Exception {
        String sql = "Update artist set age = ? where first_name = ?";
        smt = conn.prepareStatement(sql);
        smt.setInt(1, age);
        smt.setString(2, first_name);
        int results = smt.executeUpdate();
        System.out.println("Records updated: " + results);
    }

    // Updating artist Last Name by id
    public void updateLastName(String last_name, int id) throws Exception {
        String sql = "Update artist set last_name = ? where id = ?";
        smt = conn.prepareStatement(sql);
        smt.setString(1, last_name);
        smt.setInt(2, id);
        int results = smt.executeUpdate();
        System.out.println("Records updated: " + results);
    }

    //Deleting artist by id
    public void deleteArtistbyId(int id) throws Exception {

        String sql = "delete from artist where id = ?";
        smt = conn.prepareStatement(sql);
        smt.setInt(1, id);

        int results = smt.executeUpdate();
        System.out.println("Records deleted: " + results);

    }
    // show all the artist 

    public void showAllArtist() throws Exception {
        String sql = "select * from artist";
        smt = conn.prepareStatement(sql);
        myRs = smt.executeQuery();
        System.out.println("             Showing All artist              ");
        System.out.println("=============================================");
        header();
        while (myRs.next()) {
            System.out.println(fixLengthString(myRs.getInt("id"), 5) + " " + fixLengthString(myRs.getString("first_name"), 15) + " " + fixLengthString(myRs.getString("last_name"), 15) + " " + fixLengthString(myRs.getInt("age"), 5));

        }

    }
    // Show artist by id

    public void showArtistById(int id) throws Exception {
       try{
        String sql = "select * from artist where id = ?";
        smt = conn.prepareStatement(sql);
        smt.setInt(1, id);
        myRs = smt.executeQuery();
          System.out.println("           Showing artist by id              ");
        System.out.println  ("=============================================");
        header();
        while (myRs.next()) {
            System.out.println(fixLengthString(myRs.getInt("id"), 5) + " " + fixLengthString(myRs.getString("first_name"), 15) + " " + fixLengthString(myRs.getString("last_name"), 15) + " " + fixLengthString(myRs.getInt("age"), 5));
        }
       }
       catch (SQLException e){
           System.out.println("Record not found!");
       }
    }

    private void header() {
        System.out.println(fixLengthString("ID", 5) + " " + fixLengthString("FIRST NAME", 15) + " " + fixLengthString("LAST NAME", 15) + " " + fixLengthString("AGE", 5)+"\n");
    }

    // show Artist by first name
    public void showArtistByFirstName(String first_name) throws Exception {
        String sql = "select * from artist where first_name = ?";
        smt = conn.prepareStatement(sql);
        smt.setString(1, first_name);
        myRs = smt.executeQuery();
          System.out.println("       Showing artist by first name          ");
        System.out.println  ("=============================================");
        header();
        while (myRs.next()) {
            System.out.println(fixLengthString(myRs.getInt("id"), 5) + " " + fixLengthString(myRs.getString("first_name"), 15) + " " + fixLengthString(myRs.getString("last_name"), 15) + " " + fixLengthString(myRs.getInt("age"), 5));

        }

    }

    private String fixLengthString(String start, int length) {
        //TODO: fix string padding problem
        if (start.length() >= length) {
            return start.substring(0, length);
        } else {
            while (start.length() < length) {
                start += " ";
            }
            return start;
        }
    }

    private String fixLengthString(int start, int length) {
        String startString = String.valueOf(start);
        return fixLengthString(startString, length);
    }
}
