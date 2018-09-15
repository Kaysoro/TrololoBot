package view;

import util.ClientConfig;

import javax.swing.*;
import java.awt.*;

public class UsernameDialog extends JDialog {

	public UsernameDialog() {
		super();
		setSize(new Dimension(300, 120));
		setLocationRelativeTo(null);
		setAlwaysOnTop(false);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("TrololoBot - Username");

		JTextField username = new JTextField(ClientConfig.DISCORD().getOurUser().getName());
		username.setPreferredSize(new Dimension(200, 25));

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JButton button = new JButton("       OK        ");
		button.addActionListener(event -> {
			if (! username.getText().isEmpty())
				ClientConfig.DISCORD().changeUsername(username.getText());
			dispose();
		});

		JPanel choices = new JPanel();
		choices.add(new JLabel("Username"));
		choices.add(username);

		JPanel ok = new JPanel();
		ok.add(button);

		panel.add(choices, BorderLayout.CENTER);
		panel.add(ok, BorderLayout.SOUTH);
		getContentPane().add(panel);
	}
}