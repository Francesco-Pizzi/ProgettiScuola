package it.unicam.mdp_mgc_2026.palla8;

import java.util.ArrayList;
import java.util.Arrays;

public class Palla8 {

//    public Palla8(ArrayList<String> risp){
//
//    }

    private final ArrayList<String> risposte = new ArrayList<>(Arrays.asList(
            "È certo", "È decisamente così", "Senza alcun dubbio", "Sì, sicuramente",
            "Ci puoi contare", "Per come la vedo io, sì", "È molto probabile",
            "Le prospettive sono buone", "Sì", "I segni indicano di sì",
            "Risposta nebulosa, riprova", "Riprova più tardi", "Meglio non dirtelo ora",
            "Non posso predirlo ora", "Concentrati e rifai la domanda", "È un segreto",
            "Chiedimelo di nuovo", "Le nuvole oscurano la vista", "Non ne sono sicuro",
            "Non ci contare", "La mia risposta è no", "Le mie fonti dicono di no",
            "Le prospettive non sono buone", "Molto incerto", "Assolutamente no",
            "Dimenticalo", "Non sperarci", "Le stelle dicono di no", "Difficile", "Poche possibilità"
    ));

    public String getRisposta(){
        return risposte.get((int) (Math.random()*risposte.size()));
    }

    /**
     *
     *
     * @param s
     * @return
     */
    public int addRisposta(String s){
        if(risposte.contains(s)){
            return -1;
        }
        risposte.add(s);
        return 0;
    }

    public int removeRisposta(int idx){
        if(idx<0 || idx>risposte.size()){
            return -1;
        }
        risposte.remove(idx);
        return 0;
    }

    public int removeRisposta(String risp){
        boolean ok = risposte.remove(risp);
        if(ok){
            return 0;
        }else{
            return -1;
        }
    }
}
