package com.company;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Application {
    public void run() {
        View view = new View();
        Controller controller = new Controller();
        view.setController(controller);
        controller.setView(view);
        view.create();
        controller.startGame();
        methodList();
    }

    public void methodList() {
        Class c = new View().getClass();
        Method[] declaredMethods = c.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            for (int j = 0; j < declaredMethods[i].getParameterCount(); j++) {
                System.out.println(declaredMethods[i].getParameters()[j].getName());
            }
        }
        Field[] declaredField = c.getDeclaredFields();
        for (int i = 0; i < declaredField.length; i++) {
            System.out.println(declaredField[i].getName());
        }
    }
}