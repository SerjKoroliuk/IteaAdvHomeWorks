package pudge;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Pudge extends JFrame {

	private JPanel contentPane;
	private JLabel phrase1;
	private JLabel phrase2;
	private JLabel phrase3;
	private Locale currentLocale;
	private ResourceBundle messages;
	private File cache = new File("cache.txt");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pudge frame = new Pudge();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public Pudge() {
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(900, 100, 740, 827);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel pudgeImage = new JLabel("");
		pudgeImage.setBounds(57, 44, 316, 429);
		pudgeImage.setIcon(new ImageIcon("img\\pudge.jpg"));
		pudgeImage.setLabelFor(this);
		contentPane.add(pudgeImage);

		phrase1 = new JLabel();
		phrase1.setBounds(57, 528, 576, 43);
		phrase1.setForeground(Color.RED);
		phrase1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		contentPane.add(phrase1);

		phrase2 = new JLabel();
		phrase2.setForeground(Color.RED);
		phrase2.setBounds(57, 609, 576, 43);
		phrase2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		contentPane.add(phrase2);

		phrase3 = new JLabel();
		phrase3.setForeground(Color.RED);
		phrase3.setBounds(57, 684, 576, 43);
		phrase3.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		contentPane.add(phrase3);

		JButton btnRus = new JButton("\u0420\u0443\u0446\u043A\u0438\u0439");
		btnRus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentLocale = new Locale("ru", "RU");
				update();
			}
		});
		btnRus.setBounds(438, 44, 195, 49);
		btnRus.setBackground(Color.LIGHT_GRAY);
		btnRus.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(btnRus);

		JButton btnEn = new JButton("English");
		btnEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentLocale = new Locale("en", "EN");
				update();

			}
		});
		btnEn.setBounds(438, 136, 195, 49);
		btnEn.setBackground(Color.LIGHT_GRAY);
		btnEn.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(btnEn);

		JButton btnDe = new JButton("Deutsche");
		btnDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentLocale = new Locale("de", "DE");
				update();
			}
		});
		btnDe.setBounds(438, 231, 195, 49);
		btnDe.setBackground(Color.LIGHT_GRAY);
		btnDe.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(btnDe);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentLocale != null) {
					try (FileWriter fw = new FileWriter(cache)) {
						fw.write(currentLocale.toString());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnSave.setBounds(524, 361, 109, 43);
		btnSave.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
		btnSave.setBackground(Color.LIGHT_GRAY);
		contentPane.add(btnSave);

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try (Scanner sc = new Scanner(cache)) {
					if (sc.hasNextLine()) {
						currentLocale = new Locale(sc.nextLine());
						update();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLoad.setBounds(524, 435, 109, 38);
		btnLoad.setBackground(Color.LIGHT_GRAY);
		btnLoad.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
		contentPane.add(btnLoad);

	}

	private void update() {
		messages = ResourceBundle.getBundle("localis", currentLocale);
		phrase1.setText(messages.getString("phrase1"));
		phrase2.setText(messages.getString("phrase2"));
		phrase3.setText(messages.getString("phrase3"));

	}
}
