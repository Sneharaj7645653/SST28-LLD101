public class MessAddOn implements PricingComponent{
    @Override
    public Money price() {
        return new Money(1000);
    }
}
