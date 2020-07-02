package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class MenuView extends JFrame implements ActionListener  {
	private JLabel title;
	private JButton btn[];
	
	
	public MenuView(){
		super("Menu");

		setLayout(null);
		
		// Lable --------------------------------
		
		title = new JLabel("Hello");
		title.setBounds(20, 30, 260, 24);
		title.setHorizontalAlignment(JLabel.CENTER);
		Font f1 = new Font("SanSerif", Font.PLAIN, 20);
		title.setFont(f1);
		add(title);

		// Btn -----------------------------------

		btn = new JButton[3];
		
		String label[] = {"입/출 내역 추가하기", "기간별 검색하기", "항목별 검색하기"};
	
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton(label[i]);
			btn[i].addActionListener(this);
			btn[i].setBounds(50, 93 + (52 * i), 200, 36);
			add(btn[i]);
		}

		// Basic setting -------------------------
		setBounds(100, 100, 316, 320);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		String btnTitle = btn.getLabel();
		
		if (btnTitle.equals("입/출 내역 추가하기")) {
				dispose();
				new AddView();

		}
		
		if (btnTitle.equals("기간별 검색하기")) {
				dispose();
				new FilterByPeriodView();

		}
		
		if (btnTitle.equals("항목별 검색하기")) {
				dispose();
				new FilterByTextView();

		}
		
	}
	
}
