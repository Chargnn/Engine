package com.chargnn.c;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Mouse extends GLFWCursorPosCallback {

    public double mouseX;
    public double mouseY;
    private static double mouseDX;
    private static double mouseDY;
    private boolean wasFirst;

    @Override
    public String getSignature() {
        return super.getSignature();
    }

    @Override
    public void callback(long args) {
        super.callback(args);
    }

    @Override
    public void invoke(long window, double xpos, double ypos) {
        if (!wasFirst) {
            wasFirst = true;
        } else {
            mouseDX += xpos - mouseX;
            mouseDY += ypos - mouseY;
        }
        mouseX = xpos;
        mouseY = ypos;
    }

    public static double getMouseDX() {
        double result = mouseDX;
        mouseDX = 0;
        return result;
    }

    public static double getMouseDY() {
        double result = mouseDY;
        mouseDY = 0;
        return result;
    }

    @Override
    public void close() {
        super.close();
    }
}
