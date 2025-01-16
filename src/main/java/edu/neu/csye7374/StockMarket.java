package edu.neu.csye7374;

import java.util.HashMap;
import java.util.Map;

class StockMarket {
    private static StockMarket instance;
    private final Map<String, Stock> stocks;

    private StockMarket() {
        stocks = new HashMap<>();
    }

    public static StockMarket getInstance() {
        if (instance == null) {
            synchronized (StockMarket.class) {
                if (instance == null) {
                    instance = new StockMarket();
                }
            }
        }
        return instance;
    }

    public void addStock(Stock stock) {
        stocks.put(stock.name, stock);
    }

    public void removeStock(String name) {
        stocks.remove(name);
    }

    public void tradeStock(String name, String bid) {
        Stock stock = stocks.get(name);
        if (stock != null) {
            stock.setBid(bid);
        } else {
            System.out.println("Stock not found: " + name);
        }
    }

    public void showAllStocks() {
        stocks.values().forEach(System.out::println);

    }

    public static void demo(){
        StockMarket market = StockMarket.getInstance();

        Stock techStock = new TechStock("IBM", 131.15, "IBM Common Stock");
        Stock pharmaStock = new PharmaStock("Pfizer", 50.75, "Pfizer Pharma Stock");
        Stock autoStock = new AutoStock("Tesla", 720.50, "Tesla Auto Stock");
        Stock financeStock = new FinanceStock("Chase", 420.50, "Chase Finance Stock");
        Stock energyStock = new EnergyStock("NationalGrid", 290.50, "NationalGrid Energy Stock");
        Stock semiConductorStock = new SemiconductorStock("AMD", 119.10, "AMD Semiconductor Stock");

        market.addStock(techStock);
        market.addStock(pharmaStock);
        market.addStock(autoStock);
        market.addStock(financeStock);
        market.addStock(energyStock);
        market.addStock(semiConductorStock);

        String[] bids = {"10", "20", "30", "40", "50", "60"};

        for (String bid : bids) {
            System.out.println("Bid: " + bid);
            market.tradeStock("IBM", bid);
            market.tradeStock("Pfizer", bid);
            market.tradeStock("Tesla", bid);
            market.tradeStock("Chase", bid);
            market.tradeStock("NationalGrid", bid);
            market.showAllStocks();
            System.out.println();
        }

        String[] amdBids = {"119", "118.20", "115.30", "120.12", "136.47", "129.23"};
        for (String bid : amdBids) {
            market.tradeStock("AMD", bid);
        }

        market.removeStock("IBM");
        market.removeStock("Pfizer");
        System.out.println("After removing the stocks we are left with - ");
        market.showAllStocks();

    }
}
