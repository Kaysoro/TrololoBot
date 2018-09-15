package view;

import controllers.AboutControl;
import controllers.CloseControl;
import controllers.SelectChannelControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import util.ClientConfig;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaysoro on 15/09/2018.
 */
public class Display extends JFrame {
    private final static Logger LOG = LoggerFactory.getLogger(Display.class);
    private static Display instance;
    private JTextPane chat;
    private JLabel title;
    private JComboBox<IChannel> channels;
    private PanelGuild panelGuild;
    private Map<Long, JButton> buttonsGuild;

    public static synchronized Display getInstance(){
        if (instance == null)
            instance = new Display();
        return instance;
    }
    private Display(){
        super("TrololoBot");
        buttonsGuild = new HashMap<>();

        // basic parameter window
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(800, 500));
        setResizable(true);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(this.getClass().getResource("/images/trolol.png")).getImage());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            LOG.warn("Display", "System Look and Feel not available");
        }

        // Menu
        JMenu menuFile = new JMenu("   File   ");
        JMenuItem quit =  new JMenuItem("Quit");
        quit.setAccelerator(KeyStroke.getKeyStroke("control Q"));
        quit.setToolTipText("Close the application");

        JMenu menuOther = new JMenu("   ?   ");

        JMenuItem about =  new JMenuItem("About");
        about.setAccelerator(KeyStroke.getKeyStroke("control A"));
        about.setToolTipText("Some informations about this application");

        menuFile.add(quit);
        menuOther.add(about);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuOther);

        setJMenuBar(menuBar);

        title = new JLabel("---", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setBackground(Color.RED);
        title.setOpaque(true);
        title.setFont(new Font("Courier", Font.BOLD,12));
        channels = new JComboBox<>();
        channels.addItemListener(new SelectChannelControl());
        panelGuild = new PanelGuild();
        for(IGuild guild : ClientConfig.DISCORD().getGuilds())
            addGuild(guild);

        JScrollPane scrollGuild = new JScrollPane(panelGuild);
        scrollGuild.setPreferredSize(new Dimension(280, 1));
        scrollGuild.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollGuild.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        chat = new JTextPane();

        JPanel workPanel = new JPanel(new BorderLayout());
        workPanel.add(new PanelAction(title, channels), BorderLayout.NORTH);
        workPanel.add(chat, BorderLayout.CENTER);
        workPanel.add(new PanelMessage(), BorderLayout.SOUTH);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollGuild, BorderLayout.WEST);
        getContentPane().add(workPanel, BorderLayout.CENTER);

        // Adding some controls
        CloseControl closeCtrl = new CloseControl(this);
        quit.addActionListener(closeCtrl);
        about.addActionListener(new AboutControl());
        this.addWindowListener(closeCtrl);
    }

    public synchronized JTextPane getChat(){
        return chat;
    }

    public synchronized Map<Long, JButton> getButtonsGuild(){
        return buttonsGuild;
    }

    public void addGuild(IGuild guild) {
        getButtonsGuild().put(guild.getLongID(), panelGuild.addGuild(guild, title, channels));
        panelGuild.revalidate();
    }

    public void removeGuild(IGuild guild) {
        if (title.getText().equals(guild.getName())){
            channels.removeAllItems();
            title.setBackground(Color.RED);
            title.setText("---");
            chat.setText("");
        }

        panelGuild.remove(getButtonsGuild().get(guild.getLongID()));
        getButtonsGuild().remove(guild.getLongID());
        panelGuild.revalidate();
        panelGuild.repaint();
    }
}
