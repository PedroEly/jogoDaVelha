
package jogodavelha;

import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class Tabuleiro{

    private String[][] tab = new String[3][3];
    private String jogador;
    private int valor = 0;

    private Tabuleiro raiz;
    private ArrayList<Tabuleiro> nohs = new ArrayList<Tabuleiro>();


    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Tabuleiro getRaiz() {
        return raiz;
    }

    public void setRaiz(Tabuleiro raiz) {
        this.raiz = raiz;
    }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jog) {
        this.jogador = jog;
    }

    public String getXY(int i, int j) {
        return tab[i][j];
    }

    public String[][] getTab() {
        return tab;
    }

    public void setX(int i, int j) {
        tab[i][j] = "X";
    }

    public void setO(int i, int j) {
        tab[i][j] = "O";
    }
    
    public void reinicia() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tab[i][j] = null;
         
                }
            }
    }
    
    public boolean ganhou(String x) {
        if(tab[0][0] == x && tab[0][1] == x && tab[0][2] == x) return true;
       else if(tab[1][0] == x && tab[1][1] == x && tab[1][2] == x) return true;
       else if(tab[2][0] == x && tab[2][1] == x && tab[2][2] == x) return true;
       else if(tab[0][0] == x && tab[1][0] == x && tab[2][0] == x) return true;
       else if(tab[0][1] == x && tab[1][1] == x && tab[2][1] == x) return true;
       else if(tab[0][2] == x && tab[1][2] == x && tab[2][2] == x) return true;
       else if(tab[0][0] == x && tab[1][1] == x && tab[2][2] == x) return true;
       else if(tab[0][2] == x && tab[1][1] == x && tab[2][0] == x) return true;
       else return false;
    }

    public boolean empatou() {
        boolean empate = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tab[i][j] == null) {
                    empate = false;
                    break;
                }
            }
        }

        return empate && !ganhou("X") && !ganhou("O");
    }

    

    public boolean terminou() {
        return ganhou("O") || empatou() || ganhou("X");
    }

    public int getResultado() {
        if (ganhou("O")) {
            valor=1;
            return 1;
        } else {
            if (ganhou("X")) {
                valor=-1;
                return -1;
            } else {
                return 0;
            }
        }
    }

   

    public ArrayList<Tabuleiro> getTodosNohs(){
        return nohs;
    }
    public ArrayList<Tabuleiro> getNohs(Tabuleiro t) {
        nohs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    Tabuleiro noh = t.clone();
                    noh.setRaiz(t);
                    if (noh.getXY(i, j) == null) {
                        if (jogador.equals("Max")) {
                            noh.setO(i, j);
                        } else {
                            noh.setX(i, j);
                        }
        
                        noh.getResultado();
                        nohs.add(noh);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        return nohs;
    }

    @Override
    protected Tabuleiro clone() throws CloneNotSupportedException {
        Tabuleiro t = new Tabuleiro();
        for (int i = 0; i < 3; i++) {
            System.arraycopy(tab[i], 0, t.getTab()[i], 0, 3);
        }
        return t;
    }

   

}
