package graphicEditor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;
import java.awt.*;


public class editor extends JFrame implements ActionListener{
	
	private Mypanel panel = new Mypanel();
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	public String version1= "";
	public Color color1 = Color.BLACK;
	public Boolean fill1 = false;
	public Stroke stroke1 = new BasicStroke(5);
	public int polycount;
	public String text="";
	public int textsize = 20;
	public JFileChooser chooser = new JFileChooser();
	public Image img = null;
	public String fileR = ""; 
	public BufferedImage img2 = new BufferedImage(1000, 800, BufferedImage.TYPE_INT_RGB);
	
	
	
	public editor()   {
		setSize(WIDTH, HEIGHT);
		setTitle("상현이가 만든 그림판 입니다.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		
//		Container contentPane = getContentPane();
//		contentPane.setLayout(new BorderLayout());
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		
		JMenu Menu = new JMenu("All function");
		
		JMenu Menu1 = new JMenu("Draw");
		JMenuItem m;
		m = new JMenuItem("line");
		m.addActionListener(this);
		Menu1.add(m);
		
		m = new JMenuItem("circle");
		m.addActionListener(this);
		Menu1.add(m);
		
		m = new JMenuItem("rectengle");
		m.addActionListener(this);
		Menu1.add(m);
		
		m = new JMenuItem("pen");
		m.addActionListener(this);
		Menu1.add(m);
		
		m = new JMenuItem("text");
		m.addActionListener(this);
		Menu1.add(m);
		
		
		Menu1.add(m);
		Menu.add(Menu1);
		
		
		
		JMenu Menu2 = new JMenu("Pro");
		
		
		JMenu Menu2_1 = new JMenu("Color");
		
		m = new JMenuItem("Black");
		m.addActionListener(this);
		Menu2_1.add(m);
		
		m = new JMenuItem("Red");
		m.addActionListener(this);
		Menu2_1.add(m);
		
		m = new JMenuItem("Blue");
		m.addActionListener(this);
		Menu2_1.add(m);
		
		m = new JMenuItem("Green");
		m.addActionListener(this);
		Menu2_1.add(m);
		
		Menu2.add(Menu2_1);
		
		m = new JMenuItem("fill");
		m.addActionListener(this);
		Menu2.add(m);
		
		
		JMenu Menu2_2 = new JMenu("thick");
		
		m = new JMenuItem("5");
		m.addActionListener(this);
		Menu2_2.add(m);
		
		m = new JMenuItem("10");
		m.addActionListener(this);
		Menu2_2.add(m);
		
		m = new JMenuItem("15");
		m.addActionListener(this);
		Menu2_2.add(m);
		
		m = new JMenuItem("20");
		m.addActionListener(this);
		Menu2_2.add(m);
		
		m = new JMenuItem("25");
		m.addActionListener(this);
		Menu2_2.add(m);
		
		Menu2.add(Menu2_2);
		
		Menu.add(Menu2);
		
		JMenu Menu3 = new JMenu("Add function");
		
		JMenu Menu3_1 = new JMenu("Erase");
		
		m = new JMenuItem("Erase circle");
		m.addActionListener(this);
		Menu3_1.add(m);
		
		m = new JMenuItem("Erase rectengle");
		m.addActionListener(this);
		Menu3_1.add(m);
		
		m = new JMenuItem("Erase line");
		m.addActionListener(this);
		Menu3_1.add(m);
		
		Menu3.add(Menu3_1);
		
		m = new JMenuItem("rotate");
		m.addActionListener(this);
		Menu3.add(m);
		
		m = new JMenuItem("Save");
		m.addActionListener(this);
		Menu3.add(m);
		
		m = new JMenuItem("Calling up");
		m.addActionListener(this);
		Menu3.add(m);
		
		Menu.add(Menu3);
		
		JMenuBar mBar = new JMenuBar();
		mBar.add(Menu);
		setJMenuBar(mBar);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new editor();
	}
	
	class Mypanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
		private Vector<Point> vStart = new Vector<Point>();
		private Vector<Point> vEnd = new Vector<Point>();
		private Vector<Color> vColor = new Vector<Color>();
		private Vector<String> vVer = new Vector<String>();
		private Vector<Boolean> vFill = new Vector<Boolean>();
		private Vector<Stroke> vStroke = new Vector<Stroke>();
		private Vector<String> vText = new Vector<String>();
		private Point tempEnd = new Point();
		private Point tempStart = new Point();
		private boolean bandbul = true;
		
		

		
		int width = 0;
		int height = 0;
		int minX = 0;
		int minY = 0;
		
		
		
		public Mypanel() {
			
			
//			
//			JPanel textPanel = new JPanel();
//			textPanel.setBackground(getForeground());
			addMouseListener(this);
			addMouseMotionListener(this);
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
			if(version1.equals("pen")) {
			Point EndP = e.getPoint();
			vEnd.add(EndP);
			repaint();
			
			Point FirstP = e.getPoint();
			vStart.add(FirstP);
			vVer.add(version1); 
			vStroke.add(stroke1);
			vColor.add(color1);
			vFill.add(fill1);
			vText.add(text);
			}
			else{
				if(bandbul == true) {
				tempEnd = e.getPoint();
		
				repaint();
				
				}
				
			}		
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			
			
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			
//			if(fileR.equals("read") && (version1.isEmpty())) {
//				repaint();
//			}
		
			tempStart = e.getPoint();
			vStart.add(tempStart);
			vVer.add(version1);
			vStroke.add(stroke1);
			vColor.add(color1);
			vFill.add(fill1);
			vText.add(text);
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			
			Point EndP = e.getPoint();
			vEnd.add(EndP);
			// vVer.add(version1);
			//vStroke.add(stroke1);
			bandbul = false;
			repaint();
			System.out.println("경계선 --------------- \n\n");
			
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			
			
		}
		
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			Graphics2D g2= (Graphics2D)g;
			Graphics2D g3 = img2.createGraphics();
			g3.setColor(Color.white);
			g3.fillRect(0, 0, 1000, 800);
			
			
			if(fileR.equals("read")) {
				
				System.out.println(img == null);
				g2.drawImage(img, 0, 0, 1000, 800, this);
				//repaint();
			}
			
			
			System.out.println(vStart.size() + " 사이즈가 달라요 ");
			System.out.println(vEnd.size());
			
			System.out.println("경계선 --------------- \n\n");
			
			width = Math.abs((int)(tempEnd.getX() - tempStart.getX()));
			height = Math.abs((int)(tempEnd.getY() - tempStart.getY()));
			
			minX = Math.min((int)tempStart.getX(), (int)tempEnd.getX());
			minY = Math.min((int)tempStart.getY(), (int)tempEnd.getY());
			
			
			if(bandbul == true) {
			if(version1.equals("line") && (bandbul== true)) {
				g2.drawLine((int)tempStart.getX(), (int)tempStart.getY(), (int)tempEnd.getX(), (int)tempEnd.getY());
			}
			
			else if(version1.equals("circle") && (bandbul== true)) {
					g2.drawOval(minX, minY, width, height);
				}
			else if(version1.equals("rectengle") && (bandbul== true)) {
					g2.drawRect(minX, minY, width, height);
			}
			}
			
	
			for(int i= 0; i< vEnd.size(); i++) {

				String v = vVer.get(i);
				Stroke st = vStroke.get(i);
				Color c = vColor.get(i);
				Point s = vStart.get(i);
				Point e = vEnd.get(i);
				boolean b = vFill.get(i);
				String t = vText.get(i);
				
				g2.setColor(c);
				g2.setStroke(st);
				g3.setColor(c);
				g3.setStroke(st);
				
				
				width = Math.abs((int)(e.x - s.x));
				height = Math.abs((int)(e.y - s.y));
				
				minX = Math.min((int)s.x, (int)e.x);
				minY = Math.min((int)s.y, (int)e.y);
				
				if(v.equals("text")){
					g2.setFont(new Font("궁서",Font.BOLD, textsize));
					System.out.println(t);
					g2.drawString(t, (int)s.getX(), (int)s.getY());
					g3.drawString(t, (int)s.getX(), (int)s.getY());
				}
				
				else if(v.equals("line") && (bandbul== false)) {
					g2.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());
					g3.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());
				
				}
				
