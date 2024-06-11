import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class tictactoe {
    int boardWidth=600;
    int boardHeight=700;

    JFrame frame = new JFrame("tic-tac-toe");
    JLabel textLabal = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel bordPanel = new JPanel();
    JPanel buttonpanel = new JPanel();
    
    

    JButton[][] boarder = new JButton[3][3];
    String playerx ="X";
    String playero ="O";
    String currentPlayer = playerx;

    boolean gameover = false;
    int turns=0;

   


    tictactoe(){
        
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        
        textLabal.setBackground(new Color(20, 178, 113 ));
        textLabal.setForeground(Color.white);
        textLabal.setFont(new Font("Arial",Font.BOLD,50));
        textLabal.setHorizontalAlignment(JLabel.CENTER);
        textLabal.setText("Tic-Tac-Toc");
        textLabal.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabal);
        frame.add(textPanel, BorderLayout.NORTH);

        JButton restbutton = new JButton("Reset");
        restbutton.setBackground(Color.GRAY);
        restbutton.addActionListener(new ActionListener () {
           public void actionPerformed(ActionEvent e){
            resetGame();
           }
        });

        JButton EXITButton =new JButton("exit");
        EXITButton.setBackground(Color.red);
        
        EXITButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e){
             System.exit(0);
          }  
        });

        buttonpanel.add(restbutton);
        buttonpanel.add(EXITButton);
        frame.add(buttonpanel,BorderLayout.SOUTH);
        buttonpanel.setBackground(new Color(78,23,67));
        

        bordPanel.setLayout(new GridLayout(3,3));
        bordPanel.setBackground(Color.WHITE);
        frame.add(bordPanel);


        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
               JButton tile = new JButton();
               boarder[r][c]= tile;
               bordPanel.add(tile);

               tile.setBackground(new Color(10, 128, 113 ));
               tile.setBorder(new LineBorder(new Color(23,15,67),2));
            
               tile.setForeground(Color.white);
               tile.setFont(new Font("Arial",Font.BOLD,120));
               tile.setFocusable(false);
               //tile.setText(currentPlayer);

               tile.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    if(gameover) return;
                    JButton tile = (JButton)e.getSource();
                    if( tile.getText()==""){
                    tile.setText(currentPlayer);
                    turns++;
                    CheckWinner();
                    if(!gameover){
                        currentPlayer=currentPlayer==playerx ? playero :playerx;
                        textLabal.setText(currentPlayer+"'s turn.");   
                    }     
                    }
                  }
               });
            }
        }





    }

    
 void CheckWinner(){
    //HORIZONTAL
    for( int ro=0;ro<3;ro++){
        if(boarder[ro][0].getText() == "")continue;

        if(boarder[ro][0].getText() == boarder[ro][1].getText() &&
           boarder[ro][1].getText() == boarder[ro][2].getText()) {
            for(int i=0;i<3;i++){
                setWinner(boarder[ro][i]);
            }
            gameover=true;
            return;
           }
    }

    //vertical
    for(int c=0;c<3;c++) {
        if(boarder[0][c].getText() =="")continue;

        if(boarder[0][c].getText() == boarder[1][c].getText() &&
           boarder[1][c].getText() == boarder[2][c].getText() ) {
            for(int i=0; i< 3;i++){
                setWinner(boarder[i][c]);
            }
            gameover=true;
            return;
           }
    }
    //diagonally
    if(boarder[0][0].getText() == boarder[1][1].getText() &&
       boarder[1][1].getText() == boarder[2][2].getText() &&
       boarder[0][0].getText() != "") {
        for (int i=0;i<3;i++) {
              setWinner(boarder[i][i]);
        }
        gameover=true;
        return;
       } 
    //anti-diaganally
    if(boarder[0][2].getText() == boarder[1][1].getText()&&
       boarder[1][1].getText() == boarder[2][0].getText()&&
       boarder[0][2].getText() !="") {
        setWinner(boarder[0][2]);
        setWinner(boarder[1][1]);
        setWinner(boarder[2][0]);
        gameover=true;
        return;
       }

    //tie
    if (turns==9) {
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                SetTie(boarder[r][c]);
            }
        }
        gameover=true;
    }
    }
 void setWinner(JButton tile) {
    tile.setForeground(Color.green);
    tile.setBackground(Color.gray);
    textLabal.setText(currentPlayer+"is the winner!");
 }
 void SetTie(JButton tile){
    tile.setForeground(Color.RED);
    tile.setBackground(Color.GRAY);
    textLabal.setText("tie!");
 }
 void resetGame(){
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            boarder[i][j].setText("");
            boarder[i][j].setBackground(new Color(10, 128, 113 ));
            boarder[i][j].setForeground(Color.white);

        }
    }
    currentPlayer=playerx;
    gameover=false;
    turns=0;
    textLabal.setText("TIC-TAC-TOE");
 }
    
}
