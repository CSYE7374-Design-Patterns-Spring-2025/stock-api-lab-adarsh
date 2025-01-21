package edu.neu.csye7374;

public class NaturalResourceStock extends Stock {

    public NaturalResourceStock(String name, double price, String description) {
        super(name, price, description);
    }

    @Override
    protected double calculateNewPrice(double bid) {
        return price + (bid - price) * 0.1;
    }

    @Override
    public String getMetric() {
        double volatility = bids.stream().mapToDouble(bid -> Math.abs(bid - price)).sum();
        return "Natural Resource Volatility: " + (volatility / Math.max(1, bids.size()));
    }
}