				else if(v.equals("pen")) {
					
					g2.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());
					g3.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());

				}
				
				else if((v.equals("circle")) && (b== true) && (bandbul== false)) {
						g.fillOval(minX, minY, width, height);
						g3.fillOval(minX, minY, width, height);
					}
				else if((v.equals("rectengle")) && (b == true) && (bandbul== false)) {
						g.fillRect(minX, minY, width, height);
						g3.fillRect(minX, minY, width, height);
					}
					
				else if(v.equals("circle") && (bandbul== false)) {
						g2.drawOval(minX, minY, width, height);
						g3.drawOval(minX, minY, width, height);
					}
				else if(v.equals("rectengle") && (bandbul== false)) {
						g2.drawRect(minX, minY, width, height);
						g3.drawRect(minX, minY, width, height);
				}
				// 여기서 부터는 bandbul이 true 일때 
				
				else if(v.equals("line") && (bandbul== true)) {
					g2.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());
					g3.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());
				}

				else if((v.equals("circle")) && (b== true) && (bandbul== true)) {
					g.fillOval(minX, minY, width, height);
					g3.fillOval(minX, minY, width, height);
				}
				else if((v.equals("rectengle")) && (b == true) && (bandbul== true)) {
					g.fillRect(minX, minY, width, height);
					g3.fillRect(minX, minY, width, height);
				}
				
				else if(v.equals("circle") && (bandbul== true)) {
					g2.drawOval(minX, minY, width, height);
					g3.fillRect(minX, minY, width, height);
				}
				else if(v.equals("rectengle") && (bandbul== true)) {
					g2.drawRect(minX, minY, width, height);
					g3.drawRect(minX, minY, width, height);
				}
						
			}
			
			System.out.println("디버깅 두번째 버전버전 ");
			bandbul = true;
			
				}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}			
			
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("line")){
			 version1 = "line";
		}
		else if(e.getActionCommand().equals("circle")) {
			version1 = "circle";
			
		}
		else if(e.getActionCommand().equals("rectengle")) {
			version1 = "rectengle";
		}
		else if(e.getActionCommand().equals("pen")) {
			version1 = "pen";
		
			
		}
		else if(e.getActionCommand().equals("Black")) {
			color1 = Color.BLACK;
			
		}
		else if(e.getActionCommand().equals("Red")) {
			color1 = Color.RED;
			
		}
	
		else if(e.getActionCommand().equals("Blue")) {
			color1 = Color.BLUE;
			
		}
		else if(e.getActionCommand().equals("Green")) {
			color1 = Color.GREEN;
			
		}
		else if(e.getActionCommand().equals("fill")) {
			fill1 = true;
		}
		
		else if(e.getActionCommand().equals("5")) {
			stroke1 = new BasicStroke(5);
			textsize = 5;
		}
		else if(e.getActionCommand().equals("10")) {
			stroke1 = new BasicStroke(10);
			textsize = 10;
		}
		else if(e.getActionCommand().equals("15")) {
			stroke1 = new BasicStroke(15);
			textsize = 15;
		}
		else if(e.getActionCommand().equals("20")) {
			stroke1 = new BasicStroke(20);
			textsize = 20;
		}
		else if(e.getActionCommand().equals("25")) {
			stroke1 = new BasicStroke(25);
			textsize = 25;
		}
		else if(e.getActionCommand().equals("fill")) {
			fill1 = true;
		}
		
		else if(e.getActionCommand().equals("text")) {
			version1 = "text";
			System.out.println("입력할 텍스트를 적어주세요!");
			Scanner s = new Scanner(System.in);
			text = s.nextLine(); 
		}
		else if(e.getActionCommand().equals("Calling up")) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "txt");
			chooser.setFileFilter(filter);
			
			int ret = chooser.showOpenDialog(null);
			if(ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", " 경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else {
				String filePath = chooser.getSelectedFile().getPath();
				img = new ImageIcon(filePath).getImage();
				fileR = "read";
				
						
			}
			
			
			
		}
		
		
		else if(e.getActionCommand().equals("Save")) {
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "txt");
			chooser.setFileFilter(filter);
			fileR = "Write";
		
			
			int ret = chooser.showSaveDialog(null);
			if(ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", " 경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else { 
				String filePath = chooser.getSelectedFile().getPath();
				
				filePath += ".jpg";
				
				System.out.println(filePath);
				File file = new File(filePath);
				
				try {
					ImageIO.write(img2, "jpg", file);					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
		
	}
}
