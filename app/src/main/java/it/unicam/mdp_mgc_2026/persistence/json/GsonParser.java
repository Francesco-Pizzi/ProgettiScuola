package it.unicam.mdp_mgc_2026.persistence.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.mdp_mgc_2026.annotations.Persona;
import it.unicam.mdp_mgc_2026.palla8.Palla8;

import java.io.*;

public class GsonParser {
    private static Gson gson;

    public static void writeGsonObj(Persona p, String file){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            gson.toJson(p, writer);
        } catch (IOException e) {
            System.err.println("Errore durante la scrittura: " + e.getMessage());
        }
    }

    public static Persona readGsonObj(String file){
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, Persona.class);
        } catch (IOException e) {
            System.err.println("Errore durante la lettura: " + e.getMessage());
        }
        return null;
    }

    public static void esempioCampieArray(){
        Gson gson = new Gson();
        gson.toJson(1);
        gson.toJson("abcd");
        gson.toJson(new Long(10));
        int[] values = { 1, 2, 3, 4 };
        gson.toJson(values);

        int i = gson.fromJson("1", int.class);
        Integer intObj = gson.fromJson("1", Integer.class);
        Long longObj = gson.fromJson("1", Long.class);
        Boolean boolObj = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] strArray = gson.fromJson("[\"abc\"]", String[].class);

        System.out.println(i + " " + intObj + " " + longObj + " " + boolObj + " " + str + " " + strArray);
    }

    public static void main(String[] args){
//        gson = new GsonBuilder().setPrettyPrinting().create();
//        //esempioCampieArray();
        Persona p1 = new Persona("Lorenzo","Rossi","C");
//        p1.setEta(37);
//        writeGsonObj(p1,"persona.json");
//        Persona p2 = (Persona) readGsonObj("persona.json");
//        System.out.println(p2.getEta());

        //Handler specifico
        gson = new GsonBuilder()
                .registerTypeAdapter(Persona.class, new PersonaSerializer())
                .registerTypeAdapter(Persona.class, new PersonaDeserializer())
                .setPrettyPrinting()
                .create();

        writeGsonObj(p1,"persona.json");
        Persona p3 = readGsonObj("persona.json");
        System.out.println(p3.getEta());
    }
}
