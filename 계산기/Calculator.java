import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.awt.*;


public class Calculator extends JFrame implements ActionListener{
	
	String sentence;
	double total= 0.0;
	
	// flag 들 선언 각각의 용도 주석처리 
	boolean flag = true;  // 연산자 중복 ex) */*/*/+-+-+ 하는 경우 마지막 연산자만 입력되게 하였습니다 -> 아이폰 계산기랑 비슷하게 나마 구현 
	boolean flag2 = true; // infix string 을 postfix로 변환할 경우 처음 변환시에 생기는 null 값을 substring 해주는데 = 계산 이후에는 null 이 생기지 않아서 처음 값에서만 null값 없애주기 위한 기능입니다. 
	boolean flag3 = true; // 
	boolean flag4 = true; // 소수점 . 을 ..... 이런식으로 여러개 찍는걸 방지하기 위한 기능입니다. 하지만 연산자들이 계산 되고 난 다음에는 소수점이 또 하나만 찍힐 수 있으므로 연산자 기능 후 true 로 변환하였습니다. 
	boolean flag5 = true; // = 연산 뒤에 연산을 또 진행할 때 연산자가 오지 못하도록 하는 것 
	String sentence2 = "";
	String[] sentence5 = new String[50];
	Stack<String> stack = new Stack<>();
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	JPanel jp = new JPanel();
	JTextField tf;
	JTextArea ta;
	
	JButton del = new JButton("->");
	JButton div = new JButton("/");
	JButton mul = new JButton("*");
	JButton plus = new JButton("+");
	JButton minus = new JButton("-");
	
	JButton n1 = new JButton("1");
	JButton n2 = new JButton("2");
	JButton n3 = new JButton("3");
	JButton n4 = new JButton("4");
	JButton n5 = new JButton("5");
	JButton n6 = new JButton("6");
	JButton n7 = new JButton("7");
	JButton n8 = new JButton("8");
	JButton n9 = new JButton("9");
	JButton n0 = new JButton("0");
	
	public static int count = 0;
	
