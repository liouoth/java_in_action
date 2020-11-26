package jdk8.lang._enum;

public enum SexEnum {
    MAN("男人"),
    WOMAN("女人");
    private String s;

    SexEnum(String s) {
        this.s = s;
    }
}
