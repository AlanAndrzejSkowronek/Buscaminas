public interface GenericInOutUser {

    int chooseDifficulty();
    int[] pickCell();
    boolean chooseToPlaceFlag();
    void wantToAddFlagMessage();
    void numFlagsAvailableMessage(int flagsAvailable);
    void cantAddFlagMessage();
    void cantRevealFlagMessage();
}