	public Calculator() {
		super("계산기 입니다 ");
		super.setLocationRelativeTo(null);
		
		jp = new JPanel();
		jp.setPreferredSize(new Dimension(200, 450));
	
// 	 	TextField 를 크게 하였습니다 , fontSize 구성 
 		tf = new JTextField(30);
		tf.setPreferredSize(new Dimension(100, 80));
		tf.setFont(new Font("Arial", Font.BOLD, 50));
		add(tf, BorderLayout.NORTH);
		
		jp.setLayout(new GridLayout(5, 4));
		add(jp, BorderLayout.CENTER);
		
		
		del.addActionListener(this);
		del.setBackground(Color.GRAY);
		del.setForeground(Color.BLACK);
		del.setPreferredSize(new Dimension(30, 30));
		jp.add(del);
		
		JButton reset = new JButton("AC");
		reset.addActionListener(this);
		reset.setBackground(Color.GRAY);
		reset.setForeground(Color.BLACK);
		reset.setPreferredSize(new Dimension(30, 30));
		jp.add(reset);
		
		JButton resetCurrent = new JButton("C");
		resetCurrent.addActionListener(this);
		resetCurrent.setBackground(Color.GRAY);
		resetCurrent.setForeground(Color.BLACK);
		resetCurrent.setPreferredSize(new Dimension(30, 30));
		jp.add(resetCurrent);
		
		JButton pc = new JButton("%");
		pc.addActionListener(this);
		pc.setBackground(Color.GRAY);
		pc.setForeground(Color.BLACK);
		pc.setPreferredSize(new Dimension(30, 30));
		jp.add(pc);
		
		
		
		for(int i =7; i<= 9; i++) {
			JButton num = new JButton("" + i);
			num.addActionListener(this);
			num.setBackground(Color.GRAY);
			num.setPreferredSize(new Dimension(30, 30));
			num.setForeground(Color.WHITE);
			jp.add(num);
		}
		
		
		div.addActionListener(this);
		div.setBackground(Color.GRAY);
		div.setForeground(Color.BLACK);
		div.setPreferredSize(new Dimension(30, 30));
		jp.add(div);
		
		
		
		for(int i =4; i<= 6; i++) {
			JButton num = new JButton("" + i);
			num.addActionListener(this);
			num.setBackground(Color.GRAY);
			num.setForeground(Color.WHITE);
			num.setPreferredSize(new Dimension(30, 30));
			jp.add(num);
		}	
		
		
		mul.addActionListener(this);
		mul.setBackground(Color.GRAY);
		mul.setForeground(Color.BLACK);
		mul.setPreferredSize(new Dimension(30, 30));
		jp.add(mul);
		
		
		
		for(int i =1; i<= 3; i++) {
			JButton num = new JButton(Integer.toString(i));
			num.addActionListener(this);
			num.setBackground(Color.GRAY);
			num.setForeground(Color.WHITE);
			num.setPreferredSize(new Dimension(30, 30));
			jp.add(num);
		}	
		
		minus.addActionListener(this);
		minus.setBackground(Color.GRAY);
		minus.setForeground(Color.BLACK);
		minus.setPreferredSize(new Dimension(30, 30));
		jp.add(minus);
		
		JButton num = new JButton("" + 0);
		num.addActionListener(this);
		num.setBackground(Color.GRAY);
		num.setForeground(Color.WHITE);
		num.setPreferredSize(new Dimension(30, 30));
		jp.add(num);
		
		
	
		
		JButton point = new JButton(".");
		point.addActionListener(this);
		point.setBackground(Color.GRAY);
		point.setPreferredSize(new Dimension(30, 30));
		jp.add(point);
		
		
		plus.addActionListener(this);
		plus.setBackground(Color.GRAY);
		plus.setForeground(Color.BLACK);
		plus.setPreferredSize(new Dimension(30, 30));
		jp.add(plus);
		
		JButton result = new JButton("=");
		result.addActionListener(this);
		result.setBackground(Color.GRAY);
		result.setForeground(Color.BLACK);
		result.setPreferredSize(new Dimension(30, 30));
		jp.add(result);
		
		pack();
		setVisible(true);
		
		
		
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		new Calculator();
		
		

	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	
		// 숫자 버튼 0과 1을 따로 구분하였습니다. 
		// 1~9 버튼은 앞에 0이 있으면 앞에 0을 지우고 쓸 수 있게 만들었습니다. 
		
		if(e.getActionCommand().equals("1") || e.getActionCommand().equals("2") || e.getActionCommand().equals("3") || e.getActionCommand().equals("4") || e.getActionCommand().equals("5") || e.getActionCommand().equals("6") || e.getActionCommand().equals("7") || e.getActionCommand().equals("8") || e.getActionCommand().equals("9")) {
			
			if(tf.getText().equals("0")) {
				tf.setText("");
				String k = e.getActionCommand();
				String str = (tf.getText() + k);
				tf.setText(str);
				flag = true;
			}
			else {
			String k = e.getActionCommand();
			String str = (tf.getText() + k);
			tf.setText(str);
			flag = true;
			}
		}
		
		// 0 버튼은 아무것도 없는 경우에는 0 하나만 입력되고 0을 계속 입력하는 경우 0이 추가되는 것이 아니라 0하나만 뜨게 하였습니다 -> 아이폰 계산기와 최대한 비슷하게 구현하려고 하였습니다. 
		
		
		if(e.getActionCommand().equals("0")) {
			if(tf.getText().equals("0")) {
				tf.setText("0");
			}

			else {
				String str = (tf.getText() + "0");
				tf.setText(str);
				
			}
				
		}
		
		
		// -> 버튼은 string으로 숫자 및 연산자 버튼을 눌렀을 때 값을 입력받는데 숫자 값을 하나 지우고 싶을 때 이용하는 버튼 입니다.  
		
		if(e.getActionCommand().equals("->")) {
			String k1 = tf.getText();
			System.out.println(k1);
			k1 = k1.substring(0, k1.length()-1);
			tf.setText(k1);
			del.setBackground(Color.WHITE);
			mul.setBackground(Color.GRAY);
			div.setBackground(Color.GRAY);
			plus.setBackground(Color.GRAY);
			minus.setBackground(Color.GRAY);
		}
		
		
		// % 버튼을 구현하려고 하였지만 휴대폰의 % 버튼이 3+5 %10 일 경우  3.2가 나와야 정상이지만 0.15가 나와버려 이게 무슨 논리로 이렇게 작동되는지 잘 모르겠어서 구현을 하지 못하였습니다. 
		
		if(e.getActionCommand().equals("%")) {
			if(flag == true) {
			
			sentence += tf.getText();
			sentence += 'a';
			sentence += "%";
			
			tf.setText("");
			flag = false;}
			else {
				sentence = sentence.substring(0, sentence.length() - 2);	
				sentence += "a";
				sentence += "%";
			}
			flag4 = true;
		}
		
		
		// 곱하기 버튼 기능 
		// 연산자가 1개 일 경우 즉, flag 가 true 일 경우는 연산자와 연산자와 text를 구별할 수 있는 숫자와 같은 기능인 문자 a를 추가합니다. 
		// 아닐 경우 앞에서 적었던 연산자와 문자 a를 substring 하여 지우고 다시 a와 연산자를 넣습니다 -> ( 계산기에서 최종 연산자가 반영 되기 때문에 연산자가 연속으로 입력될 경우 계속하여 지우고 쓰고를 반복하였습니다. 
		if(e.getActionCommand().equals("*")) {
			if(flag == true) {
			
			sentence += tf.getText();
			sentence += "a";
			sentence += "*";
	
			tf.setText("");
			flag = false;
			}
			else {
				
				sentence = sentence.substring(0, sentence.length() - 2);	
				sentence += "a";
				sentence += "*";
			}
			flag4 = true;
			mul.setBackground(Color.WHITE);
			div.setBackground(Color.GRAY);
			plus.setBackground(Color.GRAY);
			minus.setBackground(Color.GRAY);
			del.setBackground(Color.GRAY);
			
		}
		
		// 곱하기와 유사합니다. 
		
		if(e.getActionCommand().equals("/")) {
			if(flag == true) {
				
			sentence += tf.getText();
			sentence += "a";
			sentence += "/";
		
			tf.setText("");
			flag = false;
		}else {
			
			
			sentence = sentence.substring(0, sentence.length() - 2);
			sentence += "a";
			sentence += "/";}
			flag4 = true;
			div.setBackground(Color.WHITE);
			mul.setBackground(Color.GRAY);
			plus.setBackground(Color.GRAY);
			minus.setBackground(Color.GRAY);
			del.setBackground(Color.GRAY);
			
		
			}
		
		
		// 곱하기 (*) 연산자와 유사합니다. 
		if(e.getActionCommand().equals("-")) {
			
			if(flag == true) {
				
			sentence += tf.getText();
			sentence += "a";
			sentence += "-";
		
			tf.setText("");
			flag = false;
		}else {

			sentence = sentence.substring(0, sentence.length() - 2);
			sentence += "a";
			sentence += "-";
			
		}
			minus.setBackground(Color.WHITE);
			mul.setBackground(Color.GRAY);
			div.setBackground(Color.GRAY);
			plus.setBackground(Color.GRAY);
			del.setBackground(Color.GRAY);
			
			flag4 = true;
			}
		
		// "*" 곱하기 연산자와 유사합니다. 
		if(e.getActionCommand().equals("+")) {
			if(flag == true) {
					
			sentence += tf.getText();
			sentence += 'a';
			sentence += "+";
			
			tf.setText("");
			flag = false;
			
		}else {

			sentence = sentence.substring(0, sentence.length() - 2);
			sentence += "a";
			sentence += "+";
			
			tf.setText("");

		}
			plus.setBackground(Color.WHITE);
			mul.setBackground(Color.GRAY);
			div.setBackground(Color.GRAY);
			minus.setBackground(Color.GRAY);
			del.setBackground(Color.GRAY);
			
			flag4 = true;
			
		}
		
		
		// .도 연속해서 찍으면 안되기 때문에 flag4 로 구분하였으며 +-*/ 연산자 이후의 숫자를 입력할 경우에는 소수점 입력이 가능해야 하므로 /*-+ 연산자 이후에는 flag4가 true가 되도록 하였습니다. 
		if(e.getActionCommand().equals(".")) {
			
		if(flag4 ==true) {
			tf.setText(tf.getText() + "." );
			flag4 = false; 
		}
		
		}
		
		// = 연산자 
		// 1. string으로 받은 숫자(소수점이 있을 수도 없을 수도 있다. )와 연산자 들을 infix에서 postfix로 바꾼다음
		// 숫자 및 연산자 기준으로 " " 한칸을 띄워서 이 한칸 기준으로 array로 바꾸어 stack에 넣고 계산을 하려고 했습니다. 
		// 소수점 구별이 어려웠는데 문자 a를 postfix를 바꿀 때 숫자처럼 참고하여 a를 " " 빈칸으로 바꿍 처리하고 
		// 연산자도 ! 연산자를 넣어가지고 하려고 했는데 우선순위 때문에 문제가 되어 고민하다가 -> 간단하게 연산자 뒤에 " " 빈칸을 붙이면 해결되어 이런 방식으로 해결 하였습니다. 
		// flag2 는 처음 postfix로 변환되었을때 null1.2 2.4 이런식으로 나와서 앞에 문자 null을 없애려고 하였습니다. 한번 postfix로 바꾼 뒤에는 flag2가 false가 되어 그냥 처리해도 가능했습니다. 
		// sentence5 는 postfix로 변환하고 숫자 문자 각각의 기준으로 나누어진 string array 입니다. 즉, 숫자 하나하나와 문자 하나씩 배열에 저장된 배열입니다. 
		// sentence5 하나씩 읽어와 연산자 이면 stack에서 2개의 원소를 pop 해와서 계산하고 다시 그 값을 stack 에 push 하였고, else 숫자인 경우 push 하였습니다. 
		// 나온 결과가 소수점이 있는지 없는지 구분하기 위해서 나온 string 값을 double로 바꾸고 다시 그 double 값을 int로 casting 하고 double - int(캐스팅 된 값) > 0이면 소수점이 있기 때문에 double로 출력 아닌 경우 int로 출력하였습니다. 
		
		if(e.getActionCommand().equals("=")) {
			
			sentence += tf.getText();
			sentence += "a";
			String sentence2 = "";
			sentence2 = infixToPostfix(sentence);
			sentence2 = sentence2.replace("a", " ");
			sentence2 = sentence2.replace("*", "* ");
			sentence2 = sentence2.replace("/", "/ ");
			sentence2 = sentence2.replace("+", "+ ");
			sentence2 = sentence2.replace("-", "- ");
			
			
			if(flag2 == true) {
			sentence2 = sentence2.substring(4);
			
			}
			
			
			
			sentence5 = changeArrayNum(sentence2);
			
			
			for(int k = 0; k< sentence5.length; k++) {
				String e1 = sentence5[k];
				System.out.println(e1);
			}
			
			
			for(int i =0; i< sentence5.length; i++) {
				String k = sentence5[i];
				
				
				
				if(k.equals("+") || k.equals("-") | k.equals("*") || k.equals("/")) {
					
					String num1, num2, stNum3 = "";
					num1 = stack.pop();
					num2 = stack.pop();
					
					double num3 = 0.0;
					double num2Change = Double.parseDouble(num2);
					double num1Change = Double.parseDouble(num1);
					
					if(k.equals("*")) {
						num3 = num2Change * num1Change;
						stNum3 = String.valueOf(num3);
						stack.push(stNum3);
						 
					}
					if(k.equals("+")) {
						num3 = num2Change + num1Change;
						stNum3 = String.valueOf(num3);
						stack.push(stNum3);
					}
					if(k.equals("-")) {
						num3 = num2Change - num1Change;
						System.out.println(num3);
						stNum3 = String.valueOf(num3);
						stack.push(stNum3);
					}
					if(k.equals("/")) {
						
						num3 = num2Change / num1Change;
						stNum3 = String.valueOf(num3);
						stack.push(stNum3);
					}
					
					
					
				}
				else {
					
					stack.push(k);
					
				}
				flag4 = true;
					
			}
			
			double total;
			String totalSt;
			totalSt = stack.pop();
			total = Double.parseDouble(totalSt);
			System.out.println(total);
			System.out.println(stack.empty());
			
			
			if(total - (int)total > 0) {
			
				tf.setText(totalSt);
				flag4 = false;
				
				
			}
			
			else if(total - (int)total == 0) {
				
				totalSt = String.valueOf((int)total);
				tf.setText(totalSt);
				
				
			}
			else {
				tf.setText(totalSt);
				flag4 = false;
			}
			
			flag = true;
			
			flag2 = true;
			
			
			System.out.println(sentence +" 문장입니다 ");
	
			sentence = "null";
	
	}
	// AC 전체 조기화인 경우 숫자와 연산자를 받는 sentence를 초기화 하고 TextField를 초기화 하였습니다. 또한 flag들을 처음과 같이 초기화 하였습니다.
		// flag2 는 왜 그런지는 잘 모르겠지만 이번엔 null이 들어가지 않아 false로 처리하였습니다. 
		
		if(e.getActionCommand().equals("AC")) {
			sentence = "null";
			sentence5 = new String[50];
			tf.setText("");
			System.out.println(sentence);
			flag = true;  // 연산자 중복 ex) */*/*/+-+-+ 하는 경우 마지막 연산자만 입력되게 하였습니다 -> 아이폰 계산기랑 비슷하게 나마 구현 
			flag2 = true; // infix string 을 postfix로 변환할 경우 처음 변환시에 생기는 null 값을 substring 해주는데 = 계산 이후에는 null 이 생기지 않아서 처음 값에서만 null값 없애주기 위한 기능입니다. 
			flag3 = true; // 
			flag4 = true; // 소수점 . 을 ..... 이런식으로 여러개 찍는걸 방지하기 위한 기능입니다. 하지만 연산자들이 계산 되고 난 다음에는 소수점이 또 하나만 찍힐 수 있으므로 연산자 기능 후 true 로 변환하였습니다. 
			flag5 = true;
			del.setBackground(Color.GRAY);
			mul.setBackground(Color.GRAY);
			div.setBackground(Color.GRAY);
			plus.setBackground(Color.GRAY);
			minus.setBackground(Color.GRAY);
			
		}
		// C인 경우 그 문장만 취소하는 기능이기 때문에 Text만 "" null 값으로 초기화 하였습니다. 
			
		if(e.getActionCommand().equals("C")) {
			tf.setText("");
		}
		}
			
		
		
			
		// 문자열과 숫자가 합쳐진 string을 바꿔보려고 만든 method이지만 소수점 때문에 결국 안쓰게 되었습니다. 
				
		
	
