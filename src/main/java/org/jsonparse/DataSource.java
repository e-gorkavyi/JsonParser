package org.jsonparse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DataSource {

    private final String fileName;

    public DataSource(String fileName) {
        this.fileName = fileName;
    }

    public List<Ticket> getData() throws FileNotFoundException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
                .create();
        JsonReader reader = new JsonReader(new FileReader(fileName));
        TicketsCollection tickets = gson.fromJson(reader, new TypeToken<TicketsCollection>(){}.getType());
        return tickets.getTickets();
    }
}
