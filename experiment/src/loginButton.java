import javax.swing.*;
import java.awt.*;

public class loginButton extends JFrame{

  static JButton pixelLogin;

    public void loginButton(){

      ImageIcon iconPixel = new   ImageIcon("https://replit.com/@ShruthiSenthil1/Hackathon-Project#Resources/LoginButtonJPG.jpg");    
      
      pixelLogin = new JButton(iconPixel);

      setLayout(new FlowLayout()); 
      add(pixelLogin);
      
      }

    public static void main(String[] args){

      loginButton jbtn = new loginButton();
      jbtn.setSize(300,300);
      jbtn.setVisible(true);
      jbtn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
    }
    
  }

