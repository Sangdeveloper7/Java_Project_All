import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;


public class View extends JFrame implements ActionListener {
    public JFrame jf = new JFrame();
    public JFileChooser chooser = new JFileChooser();

    public BufferedImage bufferedImageO = new BufferedImage(500, 700, BufferedImage.TYPE_INT_RGB);
    public BufferedImage bufferedImageB = new BufferedImage(450, 650, BufferedImage.TYPE_INT_RGB);
    public BufferedImage bufferedImageE = new BufferedImage(450, 650, BufferedImage.TYPE_INT_RGB);
    public BufferedImage bufferedImageBr = new BufferedImage(500, 700, BufferedImage.TYPE_INT_RGB);

    public BufferedImage bufferedImage2 = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
    public double [][] k;
    public double [][][] w = null;
    public double [] filter = {0.088, 0.107, 0.088, 0.107, 0.214, 0.107, 0.088, 0.107, 0.088};
    public JPanel  jp1 = new JPanel();
    public JPanel  jp2 = new JPanel();
    public JButton jb1 = new JButton();
    public JButton jb = new JButton();
    public JButton jb2 = new JButton();
    public JButton jb3 = new JButton();
    public JButton jb4 = new JButton();
    public JButton jb5 = new JButton();
    public  JSlider slider = new JSlider(JSlider.HORIZONTAL,0,30,5);
    double q = 0.0;
    public ChangeListener cl;
    public ImageIcon imageicon;
    public JLabel jl1 = new JLabel();
    public JLabel jl2 = new JLabel();
    public JLabel jl3 = new JLabel();
    boolean flag = true;


//    public int width = bufferedImage1.getWidth();
//    public int height = bufferedImage1.getHeight();
//    public int[] pixels = new int[width * height];
//    PixelGrabber grabber = new PixelGrabber(bufferedImage1, 0, 0, width, height, pixels, 0,width);


    View(){
        GUI_show();
    }

    public void GUI_show(){
        setBounds(0,0,1300, 1000);
        setTitle("상현이가 만든 Image Processing 입니다");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        jb = new JButton("불러오기");
        jb.setBounds(30,10,160,80);
        add(jb);
        jb.addActionListener(this);

        jb1 = new JButton("흑백영상");
        jb1.setBounds(200,10,160,80);
        add(jb1);
        jb1.addActionListener(this);

        jb2 = new JButton("대비");
        jb2.setBounds(370,10,160,80);
        add(jb2);
        jb2.addActionListener(this);

        jb3 = new JButton("밝기");
        jb3.setBounds(540,10,160,80);
        add(jb3);
        jb3.addActionListener(this);

        jb4 = new JButton("스무싱");
        jb4.setBounds(710,10,160,80);
        add(jb4);
        jb4.addActionListener(this);


        jl3 = new JLabel("밝기 조정");
        jl3.setBounds(1050, 0,80,20);
        add(jl3);


        slider = new JSlider();
        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);


        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider jSlider = (JSlider) e.getSource();

                 q = jSlider.getValue();
            }
        });

        slider.addChangeListener(cl);
        slider.setBounds(900, 20, 360, 80);
        add(slider);


//        add(jp1);
        jl2 = new JLabel();
        jl2.setSize(500,700);
        jl1.setSize(500, 700);
        jp1.add(jl1);




        jp1 = new JPanel();
        jp1.setBounds(100, 150,500,700);

        jl2 = new JLabel();






    }

