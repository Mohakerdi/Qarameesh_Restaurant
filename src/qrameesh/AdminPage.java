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

public class AdminPage extends JPanel implements ActionListener{
    
    JPanel overlay;
    Image backgroundImage;
    JButton mealsButton = new JButton("Show Meals");
    JButton logButton = new JButton("Show Orders");
    
    JLabel signoutLabel = new JLabel("Sign out");
    JLabel pageLabel  = new JLabel("Admin Page");
    Logo logo = new Logo(180, 145, "src/images/logo.png");
    
    AdminPage(){
        
        backgroundImage = new ImageIcon("src\\images\\background.png").getImage();
        
        overlay = new JPanel();
        overlay.setBounds(25, 30, 450, 350);
        overlay.setLayout(null);
        overlay.setBackground(Color.getHSBColor(1, 1f, 0.4f));
        
        pageLabel.setBounds(120, 25, 280, 80);
        pageLabel.setFont(new Font("gabriola", Font.BOLD, 50));
        pageLabel.setForeground(Color.yellow);
        
        mealsButton.setBounds(80,120,110,25);
        mealsButton.setFocusable(false);
        mealsButton.addActionListener(this);

        logButton.setBounds(280,120,110,25);
        logButton.setFocusable(false);
        logButton.addActionListener(this);        
        
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
        overlay.add(logButton);
        overlay.add(mealsButton);
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
        
        if(e.getSource() == mealsButton){
            Components.playSound("src/data/click.wav");
            Card.card.show(Card.container, "mealsPage");
        }
        
        else if(e.getSource() == logButton){
            Components.playSound("src/data/click.wav");
            Card.card.show(Card.container, "logPage");
        }
    
    }
    
}