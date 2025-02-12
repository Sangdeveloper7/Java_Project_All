import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main extends JFrame implements ActionListener {

    private tablePanel tp = new tablePanel();
    private JPanel jp1 = new JPanel();
    private JPanel jp = new JPanel();
    private JPanel jp2 = new JPanel();
    public JPanel jp3 = new JPanel();
    public JPanel jp4 = new JPanel();
    private JPanel jp5 = new JPanel();

    private JLabel jl1 = new JLabel("흑");

    private JLabel jl2 = new JLabel("백");
    public static JLabel jl3 = new JLabel("2");
    public static JLabel jl4 = new JLabel("2");

    private JButton jb1 = new JButton("리셋");




    public Main() throws IOException {


        setSize(1250,900);
        setTitle("상현이가 만든 오델로 게임 입니다");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.setLayout(null);
        tp.setBounds(400,0,800,800);
        jp.setLayout(null);
        jp.setBounds(0,0,400,800);
        jp1.setLayout(null);
        jp2.setLayout(null);
        jp3.setLayout(null);
        jp4.setLayout(null);
        jp5.setLayout(null);

        jp1.setBackground(Color.black);
        jp2.setBackground(Color.WHITE);
        jp3.setBackground(Color.BLACK);
        jp4.setBackground(Color.WHITE);
        jb1.setBackground(Color.GRAY);
        jb1.setBounds(120,100,150,150);
        jb1.addActionListener(this);
        jp5.add(jb1);
        jp5.setBackground(Color.DARK_GRAY);
        jp.add(jp1);
        jp.add(jp2);
        jp.add(jp3);
        jp.add(jp4);


        jp.add(jp5);




        jp.setBorder(new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(Color.BLACK);
                g.setFont(new Font("맑은고딕", Font.BOLD, 20));
                g.drawLine(200,200,200,100);
                g.drawLine(50,300,200,300);
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return null;
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        jp1.setBounds(0,0,200,200);
        jp2.setBounds(200,0,200,200);
        jp3.setBounds(0,200,200,200);
        jp4.setBounds(200,200,200,200);

        jp5.setBounds(0,400,400,400);

        jl3.setForeground(Color.WHITE);
        jl4.setForeground(Color.BLACK);
        jl1.setForeground(Color.WHITE);
        jl2.setForeground(Color.BLACK);
        jl1.setFont(new Font("맑은고딕", Font.BOLD, 30));
        jl2.setFont(new Font("맑은고딕", Font.BOLD, 30));
        jl3.setFont(new Font("맑은고딕", Font.BOLD, 30));
        jl4.setFont(new Font("맑은고딕", Font.BOLD, 30));


        jl1.setBounds(80,50,50,50);
        jl2.setBounds(80,50,50,50);
        jl3.setBounds(80,50,80,60);
        jl4.setBounds(80,50,80,50);

        jp4.add(jl4);
        jp3.add(jl3);
        jp1.add(jl1);
        jp2.add(jl2);

        add(tp);
        add(jp);



    }

    public static void main(String[] args) throws IOException {
        Main k = new Main();
        k.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("리셋")){
           tablePanel.flaag = false;
        }
    }
}
