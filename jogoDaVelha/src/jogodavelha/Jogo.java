
package jogodavelha;


import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Pedro
 */
public class Jogo{

    
   
    private Tabuleiro tab = new Tabuleiro();
    public Interf interf;
    
    public Jogo(){
        
        interf = interf.getInstance();
        
        tab.setJogador("Min"); 
        
       
    }


    private Tabuleiro minimax(Tabuleiro tab) {
        int melhor = MaxValue(tab);
        ArrayList<Tabuleiro> nohs = tab.getTodosNohs();
        for (Tabuleiro noh : nohs) {
            
            if (noh.getValor() == melhor) {
                return noh;
            }
        }
        return null;
    }

    private int MinValue(Tabuleiro tab) {
        if (tab.terminou()) {
            tab.setValor(tab.getResultado());
            return tab.getValor();
        } else {
            tab.setValor(Integer.MAX_VALUE);
            tab.setJogador("Min");
            ArrayList<Tabuleiro> nohs = tab.getNohs(tab);
            for (Tabuleiro noh : nohs) {
                tab.setValor(Math.min(tab.getValor(), MaxValue(noh)));
            }
            return tab.getValor();
        }
    }

    private int MaxValue(Tabuleiro tab) {
        if (tab.terminou()) {
            tab.setValor(tab.getResultado());
            return tab.getValor();
        } else {
            tab.setValor(Integer.MIN_VALUE);
            tab.setJogador("Max");
            ArrayList<Tabuleiro> nohs = tab.getNohs(tab);
            for (Tabuleiro noh : nohs) {
                tab.setValor(Math.max(tab.getValor(), MinValue(noh)));
            }
            return tab.getValor();
        }
    }

    public void jogada(int i, int j) {
        
            String x = tab.getXY(i, j);
            
             if (x == null){
            
                tab.setX(i, j);
                tab.setJogador("Max");
                tab = minimax(tab);
                
                imprime(tab);
                tab.setJogador("Min");
               
                if (tab.terminou()) {
                    if(tab.ganhou("X")) JOptionPane.showMessageDialog(null, "VOCÊ GANHOU!!!");
                    else if(tab.ganhou("O")) JOptionPane.showMessageDialog(null, "VITÓRIA DO COMPUTADOR!!!");
                    else JOptionPane.showMessageDialog(null, "EMPATOU AMIGO");
                    
                    reiniciar(tab);
                }
            } 
        
    }

    public void imprime(Tabuleiro tab) {
        
        interf.setVisible(true);
        
        interf.jogou(tab.getXY(0,0), 0);
        interf.jogou(tab.getXY(0,1), 1);
        interf.jogou(tab.getXY(0,2), 2);
        interf.jogou(tab.getXY(1,0), 3);
        interf.jogou(tab.getXY(1,1), 4);
        interf.jogou(tab.getXY(1,2), 5);
        interf.jogou(tab.getXY(2,0), 6);
        interf.jogou(tab.getXY(2,1), 7);
        interf.jogou(tab.getXY(2,2), 8);

       
        
    }
    
    public void reiniciar(Tabuleiro tab){
    
        tab.reinicia();
        imprime(tab);
    
    
    }
}
