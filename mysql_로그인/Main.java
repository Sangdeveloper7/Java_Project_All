package User;

import User.Controller;
import User.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

public class Main {
//    public static void main(String[] args) throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/mysql";
//        String userName = "root";
//        String password = "dltkdgus9769!A";
//
//        Connection connection = DriverManager.getConnection(url, userName, password);
//
//
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from loginuser");
//
//            resultSet.next();
//            String name = resultSet.getString("UserName");
//            System.out.println(name);
//
//            resultSet.close();
//            statement.close();
//            connection.close();
//
//
//
//
//    }
//            JFrame jf = new JFrame(); // 로그인 패널 (처음)
//            JPanel jp1 = new JPanel(); // 회원가입 패널
//            JPanel jp2 = new JPanel(); // 로그인 후 패널 -> 로그인 되었을 때
//            JPanel jp3 = new JPanel(); // 수정 버튼 눌렀을 때 수정 패널
//            JPanel jp4 = new JPanel(); // 탈퇴 버튼 눌렀을 때 패널
//            JPanel jp5 = new JPanel(); // 로그아웃 버튼 눌렀을때.. 패널 ...? ->
//            JPanel jp6 = new JPanel(); // admin 수정 패널 -> 선수 조회 하고 삭제 추가 할 수 있다.
//            JPanel jp7 = new JPanel(); //
//
//
//            String id;
//            String name;
//            String password;
//            String birth;
//            String gender;
//            String phonenumber;
//
//
//
//            JTextField jt1 = new JTextField(); // 아이디 필드
//            JTextField jt2 = new JTextField(); // 이름 필드
//            JTextField jt3 = new JTextField(); // 비밀번호 필드
//            JTextField jt4 = new JTextField(); // 생일 필드
//            JTextField jt5 = new JTextField(); // 성별
//            JTextField jt6 = new JTextField(); // 전화번호
//            JTextArea ja1 = new JTextArea(); // 유저 정보 나열해주는 긴 textFiled
//    JTextField jt8 = new JTextField(); //
//    JTextField jt9 = new JTextField();
//    JTextField jt10 = new JTextField();

//
//
//
//            JButton jb1, jb2; // 로그인 패널 버튼 (로그인 , 회원가입 )
//            JButton jb3, jb4; // 회원가입 패널 버튼 ( 아이디 중복확인, 회원가입)
//            JButton jb5, jb6, jb7, jb8; // 수정 탈퇴 로그아웃 admin 버튼
//            JButton jb9, jb10, jb11, jb12; // 수정 메뉴 돌아가기 검색
//            JButton jb13, jb14, jb15; // 선수 검색  탈퇴 메뉴 돌아가기
//            JButton jb16, jb17, jb18; // 선수 검색 선수 추가 선수 삭제
//
//
//            JLabel jl1 = new JLabel();
//            JLabel jl2 = new JLabel();
//            JLabel jl3 = new JLabel();
//            JLabel jl4 = new JLabel();

        public static void main(String[] args){
//                EventQueue.invokeLater(new Runnable() {
//                        @Override
//                        public void run() {
//                                try {
//                                        Main frame = new Main();
//                                        frame.setVisible(true);
//                                }catch (Exception e){
//                                        e.printStackTrace();
//                           aa     }
//                        }
//                });
                View view = new View();
                view.setVisible(true);

        }
//        public Main(){
//
//        jt1.setBounds();














////-------------------------------------------------------------- 로그인 -------------------------------------------------------------------------------------
//                jf.setTitle("상현이의 로그인 게시판 입니다.");
//                jf.setBounds(50, 50, 500, 450);
//                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                jf.setVisible(true);
//                jp1.setLayout(null);
//                jf.add(jp1);
//                jb1 = new JButton("로그인");
//                jb1.setBounds(400, 200, 50, 50);
//                jb2 = new JButton("회원가입");
//                jb1.setBounds(400, 300, 50, 50);
//                jt1 = new JTextField();
//                jt1.setBounds(150,150,30,20);
//                jt2 = new JTextField();
//                jt2.setBounds(150,190,30,20);
//                jl1 = new JLabel("아이디 : ");
//                jl1.setBounds(100,150,20,20);
//                jl2 = new JLabel("비밀번호 : ");
//                jl2.setBounds(100,190,20,20);
//
//                Controller dao = new Controller();
//
//                jb1.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//
//                        }
//        }




}
