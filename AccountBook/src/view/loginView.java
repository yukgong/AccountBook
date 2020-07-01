package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import dao.MemberDao;

public class loginView extends JFrame implements ActionListener {
	private JTextField textField[];
	private JLabel lable[];
	private JLabel title;
	private JButton btn[];

	public loginView() {
		super("Sign in");

		setLayout(null);

//		JPanel panel = new JPanel();

		// Lable --------------------------------

		lable = new JLabel[2];

		title = new JLabel("Sign in to Account Book!");
		title.setBounds(20, 30, 260, 24);
		title.setHorizontalAlignment(JLabel.CENTER);
		Font f1 = new Font("SanSerif", Font.PLAIN, 20);
		title.setFont(f1);
		add(title);

		lable[0] = new JLabel("ID");
		lable[1] = new JLabel("PW");

		for (int i = 0; i < lable.length; i++) {
			lable[i].setBackground(new Color(200, 200, 200));
			lable[i].setBounds(20, 65 + (65 * i), 20, 30);
			add(lable[i]);
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

		btn[0] = new JButton("Sign in");
		btn[1] = new JButton("Sign up");

		for (int i = 0; i < btn.length; i++) {
			btn[i].setBounds(20 + ((122 + 16) * i), 217, 122, 36);
			btn[i].addActionListener(this);
			add(btn[i]);
		}

		// Basic setting -------------------------
		setBackground(Color.white);
		setBounds(100, 100, 316, 320);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton btn = (JButton) e.getSource();
		String btnTitle = btn.getLabel();

		if (btnTitle.equals("Sign up")) {
			dispose();
			new signUpView();
		}
	}
}
