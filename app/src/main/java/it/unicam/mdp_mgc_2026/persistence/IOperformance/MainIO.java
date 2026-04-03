package it.unicam.mdp_mgc_2026.persistence.IOperformance;

public class MainIO {
    public static void main(String[] args) {
        System.out.println("Starting PerformanceIO");
        IOperformance iop = new IOperformance();
        System.out.println("Ripetizioni, Memoria, File, Buffered File");
        for(int i = 1; i<=1000;i=i*10){
            Double[] r = iop.testPerformance(i);
            System.out.println(i+", "+r[0]+", "+r[1]+", "+r[2]);
        }

    }
}
