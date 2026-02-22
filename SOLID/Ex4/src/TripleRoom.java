public class TripleRoom implements PricingComponent{
    @Override
    public Money price() {
        return new Money(2000);
    }
}