	public static String divideString(String str){
		String k = null;
		String totalString = null;
		for(int i =0; i< str.length(); i++) {
			char instance = str.charAt(i);
			if(instance == '*' || instance == '/' || instance == '+' || instance == '-'){
				
			}
			else {
				totalString += k + 'a';
				totalString += " alpha";
				totalString += instance;
				k = "";
				totalString += 'a';
			}
				
	}
		return totalString;
		}
	
	// 띄어쓰기 기준 배열로 바꾸어 주는 method 
	
	public static String [] changeArrayNum (String str) {
		String[] newArray = str.split(" ");
		return newArray;
	}
	
	
	// ! 기준으로 바꾸어 주는 method -> 원래 연산자들도 숫자처럼 하나 만들어가지고 그거 기준으로 바꾸려고 하였지만 +와 * 연산자의 우선순위가 있어서 결국은 연산자 자체를 
	// 연산자 + 공백으로 바꾸는 방향을 진행하였습니다  
	
	public static String [] changeArrayOperator (String str) {
		String[] newArray = str.split("!");
		return newArray;
	}
	
	
	
	// 이 밑에 부터는 infix를 postfix로 바꾸는 method 입니다. 구글링을 참고하였으며 각 코드를 하나하나 이해하려고 노력하였습니다. 
    public static int prec(char c)
    {
    	if (c == '!' ) {
            return 1;
        }
        // 곱셈과 나눗셈
        if (c == '*' || c == '/' ) {
            return 3;
        }
        
        if (c == '|') {
        	return 5;
        }
        // 덧셈과 뺄셈
        if (c == '+' || c == '-') {
            return 4;
        }
 
       
        
 
        // 필요한 경우 더 많은 연산자를 추가합니다.
 
        return Integer.MAX_VALUE;            // 여는 대괄호 '('
    }
 
