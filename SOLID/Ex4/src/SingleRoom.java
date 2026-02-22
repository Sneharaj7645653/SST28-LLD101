public class SingleRoom implements PricingComponent{
    @Override
    public Money price() {
        return new Money(7000);
    }
}
