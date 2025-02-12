package User;

import User.Controller;
import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.jws.WebParam;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class View extends JFrame {

    JPanel contentPane;
    JPanel jplog = new JPanel();// 로그인 패널 (처음)
    JPanel jpcreate = new JPanel(); // 회원가입 패널
    JPanel jpafterLog = new JPanel(); // 로그인 후 패널 -> 로그인 되었을 때
    JPanel jpmodify = new JPanel(); // 수정 버튼 눌렀을 때 수정 패널
    JPanel jp4 = new JPanel(); // 탈퇴 버튼 눌렀을 때 패널
    JPanel jp5 = new JPanel(); // 로그아웃 버튼 눌렀을때.. 패널 ...? ->
    JPanel jp6 = new JPanel(); // admin 수정 패널 -> 선수 조회 하고 삭제 추가 할 수 있다.
    JPanel jp7 = new JPanel(); //
    JPanel jptable = new JPanel();

    JPanel jpupdate = new JPanel();
    Model instancev = null;

    JTextField jtid= new JTextField(); // 아이디 필드
    JTextField jtname = new JTextField(); // 이름 필드
    JTextField jtpw = new JTextField(); // 비밀번호 필드
    JTextField jtbirth = new JTextField(); // 생일 필드
    JTextField jtgender = new JTextField(); // 성별
    JTextField jtphone = new JTextField(); // 전화번호
    JTextArea ja = new JTextArea(); // 유저 정보 나열해주는 긴 textFiled
    JTextField jt8 = new JTextField(); //
    JTextField jt9 = new JTextField();
    JTextField jt10 = new JTextField();
    JTextField jtpn = new JTextField();
    JTextField jtgen1 = new JTextField();
    JTextField jtday = new JTextField();
    JTextField jtbd2 = new JTextField();
    JTextField jtid2 = new JTextField();
    JTextField jtde2 = new JTextField();
    //
    JTextField jtnameu = new JTextField();
    JTextField jtid2u = new JTextField();
    JTextField jtpwu = new JTextField();
    JTextField jtpnu = new JTextField();

    JTextField jtbd2u = new JTextField();

    JTextField jtgen1u = new JTextField();


    JButton jblog, jbcreate; // 로그인 패널 버튼 (로그인 , 회원가입 )
    JButton jboverlap, jbenter; // 회원가입 패널 버튼 ( 아이디 중복확인, 가입)
    JButton jbmodify, jbwithdraw, jblongout, jbadmin; // 수정 탈퇴 로그아웃 admin 버튼
    JButton jbmodify2, jbgomenu; // 수정 메뉴 돌아가기
     // 선수 검색
    JButton jbadd, jbdelte; //  선수 추가 선수 삭제
    JButton check = new JButton();
    JButton enter = new JButton();
    JButton jbgolog = new JButton();
    JButton jboutlogin = new JButton();
    JButton jbsearch = new JButton();
    JButton jbdelete = new JButton();

    JButton jbdelete2 = new JButton();
    JButton check2 = new JButton();

    JButton delete3 = new JButton();



    JLabel jl1 = new JLabel();
    JLabel jl2 = new JLabel();
    JLabel jl3 = new JLabel();
    JLabel jl4 = new JLabel();

    View(){
        GUI_show();
    }

    public void GUI_show(){



        setTitle("상현이의 로그인 프로그램 입니다.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane.setLayout(null);


//        jf.setTitle("상현이의 로그인 게시판 입니다.");
//        jf.setBounds(50, 50, 500, 450);
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jf.setVisible(true);
//        jp1.setLayout(null);
//        jf.add(jp1);

//---------------------------------------------------------1. 로그인 ---------------------------------------------------
        jpcreate.setVisible(false);
        jptable.setVisible(false);
        jplog.setVisible(true);
        jpupdate.setVisible(false);
        jpafterLog.setVisible(false);
        jplog.setBounds(100,100,700,800);
        jplog.setLayout(new BorderLayout());

        jplog.setBounds(0, 0, 637, 501);
        contentPane.add(jplog);
        jplog.setLayout(null);

        JLabel lblNewLabel = new JLabel("아이디를 입력하세요");
        lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
        lblNewLabel.setBounds(190, 50, 342, 35);
        jplog.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("ID");
        lblNewLabel_1.setBounds(179, 115, 18, 22);
        jplog.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("PW");
        lblNewLabel_1_1.setBounds(179, 173, 27, 22);
        jplog.add(lblNewLabel_1_1);

        jtid = new JTextField();
        jtid.setBounds(230, 115, 199, 30);
        jtid.setColumns(10);
        jplog.add(jtid);

        JPasswordField jtpw1 = new JPasswordField();
        jtpw1.setBounds(230, 168, 199, 30);
        jplog.add(jtpw1);

        jblog = new JButton("로그인");
        jblog.setBounds(216, 271, 80, 33);
        jplog.add(jblog);
        jblog.addActionListener(new ActionListener() {

            //jtid.getText(), jtpw.getText()
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller dao = new Controller();
                String id1 = jtid.getText();
                String pw1 = new String(jtpw1.getPassword());
                System.out.print(pw1);
                try
                {Model instance = dao.getBoard(id1);
                System.out.println(pw1);

                System.out.println(id1 + pw1);
                if(instance.userpw.equals(pw1)){
                    System.out.println("성공");
                    JOptionPane.showMessageDialog(null, "로그인 성공!!");
                    jpafterLog.setVisible(true);
                    instancev = instance;
                    jpcreate.setVisible(false);
                    jplog.setVisible(false);
                    jtid.setText("");
                    jtpw1.setText("");
                }
                else{
                    System.out.println("실패");
                    JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다 아이디와 비밀번호를 다시한번 확인해 주세요!");
                    jtid.setText("");
                    jtpw1.setText("");
                }}catch (Exception e9){
                    JOptionPane.showMessageDialog(null,"이런 아이디는 없어요.. 회원가입을 해주세요");
                    jtid.setText("");
                    jtpw1.setText("");

                }
//

            }
        });




        jbcreate = new JButton("회원가입");
        jbcreate.setBounds(316, 271, 100, 33);

        jplog.add(jbcreate);
        jbcreate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jpcreate.setVisible(true);
                jpafterLog.setVisible(false);
                jplog.setVisible(false);
                jptable.setVisible(false);
                jtid.setText("");
                jtpw1.setText("");
            }
        });



