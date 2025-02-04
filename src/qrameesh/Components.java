package qrameesh;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Components {
    
    static JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setBounds(x, y, width, height);
        return label;
    }
    
    static JTextField createTextField(String text, int x, int y, int width, int height) {
        JTextField label = new JTextField(text);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setBounds(x, y, width, height);
        return label;
    }
    
    static JPanel createPanel(int x, int y, int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.setBounds(x, y, width, height);
        return panel;
    }

    static JSpinner createSpinner(int x, int y, int width, int height) {
        SpinnerNumberModel modelSpinner = new SpinnerNumberModel(0, 0, 50, 1);
        JSpinner spinner = new JSpinner(modelSpinner);
        spinner.setBounds(x, y, width, height);
        return spinner;
    }

    static JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        return button;
    }
    
    static JTextArea createTextArea(String text){
        JTextArea txt = new JTextArea(text);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        txt.setFont(new Font("Serif", Font.PLAIN, 16));
        
        return txt;
    }
    
    static JScrollPane createScrollPane(JTextArea txt, int x, int y, int width, int height, boolean edit) {
        
        txt.setEditable(edit);
        JScrollPane sp = new JScrollPane(txt);
        sp.setBounds(x, y, width, height);
        return sp;
    }
    
    static JFileChooser createImageChooser(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("images", "jpg", "png" );
        chooser.setFileFilter(filter);
        
        return chooser;
    }
    
    
    public static void playSound(String soundFile) {
        try {
            File soundPath = new File(soundFile);
            if (soundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("Sound file not found");
            }
        } catch (Exception ex) {}
    }

    
}
