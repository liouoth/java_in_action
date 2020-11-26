package jdk8.lang._enum;

public enum DrinkEnum {
    COLA(3),
    SPRITE(4),
    SEVEN_UP(4);

    public float price;

    DrinkEnum(float price){
        this.price = price;
    }
}
