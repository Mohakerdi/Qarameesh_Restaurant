package qrameesh;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomerPage extends JPanel implements ActionListener{
    
    JPanel overlay;
    Image backgroundImage;
    JButton orderButton = new JButton("Make Order");
    JButton notsButton = new JButton("Notifications");
    
    JLabel signoutLabel = new JLabel("Sign out");
    JLabel pageLabel  = new JLabel("Customer Page");
    Logo logo = new Logo(180, 145, "src/images/logo.png");
    
    CustomerPage(){
        
        backgroundImage = new ImageIcon("src\\images\\background.png").getImage();
        
        overlay = new JPanel();
        overlay.setBounds(25, 30, 450, 350);
        overlay.setLayout(null);
        overlay.setBackground(Color.getHSBColor(1, 1f, 0.4f));
        
        pageLabel.setBounds(120, 25, 280, 80);
        pageLabel.setFont(new Font("gabriola", Font.BOLD, 50));
        pageLabel.setForeground(Color.yellow);
        
        orderButton.setBounds(80,120,110,25);
        orderButton.setFocusable(false);
        orderButton.addActionListener(this);

        notsButton.setBounds(280,120,110,25);
        notsButton.setFocusable(false);
        notsButton.addActionListener(this);        
        
        logo.getLogoLabel().setBounds(160, 385, 180, 145);    

        signoutLabel.setBounds(195, 270, 75, 40);
        signoutLabel.setFont(new Font(null,Font.BOLD,18));
        signoutLabel.setForeground(Color.yellow);
        signoutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signoutLabel.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e){
                Card.card.show(Card.container, "loginPage");
            }
            @Override public void mouseEntered(MouseEvent e){}
            @Override public void mouseExited(MouseEvent e){}
        });
        
        
        overlay.add(signoutLabel);
        overlay.add(notsButton);
        overlay.add(orderButton);
        overlay.add(pageLabel);
        
        add(overlay);
        add(logo.getLogoLabel());
        
        setSize(500,700);
        
        setLayout(null);        

    }
    
    public void paint(Graphics g){
        super.paint(g);
        
        g.setColor(Color.yellow);
        g.drawLine(222, 328, 296, 328);
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == orderButton){
            Components.playSound("src/data/click.wav");
            Card.card.show(Card.container, "homePage");
        }
        else if(e.getSource() == notsButton){
            Components.playSound("src/data/click.wav");
            Card.card.show(Card.container, "notificationsPage");
        }
    }

}
