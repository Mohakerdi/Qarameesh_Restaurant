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
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MealsPage extends JPanel implements ActionListener{
    
    Set set;
    Iterator it;

    DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    
    JButton button_Add;
    JTable table;
    
    JLabel backLabel;
    JScrollPane scroll;
    private JDialog dialog;
    private JDialog addDialog;
    Image backgroundImage;
    Logo logo = new Logo(120, 100, "src/images/logo.png");
    
    String[] column = {"Meal", "Price", ""};
    Object[][] row;
    
    String path="";
    
    MealsPage(){
        
        updateItems();
        
        backLabel = new JLabel("Back");
        
        backgroundImage = new ImageIcon("src\\images\\background.png").getImage();
        
        setLayout(null);
        
        model.setDataVector(row, column);
        table = new JTable(model){
            @Override public Class<?> getColumnClass(int column) {
                if (column == 2) {
                    return ImageIcon.class;
                }
                return super.getColumnClass(column);
            }
        };
        
        scroll = new JScrollPane(table);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(75);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.setRowHeight(100);
        table.setFont(new Font("Arial", Font.BOLD, 15));
        scroll.setBounds(30, 50, 440, 450);
        
        backLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        backLabel.setBounds(400, 600, 100, 40);
        backLabel.setForeground(Color.red);
        
        table.setBackground(Color.getHSBColor(1, 1f, 0.4f));
        table.setForeground(Color.yellow);
        table.setGridColor(Color.getHSBColor(1, 1f, 0.4f));
        table.setShowGrid(false);
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false); 
        }
        
        button_Add = new JButton("Add Meal");
        button_Add.setBounds(30, 550, 120, 40);
        button_Add.setBackground(Color.getHSBColor(0.2f, 0.6f, 1f));
        
        logo.getLogoLabel().setBounds(160, 550, 120, 95); 
      
        add(button_Add);
        add(backLabel);
        add(scroll);
        add(logo.getLogoLabel());
        
        createDialog();
        button_Add.addActionListener(this);
        
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e){
                Card.card.show(Card.container, "adminPage");
            }
            @Override public void mouseEntered(MouseEvent e){}
            @Override public void mouseExited(MouseEvent e){}
        });
    }

    public final void createDialog() {// THIS DIALOG IS MADE TO EDIT MEALS 
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    int column = table.columnAtPoint(e.getPoint());
                    if (row >= 0 && column >= 0) {
                        showDialog(row);
                    }
                }
            }
        });
    }

    private void showDialog(int rowIndex) {
        dialog = new JDialog();
        dialog.setLocation(300, 50);
        dialog.setSize(400, 500);
        dialog.setLayout(null);
        
        Map.Entry mEntry = (Map.Entry) it.next();
        for(int i=0; i<rowIndex; i++){
            mEntry = (Map.Entry) it.next();
        }
        Meal m =(Meal) mEntry.getValue();
        String key = (String) mEntry.getKey();
        
        it = set.iterator();// resetting it 
        
        JPanel panel = Components.createPanel(0, 0, 400, 500);
        panel.setBackground(Color.getHSBColor(0.2f, 0.2f, 1f));
        JLabel labelPrice = Components.createLabel("Total Price:", 20, 80, 150, 30);
        JLabel labelDuration = Components.createLabel("Total Duration:", 20, 120, 150, 30);
        JTextArea recipee = Components.createTextArea(m.getRecipee());
        JScrollPane recipe = Components.createScrollPane(recipee, 40, 155, 300, 250, true);
        JTextField PriceValue = Components.createTextField(String.valueOf(m.getPrice()), 150, 80, 150, 30);
        JTextField DurationValue = Components.createTextField(String.valueOf(m.getPrepareTime()), 150, 120, 150, 30);
        JButton buttonConfirmDialog = Components.createButton("Confirm", 280, 425, 90, 30);
        JButton buttonsetImage = Components.createButton("set Image", 150, 425, 90, 30);
        JButton buttondeleteMeal = Components.createButton("Delete", 10, 425, 90, 30);
        JFileChooser Imagechoose = Components.createImageChooser();

        buttondeleteMeal.setForeground(Color.red);
        
        panel.add(labelPrice);
        panel.add(recipe);
        panel.add(PriceValue);
        panel.add(DurationValue);
        panel.add(labelDuration);
        panel.add(buttonConfirmDialog);
        panel.add(buttonsetImage);
        panel.add(buttondeleteMeal);
        dialog.add(panel);
        

        dialog.setVisible(true);
        
        buttonsetImage.addActionListener(e -> setImage(Imagechoose));
        buttonConfirmDialog.addActionListener(e -> editMeal(key, m, PriceValue, DurationValue, recipee, path));
        buttondeleteMeal.addActionListener(e -> deleteMeal(key, m));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== button_Add){
            Components.playSound("src/data/click.wav");
            createAddDialog();
        }
    }

    private void createAddDialog() {//  THIS DIALOG IS MADE TO ADD NEW MEAL
        addDialog = new JDialog();
        addDialog.setLocation(300, 50);
        addDialog.setSize(400, 500);
        addDialog.setLayout(null);
        JPanel panel = Components.createPanel(0, 0, 400, 500);
        panel.setBackground(Color.getHSBColor(0.2f, 0.2f, 1f));
        JLabel labelName = Components.createLabel("Meal Name:", 20, 40, 150, 30);
        JLabel labelPrice = Components.createLabel("Total Price:", 20, 80, 150, 30);
        JLabel labelDuration = Components.createLabel("Total Duration:", 20, 120, 150, 30);
        JTextArea recipee = Components.createTextArea("");
        JScrollPane recipe = Components.createScrollPane(recipee, 40, 155, 300, 250, true);
        JTextField nameValue = Components.createTextField("", 150, 40, 150, 30);
        JTextField PriceValue = Components.createTextField("", 150, 80, 150, 30);
        JTextField DurationValue = Components.createTextField("", 150, 120, 150, 30);
        JButton buttonConfirmDialog = Components.createButton("Confirm", 280, 425, 100, 30);
        JButton buttonsetImage = Components.createButton("set Image", 80, 425, 100, 30);
        JFileChooser Imagechoose = Components.createImageChooser();
        
        panel.add(labelPrice);
        panel.add(labelName);
        panel.add(recipe);
        panel.add(PriceValue);
        panel.add(DurationValue);
        panel.add(nameValue);
        panel.add(labelDuration);
        panel.add(buttonConfirmDialog);
        panel.add(buttonsetImage);
        addDialog.add(panel);
        
        addDialog.setVisible(true);
        
        buttonsetImage.addActionListener(e -> setImage(Imagechoose));
        buttonConfirmDialog.addActionListener(e -> addNewMeal(PriceValue, DurationValue, nameValue, recipee, path));
    }

    private void addNewMeal(JTextField PriceValue, JTextField DurationValue , JTextField nameValue, JTextArea recipee, String s) {
        try{
            Components.playSound("src/data/click.wav");
            double price = Double.parseDouble(PriceValue.getText());
            int duration = Integer.parseInt(DurationValue.getText());
            String name = nameValue.getText();
            String recipe = recipee.getText();
            String path = s;
            
            Meal m = new Meal(name, recipe, price, duration, path);
            Main.addMeal(m);
            SerializationUtil.saveToFile("src/data/meals-log.ser", Main.meals);
            System.out.println(path);
            updateItems();
            this.path = "";
            addDialog.setVisible(false);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "you shouldn't leave any field empty\n and enter only numbers in price and duration", "Warning Message", JOptionPane.WARNING_MESSAGE);
        }
        
    }

    private void editMeal(String key, Meal m, JTextField PriceValue, JTextField DurationValue , JTextArea recipee, String s) {
        try{
            Components.playSound("src/data/click.wav");
            double price = Double.parseDouble(PriceValue.getText());
            int duration = Integer.parseInt(DurationValue.getText());
            String recipe = recipee.getText();
            String path = (this.path==""? m.getImagePath() : s);
            
            m.setPrice(price);
            m.setPrepareTime(duration);
            m.setRecipee(recipe);
            m.setImagePath(path);
            
            Main.meals.replace(key, m);
            System.out.println("Replaced!!");
            this.path = "";
            SerializationUtil.saveToFile("src/data/meals-log.ser", Main.meals);
            updateItems();
            dialog.setVisible(false);
             
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "you shouldn't leave any field empty\n and enter only numbers in price and duration", "Warning Message", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    void deleteMeal(String key, Meal m){
        Components.playSound("src/data/click.wav");
        int x = JOptionPane.showConfirmDialog(null, "Delete meal?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (x == 0) {
            Components.playSound("src/data/click.wav");
            Main.meals.remove(key);
            SerializationUtil.saveToFile("src/data/meals-log.ser", Main.meals);
            dialog.setVisible(false);
            updateItems();
        } else {}
    }

    private void setImage(JFileChooser Imagechoose) {
        Components.playSound("src/data/click.wav");
        Imagechoose.setCurrentDirectory(new File("C:\\Users\\User\\Documents\\NetBeansProjects\\Qarameesh\\src\\images"));
        int returnedValue = Imagechoose.showOpenDialog(this);
         if(returnedValue == JFileChooser.APPROVE_OPTION){
                    
            File selectedFile = Imagechoose.getSelectedFile();
            String name = selectedFile.getName();
            
            String filepath = "src/images/"+name;

            path = filepath;
            return;
        }
         path = ""; 
    }
    
    public void updateItems() {
        Main.meals = SerializationUtil.loadFromFile("src/data/meals-log.ser");
        row = new Object[Main.meals.size()][3];
        int i=0;
        
        set = Main.meals.entrySet();
        it = set.iterator();
        while (it.hasNext()) {
            Map.Entry mEntry = (Map.Entry) it.next();
            Meal m = (Meal) mEntry.getValue();
            row[i][0] = m.getName();
            row[i][1] = String.valueOf(m.getPrice());
            row[i][2] = new Logo(90, 90, m.getImagePath()).getScaledIcon();
            i++;
        }
        model.setDataVector(row, column);
        it=set.iterator();// RE ASSIGN TO FIRST
    }
    
}