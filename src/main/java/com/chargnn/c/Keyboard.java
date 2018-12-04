package com.chargnn.c;

import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;

public class Keyboard extends GLFWKeyCallback {

    private static boolean[] pressedKeys = new boolean[65536];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        pressedKeys[key] = action != GLFW_RELEASE;
    }

    @Override
    public String getSignature() {
        return super.getSignature();
    }

    @Override
    public void callback(long args) {
        super.callback(args);
    }

    @Override
    public void close() {
        super.close();
    }

    public static boolean isKeyDown(int keycode){
        return pressedKeys[keycode];
    }
}
