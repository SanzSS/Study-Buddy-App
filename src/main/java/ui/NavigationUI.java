package ui;

import notification.clear_notif.*;
import notification.show_notif.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationUI implements ActionListener, ViewModel{

    JPanel navCardPanel;
    JPanel swiperPanel = new JPanel();
    JPanel chatListPanel = new JPanel();
    ChatListUI chatListUI = new ChatListUI(chatListPanel);
    JPanel notifPanel = new JPanel();
    NotificationUI notificationUI = new NotificationUI(notifPanel);
    JPanel accountPanel = new JPanel();
    JPanel profilePanel = new JPanel();
    JButton swiperBtn = new JButton("Swiper");
    JButton chatBtn = new JButton("Chat");
    JButton notifBtn = new JButton("Notifications");
    JButton profileBtn = new JButton("Profile");
    JButton accountBtn = new JButton("Account");

    // controllers to call on use cases
    ShowNotifController showNotifController;
    ClearNotifController clearNotifController;

   public NavigationUI(){

   }

    public void addComponentPane(Container pane){
        navCardPanel = new JPanel(new CardLayout());
        JLabel swiperLabel = new JLabel("swiper");
        JLabel profileLabel = new JLabel("profile");
        JLabel accountLabel = new JLabel("account");
        profilePanel.add(profileLabel);
        swiperPanel.add(swiperLabel);
        accountPanel.add(accountLabel);

        navCardPanel.add(swiperPanel, "Swiper");
        navCardPanel.add(chatListUI.getPanel(), "Chat");
        navCardPanel.add(profilePanel, "Profile");
        navCardPanel.add(accountPanel, "Account");
        navCardPanel.add(notifPanel, "Notification");
        pane.add(navCardPanel);
    }

    @Override
    public void build(){
        JFrame frame = new JFrame("StudyBuddy");
        frame.setPreferredSize(new Dimension(1440, 1000));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NavigationUI nav = new NavigationUI();
        nav.addComponentPane(frame.getContentPane());
        nav.navCardPanel.setSize(1440,1080);
        nav.addActionEvent();
        //for menu bar
        JMenuBar mb = new JMenuBar();
        mb.add(nav.accountBtn);
        mb.add(nav.profileBtn);
        mb.add(nav.swiperBtn);
        mb.add(nav.chatBtn);
        mb.add(nav.notifBtn);
        frame.getContentPane().add(BorderLayout.NORTH, mb);

        frame.pack();
        frame.setVisible(true);
    }

    public void addActionEvent(){
        accountBtn.addActionListener(this);
        swiperBtn.addActionListener(this);
        notifBtn.addActionListener(this);
        profileBtn.addActionListener(this);
        chatBtn.addActionListener(this);
    }
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout)(navCardPanel.getLayout());
        if (e.getSource() == chatBtn) {
            chatListUI.build();
            cl.show(navCardPanel, "Chat");
        }
        else if (e.getSource() == swiperBtn) {
            cl.show(navCardPanel, "Swiper");
        }
        else if (e.getSource() == notifBtn) {
            cl.show(navCardPanel, "Notification");

            ShowNotifOutputBoundary showNotifPresenter=  new ShowNotifPresenter(notificationUI);
            ShowNotifInputBoundary showNotifInteractor = new ShowNotifInteractor(showNotifPresenter);
            ShowNotifController showNotifController = new ShowNotifController(showNotifInteractor);

            ClearNotifOutputBoundary clearNotifPresenter = new ClearNotifPresenter(notificationUI);
            ClearNotifInputBoundary clearNotifInteractor = new ClearNotifInteractor(clearNotifPresenter);
            ClearNotifController clearNotifController = new ClearNotifController(clearNotifInteractor);

            showNotifController.showNotif();
            notificationUI.build(clearNotifController);
        }
        else if (e.getSource() == profileBtn) {
            cl.show(navCardPanel, "Profile");
        }
        else if (e.getSource() == accountBtn) {
            cl.show(navCardPanel, "Account");
        }

    }
}
