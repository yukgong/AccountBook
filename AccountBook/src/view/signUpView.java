package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import dao.MemberDao;

public class signUpView extends JFrame implements ActionListener {
	private JTextField textField[];
	private JLabel title;
	private JLabel lable[];
	private JButton signUpBtn, checkId;

	public signUpView() {
		super("Sign up");

		setLayout(null);

//		JPanel panel = new JPanel();

		// Lable --------------------------------

		lable = new JLabel[4];

		title = new JLabel("Sign up");
		title.setBounds(20, 30, 260, 24);
		title.setHorizontalAlignment(JLabel.CENTER);
		Font f1 = new Font("SanSerif", Font.PLAIN, 20);
		title.setFont(f1);
		add(title);

		lable[0] = new JLabel("ID");
		lable[1] = new JLabel("PW");
		lable[2] = new JLabel("name");
		lable[3] = new JLabel("E-mail");

		for (int i = 0; i < lable.length; i++) {
			lable[i].setBackground(new Color(200, 200, 200));
			lable[i].setBounds(20, 65 + (65 * i), 100, 30);
			add(lable[i]);
		}

		// TextField ----------------------------

		textField = new JTextField[4];
		for (int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField(20);
			textField[i].setBounds(20, 95 + (65 * i), 260, 30);
			textField[0].setBounds(20, 95, 160, 30);
			add(textField[i]);
		}

		// Btn -----------------------------------

		signUpBtn = new JButton();

		signUpBtn = new JButton("Sign up");
		signUpBtn.addActionListener(this);
		signUpBtn.setBounds(20, 353, 260, 36);
		add(signUpBtn);

		checkId = new JButton("check");
		checkId.addActionListener(this);
		checkId.setBounds(196, 95, 84, 30);
		add(checkId);

		// Basic setting -------------------------
		setBounds(100, 100, 316, 470);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton btn = (JButton) e.getSource();
		String btnTitle = btn.getLabel();
		
		//id가 중복인지 확인하기--------------------------------------------
		if (btnTitle.equals("check")) {

			// id 확인
			MemberDao dao = MemberDao.getInstace();
			String id = textField[0].getText().trim();
			boolean b = dao.getId(id);

			if (b == true) {
				JOptionPane.showMessageDialog(null, "사용할 수 없는 ID입니다.");
				textField[0].setText("");
			} else {
				JOptionPane.showMessageDialog(null, "사용할 수 있는 ID입니다.");
			}
		}
		
		//id가 중복인지 확인하기--------------------------------------------
		if (btnTitle.equals("Sign up")) {

			// id 확인
			MemberDao dao = MemberDao.getInstace();
			String id = textField[0].getText().trim();
			String pwd = textField[1].getText().trim();
			String name = textField[2].getText().trim();
			String email = textField[3].getText().trim();
			
			
			boolean b = dao.addMember(id,pwd,name,email);

			if (b == true) {
				JOptionPane.showMessageDialog(null, "등록되었습니다.");
				dispose();
				new loginView();
			} else {
				JOptionPane.showMessageDialog(null, "등록을 실패했습니다.");
			}
		}
	}

}
