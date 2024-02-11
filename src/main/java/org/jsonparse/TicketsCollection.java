package org.jsonparse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TicketsCollection {

    @SerializedName("tickets")
    @Expose
    private final List<Ticket> tickets;
}
