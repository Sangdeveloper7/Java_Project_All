import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class tablePanel extends JPanel implements MouseListener, ActionListener {

    Image black = null;
    Image white = null;
    JLayeredPane layeredPane; //  패널들의 우선순위를 지정하여 패널들을 띄울 수 있다.
    JPanel board;
    JPanel controlPanel;
    JToolBar tool;
    JPanel gui;

    JButton restartButton;
    JButton endButton;
    JButton giveUpButton;
    JButton skipTurnButton;

    JLabel imglabel;
    public static int [][] p = {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,-1,1,0,0,0},{0,0,0,1,-1,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};

    int a = 0;
    int X, Y =0;
    boolean ab = false;
    public int order = 1;
    public static boolean flaag = true;
    public int newx;
    public int newy;
    public int directx;
    public int directy;
    public boolean end = true;
    public int bl = 2;
    public int wh = 2;
    public boolean lineEnd(int y, int x) {
        return(x<0 || x>7 || y<0 || y>7);
    }
    public boolean checkStone(int y, int x){
        return(p[y][x] == order * -1);
    }


    public void checkStone2(int y, int x){

        boolean check = false;

        for(int i =-1; i<2; i ++){
            for(int j=-1; j<2; j++){
                if(lineEnd(y+i,x+j))
                    continue;
                if(p[y+i][x+j]==order*-1){
                    newx = x+j;
                    newy = y+i;
                    while(true){
                        if(lineEnd(newy,newx)||p[newy][newx] == 0){
                            check = true;
                            break;
                        }
                        else if(p[newy][newx] == order) {
                            check = false;
                            break;
                        }
                        else if(p[newy][newx] == order*-1){
                            newx += j;
                            newy += i;
                        }
                    }
                    if(!check){
                        while(newx!=x || newy!=y){
                            newx -= j;
                            newy -= i;
                            p[newy][newx] = order;
                        }
                    }
                }
            }
        }
    }

    public boolean inCheck(int y, int x){ //
        for(int i =-1; i<2; i ++){
            for(int j=-1; j<2; j++){
                if(lineEnd(y+i,x+j)) {
                    continue;
                }
                //System.out.println("--------"+checkStone(y+i,x+j));
                if(p[y+i][x+j]==order*-1){
                    if(check2(y,x,i,j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean check2(int y, int x, int directy, int directx){ // 놓을곳 있는지 확인해주는 체크
        int afterX = x+ directx;
        int afterY = y + directy;
        while(true){
            if(lineEnd(afterY,afterX) || p[afterY][afterX] == 0)
                return false;
            else if(p[afterY][afterX] == order)
                return true;
            else if(checkStone(afterY,afterX))
                afterY += directy;
                afterX += directx;
        }
    }

    public int countStone(int stone){
        int count = 0;
        for(int i =0; i < 8; i++){
            for(int j=0; j<8; j++){
                if(p[i][j] == stone)
                    count++;
            }
        }
        return count;
    }
    public boolean passcheck(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(p[i][j] == 0){
                    if(inCheck(i,j) == false){
                        continue;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public tablePanel() throws IOException {


        addMouseListener(this);
        repaint();
        setSize(800,800);

//        layeredPane = new JLayeredPane();
//        layeredPane.setPreferredSize(size); // setSize와 다른점 -> setSize로 패널을 지정해주면 짤려서 패널이 나오지 않는다 -> Layout 관리시 setPrefferedSize 사용 !!
//
        try {
            white = ImageIO.read(new File("C:\\Users\\sangh\\OneDrive\\바탕 화면\\흰색.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        try {
            black = ImageIO.read(new File("C:\\Users\\sangh\\OneDrive\\바탕 화면\\검은색.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        getImage();
    }


    public void getImage()
    {
        Image whiteImage = white;
        Image blackImage = black;

       Image whiteImageResize = whiteImage.getScaledInstance(100, 100, whiteImage.SCALE_SMOOTH);
       Image blackImageResize = blackImage.getScaledInstance(100, 100, blackImage.SCALE_SMOOTH);

        ImageIcon white1 = new ImageIcon(whiteImageResize);
        ImageIcon black1 = new ImageIcon(blackImageResize);

        white = white1.getImage();
        black = black1.getImage();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int k =1;
        for(int i =0; i < 8; i ++){

            for(int j = 0; j <8; j++) {
                if (k % 2 == 0) {
                    g.setColor(new Color(190, 124, 7));
                    g.fillRect(100 * j, 100 * i, 100, 100);

                } else {
                    g.setColor(new Color(128, 123, 123, 136));
                    g.fillRect(100 * j, 100 * i, 100, 100);

                }
                k++;
            }
            k++;
        }



        for(int i =0; i < 8; i ++){

            for(int j = 0; j <8; j++) {
                if (p[i][j] == 0) {
                    continue;
                } else if(p[i][j] == 1){
                    g.setColor(Color.BLACK);
//                    g.fillOval(100 * j, 100 * i, 100, 100);
                    g.drawImage(black, 100 * j, 100 * i, this);

                }
                else if(p[i][j] == -1){
                    g.setColor(Color.white);
//                    g.fillOval(100 * j, 100 * i, 100, 100);
                    g.drawImage(white, 100 * j, 100 * i, this);
                }
            }
        }








    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {



    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        X = x/100;
        Y = y/100;
        boolean flag = true;
//        boolean k = true;
//        for(int i= 0; i<8; i++){
//            if(k = false){
//                System.out.println("나는 감자 입니다 ");
//                break;
//            }
//            for(int j=0; j<8; j++) {
//                if (inCheck(i, j)) {
//                }
//                else {
//                    k = false;
//                }
//            }
//        }
//        if(k == false){
//            JOptionPane.showMessageDialog(null,"놓을 곳이 없으므로 다음 차례로 넘어가유~~ ");
//            order = order * -1;
//        }



//        if(flag == true){
//            System.out.println("pass라 한턴을 건너 뜁니다. ");
//            JOptionPane.showMessageDialog(null,"놓을 곳이 없으므로 다음 차례로 넘어가유~~ ");
//            order = order * -1;
//        }
        System.out.println(flaag);
        if(flaag == false){
            for(int i =0; i <8; i++){
                for(int j=0; j< 8; j++){
                    p[i][j] = 0;
                }
            }
            p[3][3] = -1;
            p[3][4] = 1;
            p[4][3] = 1;
            p[4][4] = -1;
            order = 1;
            repaint();
        }

        if(bl + wh == 64 || bl == 0 || wh == 0){
            if(bl > wh){
                Main.jl3.setText("승리");
                Main.jl4.setText("패배");
                for(int i =0; i <8; i++){
                    for(int j=0; j< 8; j++){
                        p[i][j] = 0;
                    }
                }
                p[3][3] = -1;
                p[3][4] = 1;
                p[4][3] = 1;
                p[4][4] = -1;
                order = 1;

            }
            else if(wh > bl){
                Main.jl3.setText("패배");
                Main.jl4.setText("승리");
                for(int i =0; i <8; i++){
                    for(int j=0; j< 8; j++){
                        p[i][j] = 0;
                    }
                }
                p[3][3] = -1;
                p[3][4] = 1;
                p[4][3] = 1;
                p[4][4] = -1;
                order = 1;

            }
            else{
                Main.jl3.setText("공동승리");
                Main.jl4.setText("공동승리");
                for(int i =0; i <8; i++){
                    for(int j=0; j< 8; j++){
                        p[i][j] = 0;
                    }
                }
                p[3][3] = -1;
                p[3][4] = 1;
                p[4][3] = 1;
                p[4][4] = -1;
                order = 1;
            }
        }


        if(passcheck() == true){
            System.out.println("pass를 해야 되는 상황입니다 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            JOptionPane.showMessageDialog(null,"패스를 할게용 한턴 넘어갑니당~~~~~~~~~~");
            order = order *-1;
            if(passcheck() == true){
                if(bl > wh){
                    Main.jl3.setText("승리");
                    Main.jl4.setText("패배");
                    for(int i =0; i <8; i++){
                        for(int j=0; j< 8; j++){
                            p[i][j] = 0;
                        }
                    }
                    p[3][3] = -1;
                    p[3][4] = 1;
                    p[4][3] = 1;
                    p[4][4] = -1;
                    order = 1;

                }
                else if(wh > bl){
                    Main.jl3.setText("패배");
                    Main.jl4.setText("승리");
                    for(int i =0; i <8; i++){
                        for(int j=0; j< 8; j++){
                            p[i][j] = 0;
                        }
                    }
                    p[3][3] = -1;
                    p[3][4] = 1;
                    p[4][3] = 1;
                    p[4][4] = -1;
                    order = 1;

                }
                else{
                    Main.jl3.setText("공동승리");
                    Main.jl4.setText("공동승리");
                    for(int i =0; i <8; i++){
                        for(int j=0; j< 8; j++){
                            p[i][j] = 0;
                        }
                    }
                    p[3][3] = -1;
                    p[3][4] = 1;
                    p[4][3] = 1;
                    p[4][4] = -1;
                    order = 1;
                }
            }
        }

        if(inCheck(Y,X)) {
            checkStone2(Y, X);
            p[Y][X] = order;
            order = order * -1;
        }else{
            JOptionPane.showMessageDialog(null,"놓을 수 없습니다");
        }


        bl = countStone(1);
        wh = countStone(-1);
        String black = String.valueOf(bl);
        String white = String.valueOf(wh);

        Main.jl3.setText(black);
        Main.jl4.setText(white);

        System.out.println("검은 돌의 개수는 " + bl + "흰 돌의 갯수는 " + wh);




        repaint();


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
