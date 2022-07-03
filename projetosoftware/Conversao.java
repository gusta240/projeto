/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosoftware;

// import javax.swing.text.MaskFormatter;

/**
 *
 * @author dlnotari
 */
public class Conversao {
   /**
     * converte valor do dia para interiro
     * @param valor
     * @return 
     */
    public static int converteStringToInteiro(String valor) {
        // variavel local
        int retorno = 0;
        
        // testa se esta vazio
        if (!valor.isEmpty()) {
            retorno = Integer.parseInt(valor);
        }          
        
        // retorna valor
        return retorno;
    }    
}
