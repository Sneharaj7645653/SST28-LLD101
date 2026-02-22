public class LaundryAddOn implements PricingComponent{
    @Override
    public Money price() {
        return new Money(500);
    }
}
