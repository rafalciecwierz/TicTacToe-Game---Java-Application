import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main extends JFrame{

	int counter=0;
	JButton[][] button = new JButton[3][3];
	boolean flag=false;
	public Main(){
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		setSize(400,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Tic Tac Toe by R.Cie�wierz");
		setLayout(new GridBagLayout());
		
		JLabel lb = new JLabel("Rozpoczyna gracz X ",SwingConstants.CENTER);
		lb.setFont(new Font("Arial", Font.PLAIN, 24));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth =3;
		gbc.fill = GridBagConstraints.BOTH;
		add(lb, gbc);
		
		JButton b = new JButton("Rozpocznij ponownie");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth =3;
		gbc.fill = GridBagConstraints.BOTH;
		b.setVisible(false);
		add(b, gbc);
		
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new Main();
			}
		});
		
		
		
		for (int i=0;i<3;i++)
		{
			for (int j=0;j<3;j++)
			{
				button[i][j] = new JButton("");
				button[i][j].setPreferredSize(new Dimension(100,100));
				button[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
				gbc.gridwidth =1;
				gbc.gridx = i;
				gbc.gridy = j+1;
				add(button[i][j], gbc);
				button[i][j].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JButton button = (JButton) e.getSource();
						if(counter % 2 == 0)
						{
							button.setText("X");
							button.setBackground(Color.RED);
							button.setActionCommand("X");
							lb.setText("Tura gracza O ");
							if(win("X"))
								{
									lb.setText("WYGRA� GRACZ X ");
									disableAll();
									b.setVisible(true);
									counter=0;
								}
							
						}
						else
						{
							button.setText("O");
							button.setBackground(Color.BLUE);
							button.setActionCommand("O");
							lb.setText("Tura gracza X ");
							if(win("O")) 
								{
									lb.setText("WYGRA� GRACZ O ");
									disableAll();
									b.setVisible(true);
									counter=0;
								}
							
						}
						
						button.setEnabled(false);
						if (counter==8)
							{
								lb.setText("REMIS");
								b.setVisible(true);
							}
						else counter++;				
					}
				});
			}	
		}
	}
	
	public boolean win(String x)
	{
		for(int i=0;i<3;i++)
			{
				if(button[0][i].getActionCommand()==x&button[1][i].getActionCommand()==x&button[2][i].getActionCommand()==x)
					{
						return true;
						
					}
			}
		for(int i=0;i<3;i++) if(button[i][0].getActionCommand()==x&button[i][1].getActionCommand()==x&button[i][2].getActionCommand()==x) return true;
		if(button[0][0].getActionCommand()==x&button[1][1].getActionCommand()==x&button[2][2].getActionCommand()==x) return true;
		if(button[0][2].getActionCommand()==x&button[1][1].getActionCommand()==x&button[2][0].getActionCommand()==x) return true;
		else return false;
		
	}
	
    public void disableAll() {
    	for(int i=0;i<3;i++)
    	{
    		for(int j=0;j<3;j++)
    		{
    			button[i][j].setEnabled(false);
    		}
    	}

    }


	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run(){
				new Main();
			}
		});
	}

}
