package it.unicam.mdp_mgc_2026.persistence.xml.dom.saporita;

import java.util.*;

public class Menu {
    private Map<String,Piatto> piatti;
    private MenuDelGiorno mdg;

    public Menu(){
        piatti = new HashMap<>();
    }

    public Menu(Map<String,Piatto> piatti){
        if (piatti == null) {
            piatti = new HashMap<>();
        }
        this.piatti = piatti;
    }

    public void setMenuDelGiorno(String data, Set<String> idPiatti){
        Date date = new Date(data);
        mdg = new MenuDelGiorno(date, idPiatti);
    }

    public Collection<Piatto> getPiatti() {
        return piatti.values();
    }

    public int addPiatto(Piatto p){
        if (piatti.values().contains(p)) {
            return -1;
        }
        piatti.put(p.getId(),p);
        return 1;
    }

    public void removePiatto(Piatto p){
        piatti.remove(p.getId());
    }

    public Set<Primo> getPrimi(){
        Set<Primo> primi = new HashSet<>();
        for (Piatto p : piatti.values()) {
            if (p instanceof Primo) {
                primi.add((Primo) p);
            }
        }
        return primi;
    }

    public Set<Secondo> getSecondi(){
        Set<Secondo> secondi = new HashSet<>();
        for (Piatto p : piatti.values()) {
            if (p instanceof Secondo){
                secondi.add((Secondo) p);
            }
        }
        return secondi;
    }

    public String toString(){
        String ret = "Menu:\n";
        for (Piatto p : piatti.values()) {
            ret += p.toString()+"\n";
        }
        return ret;
    }

    private class MenuDelGiorno{
        private Date data;
        private Set<String> piatti;

        public MenuDelGiorno(Date data, Set<String> piatti){
            this.data = data;
            this.piatti = piatti;
        }

        public Date getData() {
            return data;
        }

        public Set<String> getPiatti() {
            return piatti;
        }

    }
}