//        jtid = new JTextField();
//        jtid.setBounds(150,150,30,20);
//        jtpw = new JTextField();
 //       jtpw.setBounds(150,190,30,20);
//        jl1 = new JLabel("아이디 : ");
//        jl1.setBounds(100,150,20,20);
//        jl2 = new JLabel("비밀번호 : ");
//        jl2.setBounds(100,190,20,20);
//------------------------------------------------------- 회원가입 창 ------------------------------
        jpcreate.setBounds(100,100,900,500);
        contentPane.add(jpcreate);
        jpcreate.setLayout(null);


        JLabel createT = new JLabel("회원가입");
        createT.setFont(new Font("굴림", Font.BOLD, 28));
        createT.setBounds(190, 0, 342, 35);
        jpcreate.add(createT);


        JLabel jlname = new JLabel("이름");
        jlname.setBounds(70, 60, 40, 22);
        jpcreate.add(jlname);


        jtname = new JTextField();
        jtname.setBounds(140, 60, 199, 30);
        jtname.setColumns(10);
        jpcreate.add(jtname);

        JLabel jlid2 = new JLabel("ID");
        jlid2.setBounds(70, 95, 40, 22);
        jpcreate.add(jlid2);



        jtid2 = new JTextField();
        jtid2.setBounds(140, 95, 199, 30);
        jtid2.setColumns(10);
        jpcreate.add(jtid2);

        check = new JButton("중복체크");
        check.setBounds(360,95,80,30);
        jpcreate.add(check);

        check.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller dao = new Controller();
                String sq = jtid2.getText();
                System.out.println(sq);
                try{
                Model mo = dao.getBoard(sq);
                    if(!mo.userid.isEmpty())
                        JOptionPane.showMessageDialog(null, "아이디가 이미 있습니다 다른 아이디를 입력하세요");
                    else
                        JOptionPane.showMessageDialog(null,"가입가능한 아이디 입니다.");

                }catch (Exception e4){
                    JOptionPane.showMessageDialog(null,"가입가능한 아이디 입니다.");

                }

            }

    });




        JLabel jlpw = new JLabel("PW");
        jlpw.setBounds(70, 130, 40, 22);
        jpcreate.add(jlpw);


        jtpw = new JTextField();
        jtpw.setBounds(140, 130, 199, 30);
        jtpw.setColumns(10);
        jpcreate.add(jtpw);


        JLabel jlpn = new JLabel("전화번호");
        jlpn.setBounds(50, 165, 90, 22);
        jpcreate.add(jlpn);


        jtpn = new JTextField();
        jtpn.setBounds(140, 165, 199, 30);
        jtpn.setColumns(15);
        jpcreate.add(jtpn);

        JLabel jlbd = new JLabel("생일 8자리");
        jlbd.setBounds(50, 200, 90, 22);
        jpcreate.add(jlbd);


        jtbd2 = new JTextField();
        jtbd2.setBounds(140, 200, 199, 30);
        jtbd2.setColumns(12);
        jpcreate.add(jtbd2);



        JLabel jlgen = new JLabel("성별 남/여");
        jlgen.setBounds(30, 235, 130, 22);
        jpcreate.add(jlgen);


        jtgen1 = new JTextField();
        jtgen1.setBounds(140, 235, 199, 30);
        jtgen1.setColumns(2);
        jpcreate.add(jtgen1);


        JButton enter = new JButton("가입");
        enter.setBounds(190, 350, 150, 33);
        jpcreate.add(enter);

        enter.addActionListener(new ActionListener() {

            //jtid.getText(), jtpw.getText()
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller dao = new Controller();



                String s1 = jtname.getText();
                String s2 = jtid2.getText();
                String s3 = jtpw.getText();
                String s4 = jtpn.getText();
                String s5 = jtbd2.getText();
                String s6 = jtgen1.getText();

                System.out.println(s1 + s2 + s3 + s4 + s5 + s6);
                System.out.println(s2);
               try {
                   Model m = new Model(s1, s2, s3, s4, s5, s6);
                   int k = dao.insertBoard(m);
                   if(k == 1) {

                       JOptionPane.showMessageDialog(null, "가입성공");
                   jpcreate.setVisible(false);
                   jplog.setVisible(true);
                   jtname.setText("");
                   jtid2.setText("");
                   jtpw.setText("");
                   jtpn.setText("");
                   jtbd2.setText("");
                   jtgen1.setText("");
                   }
                   else{
                       JOptionPane.showMessageDialog(null,"가입실패 입력하신 항복을 다시 한번 확인해 주세요");
                   }

               }catch (Exception e2){
                   JOptionPane.showMessageDialog(null,"가입실패 입력하신 항복을 다시 한번 확인해 주세요");

            } }

            });

        jbgolog = new JButton("로그인창 돌아가기");
        jbgolog.setBounds(190, 290, 150, 33);

        jpcreate.add(jbgolog);
        jbgolog.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jpcreate.setVisible(false);
                jpafterLog.setVisible(false);
                jplog.setVisible(true);
            }
        });

