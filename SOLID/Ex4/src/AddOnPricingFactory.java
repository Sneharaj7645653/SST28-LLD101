public class AddOnPricingFactory {

    public static PricingComponent from(AddOn addOn) {

        return switch (addOn) {
            case MESS -> new MessAddOn();
            case LAUNDRY -> new LaundryAddOn();
            case GYM -> new GymAddOn();
        };
    }
}