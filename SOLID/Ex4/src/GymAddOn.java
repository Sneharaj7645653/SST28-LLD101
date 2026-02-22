public class GymAddOn implements PricingComponent{
    @Override
    public Money price() {
        return new Money(600);
    }
}
