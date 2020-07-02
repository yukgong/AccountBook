package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import dao.AccountBookDao;
import dao.MemberDao;
import dto.AccountBookDto;

public class FilterByTextView extends JFrame implements ActionListener {
	private JTextField textField;
	private JLabel title, label;
	private JButton resultBtn, preBtn;
	private JTextArea textArea;

	public FilterByTextView() {
		super("항목별 검색하기");
		setLayout(null);

		// title -------------------------------
		title = new JLabel("항목별 검색하기");
		title.setBounds(20, 30, 300, 24);
		title.setHorizontalAlignment(JLabel.CENTER);
		Font f1 = new Font("SanSerif", Font.PLAIN, 20);
		title.setFont(f1);
		add(title);

		// Label --------------------------------

		label = new JLabel("검색 결과");
		label.setBounds(20, 127, 100, 20);
		add(label);

		// TextField ----------------------------

		textField = new JTextField(20);
		textField.setBounds(20, 73, 208, 30);
		add(textField);

		// TextArea ------------------------------
		textArea = new JTextArea();
		textArea.setBounds(20, 151, 300, 150);
		Border border = BorderFactory.createLineBorder(Color.DARK_GRAY);
		textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		add(textArea); 

		// Btn -----------------------------------

		resultBtn = new JButton("검색");
		resultBtn.addActionListener(this);
		resultBtn.setBounds(236, 73, 84, 30);
		add(resultBtn);

		preBtn = new JButton("<");
		preBtn.addActionListener(this);
		preBtn.setBounds(0, 0, 50, 30);
		add(preBtn);

		// Basic setting -------------------------
		setBounds(100, 100, 356, 360);
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
		
		if (btnTitle.equals("검색")) {
			
			// 필터에 사용될 변수
			String id = MemberDao.getInstace().getLoginID();
			String content = textField.getText();	

			AccountBookDao dao = AccountBookDao.getInstace();
			List<AccountBookDto> list = dao.FilterByText(id, content);
			
			if (list.size() > 0 && !content.equals("")) {
				for (AccountBookDto a : list) {
					
					String io;
					if(a.getIo_kind().equals("i")) { io = "수입";}
					else {io = "지출";}
					
					textArea.append("[" + a.getWdate() + "] " 
										+ io + " " 
										+ a.getAmount() + "원\t"
										+ a.getContent() +"\n" );
				}
				list = null;
				
				textField.setText("");

			} else {
				JOptionPane.showMessageDialog(null, "\'" + content + "\'에 대한 검색 결과가 없습니다.");
					textField.setText("");
				}
			}
	}
}
