public class Price {
    private double monetaryAmount;
    private String currency;

    public Price(double monetaryAmount, String currency) {
        this.monetaryAmount = roundUpToTwoDecimals(monetaryAmount);
        this.currency = currency;
    }

    private double roundUpToTwoDecimals(double monetaryAmount) {
        return Math.ceil(monetaryAmount * 100.0) / 100.0;
    }

    public double getMonetaryAmount() {
        return monetaryAmount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Price{" +
                "monetaryAmount=" + monetaryAmount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
