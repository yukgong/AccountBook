package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import dao.AccountBookDao;
import dao.MemberDao;
import dto.AccountBookDto;
import dto.MemberDto;

public class FilterByPeriodView extends JFrame implements ActionListener {
	private JTextField textField[];
	private JLabel label[];
	private JLabel title;
	private JButton resultBtn, preBtn;
//	private JComboBox choice[];
	JTextArea textArea;
	
	public FilterByPeriodView() {
		super("기간별 내역 검색");
		setLayout(null);

		// title -------------------------------
		title = new JLabel("기간별 내역 검색");
		title.setBounds(20, 30, 260, 24);
		title.setHorizontalAlignment(JLabel.CENTER);
		Font f1 = new Font("SanSerif", Font.PLAIN, 20);
		title.setFont(f1);
		add(title);


		// Label --------------------------------

		label = new JLabel[3];
		
		String textLabel[] = {"시작일", "종료일", "검색 결과"};

		for (int i = 0; i < label.length; i++) {
			label[i] = new JLabel(textLabel[i]);
			label[i].setBounds(20, 65 + (65 * i), 100, 30);
			add(label[i]);
		}
		
		label[2].setBounds(20, 265, 100, 30);

		// TextField ----------------------------

		textField = new JTextField[2];
		for (int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField(20);
			textField[i].setBounds(20, 95 + (65 * i), 260, 30);
			add(textField[i]);
		}
		
		// TextArea ------------------------------
		textArea = new JTextArea();
		textArea.setBounds(20, 294, 260, 150);
		Border border = BorderFactory.createLineBorder(Color.DARK_GRAY);
		textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		add(textArea);
		

		// Btn -----------------------------------

		resultBtn = new JButton("결과 보기");
		resultBtn.addActionListener(this);
		resultBtn.setBounds(20, 205, 260, 36);
		add(resultBtn);

		preBtn = new JButton("<");
		preBtn.addActionListener(this);
		preBtn.setBounds(0, 0, 50, 30);
		add(preBtn);

		// Basic setting -------------------------
		setBounds(100, 100, 316, 504);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		String btnTitle = btn.getLabel();
		
		//뒤로 가기 --------------------------------------
		
		if (btnTitle.equals("<")) {
			dispose();
			new MenuView();
		}
		
		//결과 보기 --------------------------------------
		
		if (btnTitle.equals("결과 보기")) {
			
			// 필터에 사용될 변수
			String id = MemberDao.getInstace().getLoginID();
			String startDay = textField[0].getText();
			String endDay = textField[1].getText();		

			AccountBookDao dao = AccountBookDao.getInstace();
			List<AccountBookDto> list = dao.FilterByPeriod(id, startDay, endDay);
			
			if (list.size() != 0) {
				for (AccountBookDto a : list) {
					
					String io;
					if(a.getIo_kind().equals("i")) { io = "수입";}
					else {io = "지출";}
					
					textArea.append("[" + a.getWdate() + "] " 
										+ io + " " 
										+ a.getAmount() + "원\t"
										+ a.getContent() +"\n" );
				}

			} else {
				JOptionPane.showMessageDialog(null, "해당 기간에 데이터가 존재하지 않습니다.");
				for (int i = 0; i < textField.length; i++) {
					textField[i].setText("");
				}
			}
			
		}
		
		
	}
	
}