//-------------------------------------------------------------------------3. 로그인 후 들어간 창 ----------------------------------------------------------------
        jpafterLog.setBounds(100,100,900,500);
        contentPane.add(jpafterLog);
        jpafterLog.setLayout(null);

        JLabel manager  = new JLabel("사용자 관리 창 ");
        manager.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 28));
        manager.setBounds(130, 0, 342, 35);
        jpafterLog.add(manager);

        jbadd = new JButton("회원탈퇴");
        jbadd.setBounds(40, 100, 400, 35);
        jbadd.setFont(new Font("굴림", Font.BOLD, 26));
        jpafterLog.add(jbadd);
        jbadd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int k = JOptionPane.showConfirmDialog(null,"정말로 삭제하시겠습니까?");
                if(k == 0){
                    Controller dao = new Controller();
                    dao.deleteBoard(instancev);
                    JOptionPane.showMessageDialog(null,"삭제되었습니다.");
                    jpcreate.setVisible(false);
                    jpafterLog.setVisible(false);
                    jplog.setVisible(true);
                    jtid.setText("");
                    jtpw1.setText("");

                }
                else{
                    JOptionPane.showMessageDialog(null,"잘 생각했어요~ ");

                }
            }
        });




        jbsearch = new JButton("전체 유저 보기, 삭제(관리자)");
        jbsearch.setBounds(40, 150, 400, 35);
        jbsearch.setFont(new Font("굴림", Font.BOLD, 26));
        jpafterLog.add(jbsearch);
        jbsearch.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if(instancev.getUserId().equals("aaaa")){
                                        jptable.setVisible(true);
                                        jpafterLog.setVisible(false);}
                                        else{
                                            JOptionPane.showMessageDialog(null,"관리자 전용 접근 기능입니다.");
                                            jpafterLog.setVisible(true);
                                        }
                                    }
                                });

                jbsearch = new JButton("나의 정보 바꾸기");
        jbsearch.setBounds(40, 200, 400, 35);
        jbsearch.setFont(new Font("굴림", Font.BOLD, 26));
        jpafterLog.add(jbsearch);
        jbsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    jpupdate.setVisible(true);
                    jpafterLog.setVisible(false);

            }
        });


        jboutlogin = new JButton("로그아웃");
        jboutlogin.setBounds(40, 250, 400, 35);
        jboutlogin.setFont(new Font("굴림", Font.BOLD, 26));
        jpafterLog.add(jboutlogin);
        jboutlogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jpcreate.setVisible(false);
                jpafterLog.setVisible(false);
                jplog.setVisible(true);
                jtid.setText("");
                jtpw1.setText("");
            }
        });
