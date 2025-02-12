package User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import User.Model;
import com.sun.javafx.UnmodifiableArrayList;
import javafx.concurrent.Task;

public class Controller {

        String url = "jdbc:mysql://localhost:3306/mysql";
        String userName = "root";
        String password = "dltkdgus9769!A";



        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        final String BOARD_INSERT = "insert into loginuser (UserName, UserId, UserPw, PhoneN, BirthDate, UserGender) values (?,?,?,?,?,?)";
        final String BOARD_UPDATE = "update loginuser set UserName=?, UserpW=?, PhoneN=?, BirthDate=?, UserGender=? where UserId=?";
        final String BOARD_DELETE = "delete from loginuser  where UserId=?";
        final String BOARD_GET = "select * from loginuser  where UserId=?";
        final String BOARD_LIST = "select * from loginuser order by UserId desc";








        public int insertBoard(Model vo){
            System.out.println("===> JDBC로 insertBoard() 기능 처리");
            try {
                conn = DriverManager.getConnection(url, userName, password);
                stmt = conn.prepareStatement(BOARD_INSERT);
                stmt.setString(1, vo.getUserName());
                stmt.setString(2, vo.getUserId());
                stmt.setString(3, vo.getUserPw());
                stmt.setString(4, vo.getPhoneN());
                stmt.setString(5, vo.getBirthday());
                stmt.setString(6, vo.getGender());

                stmt.executeUpdate();
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

        // 글 삭제
        public void deleteBoard(Model vo) {
            System.out.println("===> JDBC로 deleteBoard() 기능 처리");
            try {
                conn = DriverManager.getConnection(url, userName, password);
                stmt = conn.prepareStatement(BOARD_DELETE);
                stmt.setString(1, vo.getUserId());
                stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public int updateBoard(Model vo) {
            System.out.println("===> JDBC로 updateBoard() 기능 처리");
            try {
                conn = DriverManager.getConnection(url, userName, password);
                stmt = conn.prepareStatement(BOARD_UPDATE);
                stmt.setString(1, vo.getUserName());
                stmt.setString(2, vo.getUserId());
                stmt.setString(3, vo.getUserPw());
                stmt.setString(4, vo.getPhoneN());
                stmt.setString(5, vo.getBirthday());
                stmt.setString(6, vo.getGender());

                System.out.println(vo.getUserName() + "-" + vo.getUserId() + "-" + vo.getUserPw() + "-" + vo.getUserPw() + " " + vo.getBirthday() + " " + vo.getGender());
                stmt.executeUpdate();
                return 1;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }


        public List<Model> getBoardList(){
            List<Model> list = new ArrayList<Model>();
            System.out.println("===> JDBC로 getBoardList() 기능 처리");
            try {
                conn = DriverManager.getConnection(url, userName, password);
                stmt = conn.prepareStatement(BOARD_LIST);
                rs = stmt.executeQuery();
                while(rs.next()) {
                    Model one = new Model();
                    one.setUserId(rs.getString("UserId"));
                    one.setUserName(rs.getString("UserName"));
                    one.setGender(rs.getString("UserGender"));
                    list.add(one);
                }
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

    public Model getBoard(String userid) {
        Model one = new Model();
        System.out.println("===> JDBC로 getBoard() 기능 처리");
        try {
            conn = DriverManager.getConnection(url, userName, password);
            stmt = conn.prepareStatement(BOARD_GET);
            stmt.setString(1, userid);
            rs = stmt.executeQuery();
            if(rs.next()) {
                one.setUserName(rs.getString("UserName"));
                one.setUserId(rs.getString("UserId"));
                one.setUserPw(rs.getString("UserPw"));
                one.setPhoneN(rs.getString("PhoneN"));
                one.setBirthday(rs.getString("BirthDate"));
                one.setGender(rs.getString("UserGender"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return one;
    }


//    public int login(String userid, String userpw){
//        String SQL = "SELECT UserPw FROM loginuser WHERE UserId = ?";
//        System.out.println("디버깅");
//                try{
//                    pstm = connection.prepareStatement(SQL);
//                    pstm.setString(1, userid);
//                    rs = pstm.executeQuery();
//                    if(rs.next()){
//                        if(rs.getString(1).equals(userpw))
//                            return 1;
//                        else
//                            return 0;
//                    }
//                    return -1;
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                return -2;







//    public List<Model>
//    Connection connection = null; // 연결 부분
//    ResultSet rs = null; // 결과값 불러오는 것
//    Statement st = null; // 코드를 구성하는 하나하나 문자
//
//    PreparedStatement pstm = null;
//
//
//    public Controller(){
//        try{
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "dltkdgus9769!A");
//        }catch(Exception e){
//            e.printStackTrace();
//        }}
//
//    public void insertUser(Model model){
//        try {
//            st = connection.createStatement();
//            int stmt = st.executeUpdate("insert into loginuser values  ('" + model.getUserName() + "', '" + model.getUserId()  + "', '" + model.getUserPw() + "', '" + model.getPhoneN() + "', '" + model.getBirthDate() + "', '" + model.getGender() + "', '" + model.getAdmin() + "');");
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        } finally {
//            try {
//                st.close();
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public ArrayList<Model> readUser(){
//        ArrayList<Model> arr = new ArrayList<Model>();
//        System.out.println(arr);
//        try {
//            st = connection.createStatement();
//            rs = st.executeQuery("select * from loginuser;");
//            while (rs.next()){
//                arr.add(new Model(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                st.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return arr;
//    }
//
//    public void updateUser(String name, String id, String password, String phoneN, String birthday, String gender, int admin){
//        try {
//
//
//            st = connection.createStatement();
//            int stmt = st.executeUpdate("update loginuser set admin = '" + admin + "' where gender = '" + gender + "' where birthdate = '" + birthday + "' where phonenumber = '" + phoneN + "' where userpw = '" + password + "' where userid = '" + id + "'where username = '" + name + "';");
//        }catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try{
//                st.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void deleteUser(String name){
//        try {
//            st = connection.createStatement();
//            int stmt = st.executeUpdate("delete from loginuser where username = '" + name + "';");
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            try {
//                st.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public ArrayList<Model> searchUser(String name){
//        ArrayList<Model> arr = new ArrayList<Model>();
//        System.out.println(arr);
//        try {
//            st = connection.createStatement();
//            rs = st.executeQuery("select * from loginuser where username like '%" + name + "%';");
//            while (rs.next()){
//                arr.add(new Model(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                st.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return arr;
//    }
//

//    }


    }


