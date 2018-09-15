package view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.util.DiscordException;
import util.ClientConfig;

import javax.swing.*;
import java.awt.*;

public class UsernameDialog extends JDialog {

	private final static Logger LOG = LoggerFactory.getLogger(UsernameDialog.class);

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
			if (! username.getText().isEmpty() && ! username.getText().equals(ClientConfig.DISCORD().getOurUser().getName()))
				try {
					ClientConfig.DISCORD().changeUsername(username.getText());
				} catch (DiscordException e){
					LOG.warn("UsernameDialog", e);
					JOptionPane.showMessageDialog(this, e.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
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