package view;

import sx.blah.discord.handle.obj.ActivityType;
import sx.blah.discord.handle.obj.StatusType;
import util.ClientConfig;

import javax.swing.*;
import java.awt.*;

public class DispatcherDialog extends JDialog {

	public DispatcherDialog() {
		super();
		setSize(new Dimension(300, 180));
		setLocationRelativeTo(null);
		setAlwaysOnTop(false);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("TrololoBot - Dispatcher");

		JComboBox<StatusType> statusTypeJComboBox = new JComboBox<>();
		statusTypeJComboBox.setPreferredSize(new Dimension(200, 25));
		for(StatusType statusType : StatusType.values())
			statusTypeJComboBox.addItem(statusType);

		JComboBox<ActivityType> activityTypeJComboBox = new JComboBox<>();
		activityTypeJComboBox.setPreferredSize(new Dimension(200, 25));
		for(ActivityType activityType : ActivityType.values())
			activityTypeJComboBox.addItem(activityType);

		JTextField text = new JTextField();
		text.setPreferredSize(new Dimension(200, 25));

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JButton button = new JButton("       OK        ");
		button.addActionListener(event -> {
			ClientConfig.DISCORD().changePresence((StatusType) statusTypeJComboBox.getSelectedItem(),
					(ActivityType) activityTypeJComboBox.getSelectedItem(), text.getText());
			dispose();
		});

		JPanel choices = new JPanel();
		choices.add(new JLabel("Status type"));
		choices.add(statusTypeJComboBox);
		choices.add(new JLabel("Activity type"));
		choices.add(activityTypeJComboBox);
		choices.add(new JLabel("Text"));
		choices.add(text);

		JPanel ok = new JPanel();
		ok.add(button);

		panel.add(choices, BorderLayout.CENTER);
		panel.add(ok, BorderLayout.SOUTH);
		getContentPane().add(panel);
	}
}