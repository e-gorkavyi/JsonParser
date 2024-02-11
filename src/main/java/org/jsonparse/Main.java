package org.jsonparse;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        DataSource dataSource = new DataSource(args[0]);

        List<Ticket> tickets = dataSource.getData();

        Map<String, Long> carrierMinTimeInMinutes = new HashMap<>();
        List<Integer> prices = new ArrayList<>();
        Long timeInMinutes = 0L;

        for (Ticket ticket : tickets) {
            timeInMinutes = ChronoUnit.MINUTES.between(LocalDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime()),
                    LocalDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime()));
            if (ticket.getOrigin().equals("VVO") && ticket.getDestination().equals("TLV")) {
                if (!carrierMinTimeInMinutes.containsKey(ticket.getCarrier())) {
                    carrierMinTimeInMinutes.put(ticket.getCarrier(), timeInMinutes);
                } else if (carrierMinTimeInMinutes.get(ticket.getCarrier()) > timeInMinutes) {
                    carrierMinTimeInMinutes.put(ticket.getCarrier(), timeInMinutes);
                }

                prices.add(ticket.getPrice());

            }
        }

        System.out.println("Minimal flight time between VVO and TLV:");
        carrierMinTimeInMinutes.forEach((key, value) -> {
            Long hours = value / 60;
            Long minutes = value % 60;
            System.out.printf("%s --- %d:%02d%n", key, hours, minutes);
        }
        );

        OptionalDouble averagePrice = prices
                .stream()
                .mapToDouble(a -> a)
                .average();

        DoubleStream sortedPrices = prices.stream().mapToDouble(a -> a).sorted();
        Double medianPrice = prices.size() % 2 == 0 ?
            sortedPrices.skip(prices.size()/2-1).limit(2).average().orElseThrow(IllegalStateException::new):
            sortedPrices.skip(prices.size()/2).findFirst().orElseThrow(IllegalStateException::new);

        if (averagePrice.isPresent()) {
            System.out.println("Difference between an average price and a minimal price for VVO - TLV flights:");
            Double priceDiff = averagePrice.getAsDouble() - medianPrice;
            System.out.printf("%5.2f%n", priceDiff);
        }

    }
}