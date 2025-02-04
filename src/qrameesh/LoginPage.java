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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends JPanel implements ActionListener {
    
    JPanel overlay;
    Image backgroundImage;
    JButton nextbButton = new JButton("next");
    JButton resetbButton = new JButton("reset");
    JTextField nameFiled = new JTextField();
    JPasswordField passwordFiled = new JPasswordField();
    
    JLabel nameLabel = new JLabel("name :");
    JLabel PasswordLabel  = new JLabel("Password :");
    JLabel signinLabel = new JLabel("Sign in");
    JLabel pageLabel  = new JLabel("Login Page");
    Logo logo = new Logo(180, 145, "src/images/logo.png");
    
    LoginPage(){
        
        backgroundImage = new ImageIcon("src\\images\\background.png").getImage();
        
        overlay = new JPanel();
        overlay.setBounds(25, 30, 450, 250);
        overlay.setLayout(null);
        overlay.setBackground(Color.getHSBColor(1, 1f, 0.4f));
        
        pageLabel.setBounds(150, 25, 250, 80);
        pageLabel.setFont(new Font("gabriola", Font.BOLD, 50));
        pageLabel.setForeground(Color.yellow);
        
        nameLabel.setBounds(50,100,250,25);
        PasswordLabel.setBounds(50,150,250,25);
        
        nameLabel.setForeground(Color.yellow);
        PasswordLabel.setForeground(Color.yellow);
        
        nameFiled.setBounds(175,100,200,25);
        passwordFiled.setBounds(175,150,200,25);
        
        nextbButton.setBounds(125,600,75,25);
        nextbButton.setFocusable(false);
        nextbButton.addActionListener(this);

        resetbButton.setBounds(275,600,75,25);
        resetbButton.setFocusable(false);
        resetbButton.addActionListener(this);        
        
        logo.getLogoLabel().setBounds(160, 385, 180, 145);    

        signinLabel.setBounds(208, 550, 75, 40);
        signinLabel.setFont(new Font(null,Font.BOLD,18));
        signinLabel.setForeground(Color.yellow);
        signinLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signinLabel.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e){
                Card.card.show(Card.container, "signinPage");
            }
            @Override public void mouseEntered(MouseEvent e){}
            @Override public void mouseExited(MouseEvent e){}
        });
        
        overlay.add(pageLabel);
        overlay.add(nameFiled);
        overlay.add(nameLabel);
        overlay.add(passwordFiled);
        overlay.add(PasswordLabel);
        add(overlay);
        add(nextbButton);
        add(resetbButton);
        add(signinLabel);
        add(logo.getLogoLabel());
        
        
        setSize(500,700);
        
        setLayout(null);        

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resetbButton){
            Components.playSound("src/data/click.wav");
            reset();
        }

        if(e.getSource()==nextbButton){
            Components.playSound("src/data/click.wav");
            String firstName = nameFiled.getText();
            String password = new String(passwordFiled.getPassword());
            
            if(!(firstName.equals(""))){
                if(Checker.check_Password(password)){
                    passwordErr();
                }else{
                     if(Main.searchUsers(firstName, password)){
                         if(Main.CurrentUser.isCellEditable()){
                             Card.card.show(Card.container, "adminPage");
                             reset();
                         }else{
                             Card.card.show(Card.container, "customerPage");
                             reset();
                         }
                         
                     }
                     else{
                         showErr();
                     }
                }
            }
            else{
                showErr();
            }
        }
    }
    
    public void paint(Graphics g){
        super.paint(g);
        
        g.setColor(Color.yellow);
        g.drawLine(208, 578, 270, 578);
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private void showErr() {
        JOptionPane.showMessageDialog(null, "Invalid name or password", "Warning Message", JOptionPane.WARNING_MESSAGE);
    }
    
    private void passwordErr() {
        JOptionPane.showMessageDialog(null, "password length must be 8 chars at least and 16 at most", "Warning Message", JOptionPane.WARNING_MESSAGE);
    }
    
    public void reset(){
        nameFiled.setText("");
        passwordFiled.setText("");
    }
}
