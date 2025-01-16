package edu.neu.csye7374;

class SemiconductorStock extends Stock {

    public SemiconductorStock(String name, double price, String description) {
        super(name, price, description);
    }

    @Override
    protected double calculateNewPrice(double bid) {
        return price * 0.95 + bid * 0.05;
    }

    @Override
    public String getMetric() {
        double averageBid = bids.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double metric = (averageBid - price) * Math.log(bids.size() + 1);
        metric *= Math.abs(averageBid - price);
        return String.format("Performance Metric: %.2f", metric);
    }

}