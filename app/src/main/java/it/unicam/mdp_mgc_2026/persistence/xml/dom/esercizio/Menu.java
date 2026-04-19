package it.unicam.mdp_mgc_2026.persistence.xml.dom.esercizio;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private String nome;
    private List<Piatto> piatti;
    private List<Bevanda> bevande;
    private List<Vino> vini;

    public Menu(String nome) {
        this.nome = nome;
        this.piatti = new ArrayList<>();
        this.bevande = new ArrayList<>();
        this.vini = new ArrayList<>();
    }

    public Menu(String nome, List<Piatto> piatti,  List<Bevanda> bevande, List<Vino> vini) {
        this.nome = nome;
        this.piatti = piatti;
        this.bevande = bevande;
        this.vini = vini;
    }

    public String getNome() { return this.nome; }
    public List<Piatto> getPiatti() { return this.piatti; }
    public List<Bevanda> getBevande() { return this.bevande; }
    public List<Vino> getVini() { return this.vini; }

    public void aggiungiPiatto(Piatto piatto) { this.piatti.add(piatto);}
    public void aggiungiBevanda(Bevanda bevanda) { this.bevande.add(bevanda);}
    public void aggiungiVino(Vino vino) { this.vini.add(vino);}

    public String stampaPerCategoria(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n=== ").append(nome).append(" ===\n\n");

        stampaCategoriaPiatto(sb, Piatto.Categoria.ANTIPASTO, "Antipasti: ");
        stampaCategoriaPiatto(sb, Piatto.Categoria.PRIMO, "Primi: ");
        stampaCategoriaPiatto(sb, Piatto.Categoria.SECONDO, "Secondi: ");
        stampaCategoriaPiatto(sb, Piatto.Categoria.DOLCE, "Dolci: ");

        stampaTipoBevanda(sb, Bevanda.TipoBevanda.ACQUA, "Acque: ");
        stampaTipoBevanda(sb, Bevanda.TipoBevanda.BIBITA, "Bibite: ");
        stampaTipoBevanda(sb, Bevanda.TipoBevanda.BIRRA, "Birre: ");
        stampaTipoBevanda(sb, Bevanda.TipoBevanda.VINO, "Vini: ");

        stampaColoreVino(sb, Vino.ColoreVino.ROSSO, "Rossi");
        stampaColoreVino(sb, Vino.ColoreVino.BIANCO, "Bianchi");
        stampaColoreVino(sb, Vino.ColoreVino.SPUMANTE, "Spumanti");

        return sb.toString();
    }

    private void stampaCategoriaPiatto(StringBuilder sb, Piatto.Categoria categoria, String titolo) {
        boolean categoriaTrovata = false;

        for (Piatto p : piatti) {
            if (p.getCategoria() == categoria) {
                if (!categoriaTrovata) {
                    sb.append(titolo).append(":\n\n");
                    categoriaTrovata = true;
                }

                sb.append("- ").append(p.getNome());

                if (p.getDescrizione() != null && !p.getDescrizione().isBlank()) {
                    sb.append(" - ").append(p.getDescrizione());
                }

                sb.append(" - ").append(String.format("%.2f", p.getPrezzo())).append("€");
                if (!p.getAllergeni().isEmpty()) {
                    sb.append(" - Allergeni: ");
                    for (Allergene a : p.getAllergeni()) {
                        sb.append(a.getCodice())
                                .append("(")
                                .append(a.getDescrizione())
                                .append(") ");
                    }
                }
                sb.append("\n");
            }
        }

        if (categoriaTrovata) {
            sb.append("\n");
        }
    }

    private void stampaTipoBevanda (StringBuilder sb, Bevanda.TipoBevanda tipoBevanda, String titolo) {
        boolean categoriaTrovata = false;
        for (Bevanda b : bevande) {
            if (b.getTipo() == tipoBevanda) {
                if (!categoriaTrovata) {
                    sb.append(titolo).append(":\n\n");
                    categoriaTrovata = true;
                }
                sb.append("- ").append(b.getNome());
                if (b.getDescrizione() != null && !b.getDescrizione().isBlank()) {
                    sb.append(" - ").append(b.getDescrizione());
                }

                if (b.getOpzioniPrezzo() != null && !b.getOpzioniPrezzo().isEmpty()) {
                    sb.append(" - ");
                    for (int i = 0; i < b.getOpzioniPrezzo().size(); i++) {
                        OpzionePrezzo opzione = b.getOpzioniPrezzo().get(i);

                        sb.append(opzione.getFormato())
                                .append(": ")
                                .append(String.format("%.2f", opzione.getPrezzo()))
                                .append("€");

                        if (i < b.getOpzioniPrezzo().size() - 1) {
                            sb.append(" - ");
                        }
                    }
                }

                sb.append("\n");
            }
        }

        if (categoriaTrovata) {
            sb.append("\n");
        }
    }

    private void stampaColoreVino (StringBuilder sb, Vino.ColoreVino coloreVino, String titolo) {
        boolean categoriaTrovata = false;
        for (Vino v : vini) {
            if (v.getColoreVino() == coloreVino) {
                if (!categoriaTrovata) {
                    sb.append(titolo).append(":\n\n");
                    categoriaTrovata = true;
                }
                sb.append("- ").append(v.getNome());

                if (v.getDescrizione() != null && !v.getDescrizione().isBlank()) {
                    sb.append(" - ").append(v.getDescrizione());
                }
                sb.append(" - Cantina: ").append(v.getCantina());
                if (v.getOpzioniPrezzo() != null && !v.getOpzioniPrezzo().isEmpty()) {
                    sb.append(" - ");
                    for (int i = 0; i < v.getOpzioniPrezzo().size(); i++) {
                        OpzionePrezzo opzione = v.getOpzioniPrezzo().get(i);

                        sb.append(opzione.getFormato())
                                .append(": ")
                                .append(String.format("%.2f", opzione.getPrezzo()))
                                .append("€");

                        if (i < v.getOpzioniPrezzo().size() - 1) {
                            sb.append(" - ");
                        }
                    }
                }

                sb.append("\n");
            }
        }
        if (categoriaTrovata) {
            sb.append("\n");
        }
    }
}