//-----------------------------------------------------------------------------------------------------------------------------------
        jptable.setBounds(0,0,900,800);
        contentPane.add(jptable);
        jptable.setLayout(null);

        ja = new JTextArea();
        ja.setBounds(0,0,900,500);
        jptable.add(ja);
        Controller dao2 = new Controller();
        ArrayList<Model> mo = new ArrayList<>();
        mo = (ArrayList<Model>) dao2.getBoardList();

        ja.setText("");
        ja.append("                                         \t" + "id" + "\t" + "name" + "\t" + "gender\n");
        ja.append("\t" + "---------------------------------------------------------------------------------------------------------------\n");

        for(int i =0; i<mo.size(); i++){
            ja.append("                                        \t" + mo.get(i).getUserId() + " \t " + mo.get(i).getUserName() + " \t  " + mo.get(i).getGender()
                    + "\n");
        }

        JLabel jlde2 = new JLabel("삭제할 아이디를 입력해 주세요");
        jlde2.setBounds(100,600,200,33);
        jptable.add(jlde2);

        jtde2 = new JTextField("");
        jtde2.setBounds(400, 600, 80,33);
        jptable.add(jtde2);


        jbdelete2 = new JButton("삭제");
        jbdelete2.setBounds(500, 600, 80, 33);
        jptable.add(jbdelete2);
        jbdelete2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller dao2 = new Controller();
                String id2 = jtde2.getText();

                Model instance = dao2.getBoard(id2);

                try {
                    dao2.deleteBoard(instance);
                    JOptionPane.showMessageDialog(null,"삭제되었습니다.");
                    jtde2.setText("");
                    jpafterLog.setVisible(true);
                    jptable.setVisible(false);

                }catch (Exception e6){
                    JOptionPane.showMessageDialog(null,"삭제가 안되었네요..? 왜 그럴까요");

                }


            }
        });


                jbdelete2 = new JButton("뒤로가기");
        jbdelete2.setBounds(500, 680, 80, 33);
        jptable.add(jbdelete2);
        jbdelete2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jpcreate.setVisible(false);
                jpafterLog.setVisible(true);
                jplog.setVisible(false);
                jptable.setVisible(false);

            }
        });

