package com.mkhaleghy.cinemakt;

public class MainJava {

    public static void main(){

        for (int i = 0; i < 30; i++) {
            final int finalI = i;
            new call() {
                @Override
                public void onSomthing() {
                    System.out.printf(""+ finalI);
                }
            };
        }

        StaticFieldsAndMethodsKt.setX(10);


    }


    interface call{
        void onSomthing();
    }

}
