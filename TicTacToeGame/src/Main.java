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

public class Main extends JFrame{       			 //we will use JFrame

	int counter=0;									//counter used to decide which player has a turn and for setting a draw
	JButton[][] button = new JButton[3][3];			//tictactoe grid map
	
	public Main(){									//scene constructor
		
		
		//setup the window
		GridBagConstraints gbc = new GridBagConstraints();		
		setSize(400,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Tic Tac Toe by R.Cie�wierz");
		setLayout(new GridBagLayout());
		
		//Adding some labels and buttons
		JLabel lb = new JLabel("Player X starts ",SwingConstants.CENTER);
		lb.setFont(new Font("Arial", Font.PLAIN, 24));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth =3;
		gbc.fill = GridBagConstraints.BOTH;
		add(lb, gbc);
		JButton b = new JButton("PLAY AGAIN");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth =3;
		gbc.fill = GridBagConstraints.BOTH;
		b.setVisible(false);
		add(b, gbc);
		
		b.addActionListener(new ActionListener() {     //this will perform when you push the button - reseting the game
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new Main();
			}
		});
		
		
		//initializing the grid and placing buttons
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
						
						JButton button = (JButton) e.getSource();
						//Logic of the game
						if(counter % 2 == 0) 				//player X turn
						{
							button.setText("X");
							button.setBackground(Color.RED);
							button.setActionCommand("X");
							lb.setText("PLAYER O TURN");
							if(win("X"))
								{
									lb.setText("PLAYER X WINS!");
									disableAll();
									b.setVisible(true);
									counter=0;
								}
							
						}
						else								//player O turn
						{
							button.setText("O");
							button.setBackground(Color.BLUE);
							button.setActionCommand("O");
							lb.setText("PLAYER X TURN");
							if(win("O")) 
								{
									lb.setText("PLAYER O WINS!");
									disableAll();
									b.setVisible(true);
									counter=0;
								}
							
						}
						button.setEnabled(false);
						//game ends when limit of moves ends
						if (counter==8)
							{
								lb.setText("DRAW");
								b.setVisible(true);
							}
						else counter++;				
					}
				});
			}	
		}
	}
	//this function checks rows,colums and diagonals of the matrix and decide if someone has won
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

	//this function disables all the buttons on the grid
    public void disableAll() {
    	for(int i=0;i<3;i++)
    	{
    		for(int j=0;j<3;j++)
    		{
    			button[i][j].setEnabled(false);
    		}
    	}

    }


	
	
//main function just initialize the window
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run(){
				new Main();
			}
		});
	}

}