//    public static double[][] changeBlack(BufferedImage bi){
//        double[][] result = new double[bi.getHeight()][bi.getWidth()];
//        for(int y = 0; y<bi.getHeight(); y++){
//            for(int x=0; x<bi.getWidth(); x++){
//                Color c = new Color(bi.getRGB(x,y));
//                result[y][x] += c.getRed();
//                result[y][x] += c.getGreen();
//                result[y][x] += c.getBlue();
//                result[y][x] /= 3;
//
//            }
//        }
//        return result;
//    }
    public static BufferedImage makeBuf(BufferedImage bi) {
        BufferedImage result = new BufferedImage(450, 650, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < bi.getHeight()-1; y++) {
            for (int x = 0; x < bi.getWidth()-1; x++) {
                Color color = new Color(bi.getRGB(x, y));
                int X = (int) (color.getRed());
                int Y = (int) (color.getGreen());
                int Z = (int) (color.getBlue());
                System.out.println(Y + " 이제부터 색상 정수 값 " + (int) color.getRed());
                result.setRGB(x, y, new Color(X, Y, Z).getRGB());
            }
        }return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("불러오기")) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg and png", "jpg");
            chooser.setFileFilter(filter);
            int ret = chooser.showOpenDialog(null);
            if (ret != JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.");
                return;
            } else {
                String filePath = chooser.getSelectedFile().getPath();
                System.out.println(filePath);
                File file = new File(filePath);
                imageicon = new ImageIcon(filePath);
                jl1.setIcon(imageicon);
                jp1.add(jl1);
                jp1.setBackground(Color.YELLOW);
                add(jp1);
                try {
                    bufferedImageO = ImageIO.read(file);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    bufferedImageBr = ImageIO.read(file);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                jl2 = new JLabel(new ImageIcon(bufferedImageBr));

                jp2.setBounds(700, 150, 500, 700);
                jp2.add(jl2);
                jp2.setBackground(Color.YELLOW);
                add(jp2);
            }
        } else if (e.getActionCommand().equals("스무싱")) {
            if(flag == true){
                for(int y = 1; y < bufferedImageO.getHeight()-1; y++) {
                    for(int x = 1; x < bufferedImageO.getWidth()-1; x++){

                        int Xavg = 0;
                        int Yavg = 0;
                        int Zavg = 0;
                        int k = 0;

                        for(int a=-1; a < 2; a++){
                            for(int b = -1; b < 2; b++){
                                Color color = new Color(bufferedImageO.getRGB(x+a, y+b));

                                Xavg += (int)(color.getRed());

                                Yavg += (int)(color.getGreen());

                                Zavg += (int)(color.getBlue());




                            }
                        }
                        Xavg = Xavg /9;
                        Yavg = Yavg /9;
                        Zavg = Zavg /9;

                        for(int a=-1; a < 2; a++){
                            for(int b = -1; b < 2; b++){
                                bufferedImageBr.setRGB(x+a, y+b, new Color(Xavg, Yavg,Zavg).getRGB());
                            }
                        }

                    }

                }
            }



            jl2.setIcon(new ImageIcon(bufferedImageBr));
            jp2.add(jl2);
            add(jp2);
            flag = false;
            System.out.println("오아ㅜ --------------------------------------------------------------------");


        } else if (e.getActionCommand().equals("흑백영상")) {

            if (flag == true) {
                for (int y = 0; y < bufferedImageO.getHeight(); y++) {
                    for (int x = 0; x < bufferedImageO.getWidth(); x++) {
                        Color color = new Color(bufferedImageO.getRGB(x, y));
                        int Y = (int) (0.333 * color.getRed() + 0.333 * color.getGreen() + 0.333 * color.getBlue());
                        System.out.println(Y + " 이제부터 색상 정수 값 " + (int) color.getRed());
                        bufferedImageBr.setRGB(x, y, new Color(Y, Y, Y).getRGB());
                    }
                }

//            jl2 = new JLabel(new ImageIcon(bufferedImageBr));
                jl2.setIcon(new ImageIcon(bufferedImageBr));
                jp2.add(jl2);
                add(jp2);
                flag = false;
                System.out.println("오아ㅜ --------------------------------------------------------------------");
            } else {
//                bufferedImageO = bufferedImageBr;
//                BufferedImage buf = makeBuf(bufferedImageO);

//                bufferedImageBr = buf;

                jl2.setIcon(new ImageIcon(bufferedImageO));

                jp2.add(jl2);
                add(jp2);
                flag = true;

                System.out.println("초기화임 ");
//                jl2 = new JLabel(new ImageIcon(bufferedImageBr));
//                jp2 = new JPanel();
//                jp2.setBounds(600, 150, 500, 700);
//                jp2.add(jl2);
//                add(jp2);

            }
        } else if (e.getActionCommand().equals("밝기")) {
//           JSlider value = (JSlider)e.getSource();
//           double size = value.getValue();
//           System.out.println(size);
            int bright = slider.getValue();
            bright = bright * 2;


                System.out.println(bright+ " 222222222222222222222222222222");
            if (flag == true) {
                for (int y = 0; y < bufferedImageO.getHeight(); y++) {
                    for (int x = 0; x < bufferedImageO.getWidth(); x++) {
                        Color color = new Color(bufferedImageO.getRGB(x, y));
//                    System.out.println((int)color.getRed() + "   "  + (int)color.getBlue() + "   " + (int)color.getGreen());
                        int a = (int) color.getRed();
                        int b = (int) color.getGreen();
                        int c = (int) color.getBlue();


                        int X = (int) Math.min(a + bright, 255);
                        int Y = (int) Math.min(b + bright, 255);
                        int Z = (int) Math.min(c + bright, 255);

                        bufferedImageBr.setRGB(x, y, new Color(X, Y, Z).getRGB());

                    }
                }
                jl2.setIcon(new ImageIcon(bufferedImageBr));
                jp2.setBounds(700, 150, 500, 700);
                jp2.add(jl2);
                jp2.setBackground(Color.YELLOW);
                add(jp2);
                flag = false;
            } else {
                jl2.setIcon(new ImageIcon(bufferedImageO));

                jp2.add(jl2);
                add(jp2);
                flag = true;
            }}

