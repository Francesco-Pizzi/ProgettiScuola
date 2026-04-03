package it.unicam.mdp_mgc_2026.persistence.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import it.unicam.mdp_mgc_2026.annotations.Persona;

import java.lang.reflect.Type;

public class PersonaSerializer implements JsonSerializer<Persona> {
    @Override
    public JsonElement serialize(Persona p, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("nome", p.getNome());
        jsonObject.addProperty("cognome", p.getCognome());
        jsonObject.addProperty("cf", p.getCf());
        return jsonObject;
    }
}
