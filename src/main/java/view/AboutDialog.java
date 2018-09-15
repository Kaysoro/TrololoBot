package view;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.net.URI;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AboutDialog extends JDialog {

	private Logger LOG = LoggerFactory.getLogger(AboutDialog.class);

	public AboutDialog() {
		super();
		setSize(new Dimension(230, 220));
		setLocationRelativeTo(null);
		setAlwaysOnTop(false);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("TrololoBot - About");
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JButton button = new JButton("       OK        ");
		button.addActionListener(event -> dispose());

		JPanel ok = new JPanel();
		ok.add(button);

		panel.add(new JLabel("TrololoBot - version " + Constants.version), BorderLayout.NORTH);

		JButton img = new JButton(new ImageIcon(getClass().getResource("/images/github.png")));
		img.setPreferredSize(new Dimension(105, 105));
		img.setFocusPainted(false);
		img.addActionListener(event -> {
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
				try {
					desktop.browse(new URI(Constants.git));
				} catch (Exception e1) {
					LOG.error(e1.getMessage());
				}
		});
		JPanel panelImg = new JPanel();
		panelImg.add(img);
		panel.add(panelImg, BorderLayout.CENTER);
		panel.add(ok, BorderLayout.SOUTH);
		getContentPane().add(panel);
	}
}