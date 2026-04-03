package it.unicam.mdp_mgc_2026.persistence.json;

import com.google.gson.*;
import it.unicam.mdp_mgc_2026.annotations.Persona;

import java.lang.reflect.Type;

public class PersonaDeserializer implements JsonDeserializer<Persona> {

    @Override
    public Persona deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String nome = jsonObject.get("nome").getAsString();
        String cognome = jsonObject.get("cognome").getAsString();
        String cf = jsonObject.get("cf").getAsString();
        Persona p = new Persona(nome, cognome, cf);
        return p;
    }
}
