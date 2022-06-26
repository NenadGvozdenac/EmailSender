import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {
	
	public static JFrame prozor;
	
	public static String email_adresa = "testingjavaemailsending@gmail.com";
	public static String sifra_email = "lpkgfajmqkqlquvw";
	
	public static void main(String[] args) {
		prozor = new JFrame();
		prozor.setTitle("Email Sender");
		prozor.setBounds(new Rectangle(800, 600));
		prozor.setLocationRelativeTo(null);
		
		prozor.setResizable(false);
		prozor.getContentPane().setLayout(new BoxLayout(prozor.getContentPane(), BoxLayout.Y_AXIS));
		
		JTextArea tekst1 = new JTextArea("Email primaoca");
		tekst1.setFont(new Font("Serif", Font.BOLD, 20));
		tekst1.setPreferredSize(new Dimension(600, 20));
		tekst1.setLineWrap(true);
		tekst1.setWrapStyleWord(true);
		
		prozor.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		JTextArea tekst2 = new JTextArea("Naslov Email-a");
		tekst2.setFont(new Font("Serif", Font.BOLD, 20));
		tekst2.setPreferredSize(new Dimension(600, 20));
		tekst2.setLineWrap(true);
		tekst2.setWrapStyleWord(true);
		
		JTextArea tekst3 = new JTextArea("Email");
		tekst3.setFont(new Font("Serif", Font.BOLD, 20));
		tekst3.setPreferredSize(new Dimension(600, 400));
		tekst3.setLineWrap(true);
		tekst3.setWrapStyleWord(true);
		
		
		tekst1.setBorder(BorderFactory.createEtchedBorder());
		tekst2.setBorder(BorderFactory.createEtchedBorder());
		tekst3.setBorder(BorderFactory.createEtchedBorder());
		
		JButton button = new JButton("Pošalji Email");
		button.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
		button.setPreferredSize(new Dimension(150, 50));
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		dodaj_mouse_listener(tekst1);
		dodaj_mouse_listener(tekst2);
		dodaj_mouse_listener(tekst3);
		
		prozor.getContentPane().add(tekst1);
		prozor.getContentPane().add(tekst2);
		prozor.getContentPane().add(tekst3);
		prozor.getContentPane().add(button);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean greska = false;
				if(tekst1.getText().equals("")) {
					tekst1.setForeground(Color.red);
					tekst1.setText("UNESITE PODATAK! UNESITE VALIDNU EMAIL ADRESU!");
					greska = true;
				}
				
				if(tekst2.getText().equals("")) {
					tekst2.setForeground(Color.red);
					tekst2.setText("UNESITE PODATAK! UNESITE VALIDAN NASLOV!");
					greska = true;
				}
				
				if(tekst3.getText().equals("")) {
					tekst3.setForeground(Color.red);
					tekst3.setText("UNESITE PODATAK! UNESITE VALIDAN EMAIL!");
					greska = true;
				}
				
				if(greska) {
					return;
				}
			
				POSALJI_EMAIL(tekst1.getText(), tekst2.getText(), tekst3.getText());

			}
		});
		
		prozor.setVisible(true);
		prozor.setAlwaysOnTop(true);
		prozor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	static void POSALJI_EMAIL(String primaoc, String naslov, String content) {
		System.out.println("Email podaci: \n");
		System.out.println("Primaoc: " + primaoc + "\nNaslov Emaila: " + naslov + "\nEmail: " + content);
		
		String to = primaoc, from = email_adresa, sifra = sifra_email;
		
	     //Get the session object  
		
		String host = "smtp.gmail.com";
		
		Properties properties = System.getProperties();
	    properties.put("mail.smtp.host", host);
	    properties.put("mail.smtp.port", "465");
	    properties.put("mail.smtp.ssl.enable", "true");
	    properties.put("mail.smtp.auth", "true");
	    
	    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    	    return new PasswordAuthentication(from, sifra);
	    	}
	    });
	  
	    System.out.println("'" + from + "' -> '" + sifra + "'\n");
	    
	     //compose the message  
	    try{  
	       MimeMessage message = new MimeMessage(session);  
	       message.setFrom(new InternetAddress(from));  
	       message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	       message.setSubject(naslov);  
	       message.setText(content);  
	  
	         // Send message  
	       Transport.send(message);  
	       System.out.println("Poruka poslata uspesno....");  
	       
	       JOptionPane.showMessageDialog(prozor, "Uspesno poslat email!", "USPEH", JOptionPane.OK_OPTION | JOptionPane.INFORMATION_MESSAGE);
	       System.exit(0);
	       
	      } catch (MessagingException mex) {
	    	  mex.printStackTrace();
	    	  JOptionPane.showMessageDialog(prozor, "Greska pri slanju!", "GRESKA", JOptionPane.OK_OPTION | JOptionPane.WARNING_MESSAGE);
	    	  System.exit(-1);
	      }  
	}

	public static void dodaj_mouse_listener(JTextArea tekst) {
		tekst.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				tekst.setText("");
				tekst.setFont(new Font("Serif", Font.BOLD, 20));
				tekst.setForeground(Color.black);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				tekst.setText("");
				tekst.setFont(new Font("Serif", Font.BOLD, 20));
				tekst.setForeground(Color.black);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
}
