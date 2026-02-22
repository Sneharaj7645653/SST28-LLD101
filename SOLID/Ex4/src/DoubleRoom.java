public class DoubleRoom implements PricingComponent{
    @Override
    public Money price() {
        return new Money(8000);
    }
}
