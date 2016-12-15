package com.company;

import java.util.ArrayList;
import java.util.List;

public class PencilCase {
    public static List listclass = new ArrayList();

    public static Object getObject(int index) {
        Object object = listclass.get(index);
        try {
            object.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return object;
    }

    public static void removeElement(int index) {
        listclass.remove(index);
    }

    public static void addElement(Object var) {
        listclass.add(var);
    }
}
