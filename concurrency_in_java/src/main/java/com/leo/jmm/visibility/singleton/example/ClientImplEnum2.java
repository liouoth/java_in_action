package com.leo.jmm.visibility.singleton.example;


public enum ClientImplEnum2 implements ClientEnumInterface {
    INSTANCE1{
        @Override
        public String getConnection() {
            return "INSTANCE1";
        }

        @Override
        public String getName() {
            return "INSTANCE1";
        }
    },
    INSTANCE2{
        @Override
        public String getConnection() {
            return "INSTANCE2";
        }

        @Override
        public String getName() {
            return "INSTANCE2";
        }
    };

    public static void main(String[] args) {
        System.out.println(ClientImplEnum2.INSTANCE1);
    }
}
