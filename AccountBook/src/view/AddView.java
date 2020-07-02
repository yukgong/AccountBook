package view;

import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dao.AccountBookDao;
import dao.MemberDao;
import dto.AccountBookDto;

public class AddView extends JFrame implements ActionListener {
	private JTextField textField[];
	private JLabel label[];
	private JLabel title;
	private JButton addBtn;
	JComboBox choice;

	public AddView() {
		super("Sign in");

		setLayout(null);

		// title --------------------------------
		title = new JLabel("Add");
		title.setBounds(20, 30, 260, 24);
		title.setHorizontalAlignment(JLabel.CENTER);
		Font f1 = new Font("SanSerif", Font.PLAIN, 20);
		title.setFont(f1);
		add(title);

		// Select Box ---------------------------
		choice = new JComboBox();
		String selBoxLabel[] = {"수입", "지출"};
		choice.setBounds(20, 73, 260, 30);
		for (int i = 0; i < selBoxLabel.length; i++) {
			choice.addItem(selBoxLabel[i]);
		}
		add(choice);

		// Label --------------------------------

		label = new JLabel[2];

		String textLabel[] = { "금액", "내용" };

		for (int i = 0; i < label.length; i++) {
			label[i] = new JLabel(textLabel[i]);
			label[i].setBackground(new Color(200, 200, 200));
			label[i].setBounds(20, 111 + (65 * i), 100, 30);
			add(label[i]);
		}

		// TextField ----------------------------

		textField = new JTextField[2];
		for (int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField(20);
			textField[i].setBounds(20, 141 + (65 * i), 260, 30);
			textField[0].setBounds(20, 141, 260, 30);
			add(textField[i]);
		}

		// Btn -----------------------------------

		addBtn = new JButton("추가하기");
		addBtn.setBounds(20, 263, 260, 36);
		addBtn.addActionListener(this);
		add(addBtn);

	// Basic setting -------------------------
		setBounds(100, 100, 316, 360);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		String btnTitle = btn.getLabel();

		// 뒤로가기
		if (btnTitle.equals("<")) {
			dispose();
			new MenuView();

		}
		
		// 뒤로가기
		if (btnTitle.equals("추가하기")) {
			
			// id
			String id = MemberDao.getInstace().getLoginID();
			
			// io_kind
			int index = choice.getSelectedIndex();
			String io_kind = "o";
			if(index == 0) { io_kind = "i"; }
			
			// amount
			int amount = Integer.parseInt(textField[0].getText());
			
			// content
			String content = textField[0].getText();
			
			// data insert
			AccountBookDao abo = AccountBookDao.getInstace();
			AccountBookDto dto = new AccountBookDto(0, id, io_kind, amount, content, "");
			boolean b = abo.insertData(dto);
			
			if(b) {
				JOptionPane.showMessageDialog(null, "정상적으로 추가되었습니다");
				
				// 초기화
				choice.setSelectedIndex(0);	
				for (int i = 0; i < textField.length; i++) {
					textField[i].setText("");
				}
			} else {
				JOptionPane.showMessageDialog(null, "추가되지 않았습니다.");
			}

		}

	}

}