//------------------------------------------------------------------------------------------------------- 유저 수정 관리자 전용 ----------------------------------------------------------------------

        jpupdate.setBounds(0,0,900,800);
        contentPane.add(jpupdate);
        jpupdate.setLayout(null);




        JLabel createTu = new JLabel("회원 수정");
        createTu.setFont(new Font("굴림", Font.BOLD, 28));
        createTu.setBounds(190, 0, 342, 35);
        jpupdate.add(createTu);


        JLabel jlnameu = new JLabel("이름");
        jlnameu.setBounds(70, 60, 40, 22);
        jpupdate.add(jlnameu);


        jtnameu = new JTextField();
        jtnameu.setBounds(140, 60, 199, 30);
        jtnameu.setColumns(10);
        jpupdate.add(jtnameu);

        JLabel jlid2u = new JLabel("ID");
        jlid2u.setBounds(70, 95, 40, 22);
        jpupdate.add(jlid2u);



        jtid2u = new JTextField();
        jtid2u.setBounds(140, 95, 199, 30);
        jtid2u.setColumns(10);
        jpupdate.add(jtid2u);

        check2 = new JButton("수정");
        check2.setBounds(360,95,200,30);
        jpupdate.add(check2);

        check2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller dao2 = new Controller();
                String s1 = jtnameu.getText();
                String s2 = jtid2u.getText();
                String s3 = jtpwu.getText();
                String s4 = jtpnu.getText();


                System.out.println(s3 + " 안녕하세요");
                try {
                    Model model = new Model();
                    Model model2 = dao2.getBoard(s2);
                    Model model3 = dao2.getBoard(instancev.userid);
                    Model model1 = model2;
                    System.out.println("이 모델의 아이디는 " + model2.getUserId());
                    System.out.println(model2.getUserName() + model2.getUserId() + model2.getUserPw() + model2.getPhoneN() + model2.getPhoneN() + model2.getGender());
                    if (model3.getUserId().equals("aaaa")) {
                        dao2.deleteBoard(model2);
                        model1.setUserName(s1);
                        model1.setUserPw(s3);
                        model1.setPhoneN(s4);
                        int p = dao2.insertBoard(model1);
                        if (p == 1) {
                            JOptionPane.showMessageDialog(null, "수정이 완료되었습니다. ");
                            //                        jpafterLog.setVisible(true);
                            //                        jpupdate.setVisible(false);
                            jtnameu.setText("");
                            jtid2u.setText("");
                            jtpwu.setText("");
                            jtpnu.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "이런 아이디는 존재하지 않습니다 아이디를 다시한번 확인해 주실 수 있나요? ");
                        }


                    }else{
                        if(model2.getUserId().equals(instancev.getUserId())){
                            dao2.deleteBoard(model2);
                            model1.setUserName(s1);
                            model1.setUserPw(s3);
                            model1.setPhoneN(s4);
                            int p = dao2.insertBoard(model1);
                            if (p == 1) {
                                JOptionPane.showMessageDialog(null, "수정이 완료되었습니다. ");
                                //                        jpafterLog.setVisible(true);
                                //                        jpupdate.setVisible(false);
                                jtnameu.setText("");
                                jtid2u.setText("");
                                jtpwu.setText("");
                                jtpnu.setText("");
                            } else {
                                JOptionPane.showMessageDialog(null, "수정이 안됬다네요..? 아이디를 다시한번 확인해 주세요");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "관리자 이외의 유저는 자신의 아이디만 수정 가능합니다 자신의 아이디를 적어주세요");
                        }
                    }
                }catch (Exception e4){
                    JOptionPane.showMessageDialog(null,"이런 아이디는 존재하지 않습니다 다시 입력해 주세요.");

                }
            }
        });
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                Controller dao2 = new Controller();
//                String s1 = jtnameu.getText();
//                String s2 = jtid2u.getText();
//                String s3 = jtpwu.getText();
//                String s4 = jtpnu.getText();
//
//                System.out.println(s3 + " 안녕하세요");
//                try{
//                    Model model = new Model();
//                    Model model2 = dao2.getBoard(s2);
////                    model.setUserName(s1);
////                    model.setUserId(s2);
////                    model.setUserPw(s3);
////                    model.setPhoneN(s4);
////                    model.setGender(model2.getGender());
////                    model.setBirthday(model2.getBirthday());
//                    String s5 = model2.getBirthday();
//                    String s6 = model2.getGender();
//                    System.out.println(model.getUserName() + model2.getBirthday() + model.getGender());
//                    int q = dao2.updateBoard(s1, s2, s3, s4, s5, s6);
//                    if(q == 1){
//                        JOptionPane.showMessageDialog(null,"수정이 완료되었습니다. ");
//                        jpafterLog.setVisible(true);
//                        jpupdate.setVisible(false);
//
//                    }
//
//
//                }catch (Exception e4){
//                    JOptionPane.showMessageDialog(null,"이런 아이디는 존재하지 않습니다 다시 입력해 주세요.");
//
//                }
//
//            }
//
//        });

        JLabel jlpwu = new JLabel("PW");
        jlpwu.setBounds(70, 130, 40, 22);
        jpupdate.add(jlpwu);


        jtpwu = new JTextField();
        jtpwu.setBounds(140, 130, 199, 30);
        jtpwu.setColumns(10);
        jpupdate.add(jtpwu);


        JLabel jlpnu = new JLabel("전화번호");
        jlpnu.setBounds(50, 165, 90, 22);
        jpupdate.add(jlpnu);


        jtpnu = new JTextField();
        jtpnu.setBounds(140, 165, 199, 30);
        jtpnu.setColumns(15);
        jpupdate.add(jtpnu);


        delete3 = new JButton("뒤로가기");
        delete3.setBounds(500, 200, 80, 33);
        jpupdate.add(delete3);
        delete3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jpcreate.setVisible(false);
                jpafterLog.setVisible(true);
                jplog.setVisible(false);
                jptable.setVisible(false);
                jpupdate.setVisible(false);
                jtnameu.setText("");
                jtid2u.setText("");
                jtpwu.setText("");
                jtpnu.setText("");

            }
        });

        }


        }



