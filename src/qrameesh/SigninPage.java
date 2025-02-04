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

public class SigninPage extends JPanel implements ActionListener {
    
    JPanel overlay;
    Image backgroundImage;
    JButton nextbButton = new JButton("next");
    JButton resetbButton = new JButton("reset");
    JTextField nameFiled = new JTextField();
    JPasswordField passwordFiled = new JPasswordField();
    JLabel nameLabel = new JLabel("name :");
    JLabel PasswordLabel  = new JLabel("Password :");
    JTextField addressFiled = new JTextField();
    JTextField emailFiled = new JTextField();
    JLabel addressLabel = new JLabel("address :");
    JLabel emailLabel  = new JLabel("email :");
    JLabel pageLabel  = new JLabel("Signin Page");
    JLabel loginLabel  = new JLabel("already have an account?");
    JLabel message = new JLabel();
    Logo logo = new Logo(180, 145, "src/images/logo.png");
    
    SigninPage(){
        
        overlay = new JPanel();
        overlay.setBounds(25, 30, 450, 360);
        overlay.setLayout(null);
        overlay.setBackground(Color.getHSBColor(1, 1f, 0.4f));
        
        backgroundImage = new ImageIcon("src\\images\\background.png").getImage();
        
        pageLabel.setBounds(150, 25, 250, 80);
        pageLabel.setFont(new Font("gabriola", Font.BOLD, 50));
        pageLabel.setForeground(Color.yellow);
        
        nameLabel.setBounds(50,100,250,25);
        PasswordLabel.setBounds(50,150,250,25);
        addressLabel.setBounds(50,200,250,25);
        emailLabel.setBounds(50,250,250,25);

        nameFiled.setBounds(175,100,200,25);
        passwordFiled.setBounds(175,150,200,25);
        addressFiled.setBounds(175,200,200,25);
        emailFiled.setBounds(175,250,200,25);
        
        nameLabel.setForeground(Color.yellow);
        PasswordLabel.setForeground(Color.yellow);
        addressLabel.setForeground(Color.yellow);
        emailLabel.setForeground(Color.yellow);

        nextbButton.setBounds(125,600,75,25);
        nextbButton.setFocusable(false);
        nextbButton.addActionListener(this);

        message.setBounds(80,320,400, 40);
        message.setFont(new Font(null,Font.ITALIC,25));

        resetbButton.setBounds(275,600,75,25);
        resetbButton.setFocusable(false);
        resetbButton.addActionListener(this);
        
        logo.getLogoLabel().setBounds(160, 385, 180, 145);
        
        loginLabel.setBounds(170, 550, 190, 40);
        loginLabel.setFont(new Font(null,Font.BOLD,14));
        loginLabel.setForeground(Color.yellow);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e){
                Card.card.show(Card.container, "loginPage");
            }
            @Override public void mouseEntered(MouseEvent e){}
            @Override public void mouseExited(MouseEvent e){}
        });
    

        overlay.add(pageLabel);
        overlay.add(nameFiled);
        overlay.add(nameLabel);
        overlay.add(passwordFiled);
        overlay.add(PasswordLabel);
        overlay.add(addressFiled);
        overlay.add(addressLabel);
        overlay.add(emailFiled);
        overlay.add(emailLabel);
        overlay.add(message);
        add(overlay);
        add(nextbButton);
        add(resetbButton);
        add(loginLabel);
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
            checkInput();
        }
    }
    
    public void paint(Graphics g){
        super.paint(g);
        
        g.setColor(Color.yellow);
        g.drawLine(170, 578, 350, 578);
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    private void showErr() {
        JOptionPane.showMessageDialog(null, "Invalid name or password(dont leave any field empty)", "Warning Message", JOptionPane.WARNING_MESSAGE);
    }    
    private void completed() {
        JOptionPane.showMessageDialog(null, "New Account Created!", "Info Message", JOptionPane.INFORMATION_MESSAGE);
    }    
    private void passwordErr() {
        JOptionPane.showMessageDialog(null, "password length must be 8 chars at least and 16 at most", "Warning Message", JOptionPane.WARNING_MESSAGE);
    }
    private void checkInput() {
        String firstName = nameFiled.getText();
        String email = emailFiled.getText();
        String address = addressFiled.getText();
        String password = new String(passwordFiled.getPassword());

        if(!(firstName.equals("") || email.equals("") || address.equals(""))){
            if(Checker.check_Password(password)){
                passwordErr();
            }else{
                completed();
                Customer c = new Customer(firstName, password, email, address);
                Main.addUser(c);
                Card.card.show(Card.container, "customerPage");
                reset();
            }
        }
        else{
            showErr();
        }
     }
    public void reset(){
        nameFiled.setText("");
        passwordFiled.setText("");
        emailFiled.setText("");
        addressFiled.setText("");
    }
    
}
