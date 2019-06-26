

package jogodavelha;


public class JogoDaVelha {
    
/**
 *
 * @author Pedro
 */
   
    public static void main(String[] args) {
        
        
        Jogo p = new Jogo();
        Interf interf = null;
        interf = interf.getInstance();
        interf.recebe(p);
        interf.setVisible(true);
        
        
    }
    
}
