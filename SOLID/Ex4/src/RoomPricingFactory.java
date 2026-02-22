public class RoomPricingFactory {

    public static PricingComponent from(int roomType) {

        return switch (roomType) {
            case LegacyRoomTypes.SINGLE -> new SingleRoom();
            case LegacyRoomTypes.DOUBLE -> new DoubleRoom();
            case LegacyRoomTypes.TRIPLE -> new TripleRoom();
            default -> throw new IllegalArgumentException("Unknown room type");
        };
    }
}