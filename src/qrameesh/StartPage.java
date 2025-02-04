package qrameesh;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPage extends JPanel {
    
    JLabel welcomeLabel;
    JLabel loadingLabel;
    Logo logo;
    Loading ls = new Loading(1000);
    
    StartPage(){
    welcomeLabel = new JLabel("Welcome");
    loadingLabel = new JLabel("Loading...");
    
    logo = new Logo(260, 220, "src/images/logo.png");
    
    
    add(welcomeLabel);
    add(loadingLabel);
    add(logo.getLogoLabel());
                
    welcomeLabel.setBounds(158, 430, 300, 90);
    loadingLabel.setBounds(190, 480, 300, 90);
    logo.getLogoLabel().setBounds(120, 150, 260, 220);
    
    welcomeLabel.setFont(new Font("gabriola", Font.BOLD, 60));
    loadingLabel.setFont(new Font("gabriola", Font.BOLD, 30));
    welcomeLabel.setForeground(Color.yellow);
    loadingLabel.setForeground(Color.yellow);
    
    loadingLabel.setVisible(false);
    
    setBackground(Color.getHSBColor(1, 1f, 0.4f));
    
    setSize(500, 700);
    setLayout(null);
    
    addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                loadingLabel.setVisible(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ls.run(); 
                Card.card.show(Card.container, "loginPage");
            
            }
                
            @Override
            public void mouseEntered(MouseEvent e) {}
                
            @Override
            public void mouseExited(MouseEvent e) {}
        
        });

    
    }
    
}
