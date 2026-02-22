public final class Money {

    private final double amount;

    public Money(double amount) {
        this.amount = round2(amount);
    }

    public static Money zero() {
        return new Money(0.0);
    }

    public Money add(Money other) {
        return new Money(this.amount + other.amount);
    }

    public double amount() {
        return amount;
    }

    private static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return String.format("%.2f", amount);
    }
}