    // 주어진 토큰이 피연산자인지 확인하는 유틸리티 함수
    public static boolean isOperand(char c)
    {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') ||
            (c >= '0' && c <= '9') || (c == '.');
    }
 
    // 중위 표현식을 후위 표현식으로 변환하는 함수.
    // 이 함수는 유효한 중위 표현식을 기대합니다
    public static String infixToPostfix(String infix){
        // 기본 케이스
        if (infix == null || infix.length() == 0) {
            return infix;
        }
 
        // 알고리즘자를 저장할 빈 Stack을 만듭니다.
        Stack<Character> s = new Stack<>();
 
        // 접미사 표현식을 저장할 문자열 생성
        String postfix = "";
 
        // 중위 표현식을 왼쪽에서 오른쪽으로 처리
        for (char c: infix.toCharArray())
        {
            // Case 1. 현재 토큰이 여는 괄호 '(',
            // Stack에 푸시
            if (c == '(') {
                s.add(c);
            }
            // Case 2. 현재 토큰이 닫는 괄호 ')'인 경우
            else if (c == ')')
            {
                // 해당 열릴 때까지 Stack에서 토큰을 팝합니다.
                // 대괄호 '('가 제거되었습니다. 끝에 각 연산자를 추가합니다.
                // 접미사 식
                while (s.peek() != '(') {
                    postfix += s.pop();
                }
                s.pop();
            }
 
            // Case 3. 현재 토큰이 피연산자인 경우 끝에 추가
            // 접미사 식
            else if (isOperand(c)) {
                postfix += c;
            }
 
            // Case 4. 현재 토큰이 연산자인 경우
            else {
                // 더 높거나 같은 우선 순위를 가진 Stack에서 알고리즘자를 제거합니다.
                // 접미사 표현식의 끝에 추가합니다.
                while (!s.isEmpty() && prec(c) >= prec(s.peek())) {
                    postfix += s.pop();
                }
 
                // 마지막으로 현재 알고리즘자를 Stack 맨 위에 푸시합니다.
                s.add(c);
            }
        }
 
        // 마지막에 Stack의 나머지 알고리즘자를 추가합니다.
        // 접미사 식
        while (!s.isEmpty()) {
            postfix += s.pop();
        }
 
        // 접미사 표현식을 반환
        return postfix;}
  
	
}