//        } else if (e.getActionCommand().equals("edge")) {
//            for(int y =0; y<bufferedImageO.getHeight(); y++){
//                for(int x=0; x<bufferedImageO.getWidth(); x++){
//                    Color color = new Color(bufferedImageO.getRGB(x, y));
//                    int Y = (int) (0.333 * color.getRed() + 0.333 * color.getGreen() + 0.333 * color.getBlue());
//                    k[y][x] = Y;
//                }
//            }
//            int q = 0;
//            for(int y =0; y<bufferedImageO.getHeight(); y++){
//                for(int x=0; x<bufferedImageO.getWidth(); x++){
//                    double X = k[y][x];
//
//
//                }
//            }
//
//            for(int y =0; y<bufferedImageO.getHeight(); y++){
//                for(int x=0; x<bufferedImageO.getWidth(); x++){
//                    Color color = new Color(bufferedImageO.getRGB(x, y));
//                    if(q > 8){
//                        q = 0;
//                    }
//                    k[y][x] = k[y][x] * filter[q];
//                    q++;
//
//                }
//            }
            else if(e.getActionCommand().equals("대비")){
                if(flag == true) {
                    int bright = slider.getValue();
                    bright = bright * 2;
                    for (int y = 0; y < bufferedImageO.getHeight(); y++) {
                        for (int x = 0; x < bufferedImageO.getWidth(); x++) {
                            Color color = new Color(bufferedImageO.getRGB(x, y));
//                    System.out.println((int)color.getRed() + "   "  + (int)color.getBlue() + "   " + (int)color.getGreen());
                            int a = (int) color.getRed();
                            int b = (int) color.getGreen();
                            int c = (int) color.getBlue();
                            if (a >= 128) {
                                a = Math.min(a + bright, 255);
                            } else if (a < 128) {
                                a = Math.max(a - bright, 0);
                            }
                            if (b >= 128) {
                                b = Math.min(b + bright, 255);
                            } else if (b < 128) {
                                b = Math.max(b - bright, 0);
                            }

                            if (c >= 128) {
                                c = Math.min(c + bright, 255);
                            } else if (c < 128) {
                                c = Math.max(c - bright, 0);
                            }


                            bufferedImageBr.setRGB(x, y, new Color(a, b, c).getRGB());

                        }
                    }
                    jl2.setIcon(new ImageIcon(bufferedImageBr));
                    jp2.setBounds(700, 150, 500, 700);
                    jp2.add(jl2);
                    jp2.setBackground(Color.YELLOW);
                    add(jp2);
                    flag = false;
                }
                else{
                        jl2.setIcon(new ImageIcon(bufferedImageO));
                        jp2.add(jl2);
                        add(jp2);
                        flag = true;
                }
            }
//
        }



}

