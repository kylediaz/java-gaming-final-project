package com.kylediaz.metalgearocelot.input;

import java.util.Map;
import java.util.TreeMap;

public class Input {

    private static Map<Enum, InputButton> buttons = new TreeMap<>();
    private static Map<Enum, InputAxis> axisMap = new TreeMap<>();

    public static boolean getButton(Enum id) {
        return buttons.get(id).get();
    }
    public static double getAxis(Enum id) {
        return axisMap.get(id).get();
    }
    public static void setButton(Enum id, InputButton button) {
        buttons.put(id, button);
    }
    public static void setAxis(Enum id, InputAxis axis) {
        axisMap.put(id, axis);
    }

}