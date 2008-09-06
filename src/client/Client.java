package client;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.ui.ConnectPanel;

import server.ui.ServerPanel;

public class Client extends JFrame {
	
	public static void main (String [] args) {
		new Client();
	}
	
	private Socket socket;
	private JPanel contentPane;
	private LocalListener listener;
	
	public Client () {
		connect("localhost");
	}
	
	private void BuildWindow () {
		setContentPane(contentPane = new ConnectPanel(listener));
		setPreferredSize(new Dimension(200, 200));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	private void connect (String url) {
		try {
			socket = new Socket(url, 1234);
			System.out.println("Client: Connected.");
		} catch (UnknownHostException e) {
			System.err.println("Client: Cannot find host.");
			JOptionPane.showMessageDialog(this, "Cannot find host.", "jbatt client", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class LocalListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Connect")) {
				if (contentPane instanceof ConnectPanel)
					connect(((ConnectPanel)contentPane).getHostname());
			} else if (e.getActionCommand().equals("Quit")) {
				System.exit(0);
			}
		}
	}
	
}
