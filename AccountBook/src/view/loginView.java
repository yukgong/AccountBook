package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import dao.MemberDao;
import dto.MemberDto;

public class loginView extends JFrame implements ActionListener {
	private JTextField textField[];
	private JLabel label[];
	private JLabel title;
	private JButton btn[];

	public loginView() {
		super("Sign in");

		setLayout(null);

//		JPanel panel = new JPanel();

		// Label --------------------------------

		title = new JLabel("Sign in to Account Book!");
		title.setBounds(20, 30, 260, 24);
		title.setHorizontalAlignment(JLabel.CENTER);
		Font f1 = new Font("SanSerif", Font.PLAIN, 20);
		title.setFont(f1);
		add(title);
		
		label = new JLabel[2];

		String textLabel[] = {"ID", "PW"};

		for (int i = 0; i < label.length; i++) {
			label[i] = new JLabel(textLabel[i]);
			label[i].setBackground(new Color(200, 200, 200));
			label[i].setBounds(20, 65 + (65 * i), 20, 30);
			add(label[i]);
		}

		// TextField ----------------------------

		textField = new JTextField[2];
		for (int i = 0; i < textField.length; i++) {
			textField[i] = new JTextField(20);
			textField[i].setBounds(20, 95 + (65 * i), 260, 30);
			textField[0].setBounds(20, 95, 260, 30);
			add(textField[i]);
		}

		// Btn -----------------------------------

		btn = new JButton[2];

		String btnLabel[] = {"Sign in", "Sign up"};

		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton(btnLabel[i]);
			btn[i].setBounds(20 + ((122 + 16) * i), 217, 122, 36);
			btn[i].addActionListener(this);
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
		
		//Sign up --------------------------------------
		
		if (btnTitle.equals("Sign up")) {
			dispose();
			new signUpView();
		}
		
		//Sign in --------------------------------------
		
		if (btnTitle.equals("Sign in")) {
			
			MemberDao dao = MemberDao.getInstace();
			String id = textField[0].getText();
			String pwd = textField[1].getText();			
			
			MemberDto dto = dao.getIdAndPwd(id,pwd);

			if (dto == null) {
				JOptionPane.showMessageDialog(null, "존재하지 않는 회원정보입니다. \n ID와 비밀번호를 확인해주세요.");
			
			} else {
				JOptionPane.showMessageDialog(null, "반갑습니다. " + dto.getId() + "님");
				dao.setLoginID(dto.getId());
				dispose();
				new MenuView();
			}
			
		}
	}
}
