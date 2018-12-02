package com.chargnn.c;


import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class DisplayController {

    public long windowID;

    public int windowWidth;
    public int windowHeight;

    IntBuffer w = BufferUtils.createIntBuffer(1);
    IntBuffer h = BufferUtils.createIntBuffer(1);

    public boolean createDisplay(int width, int height, String title){
        setWindowHint();
        windowID = glfwCreateWindow(width, height, title, 0, 0);

        try(MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(windowID, pWidth, pHeight);
            glfwGetWindowSize(windowID, w, h);
            windowWidth = w.get(0);
            windowHeight = h.get(0);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(windowID, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pWidth.get(0)) / 2);
        }

        return windowID != 0;
    }

    public void setCurrentContext(){
        glfwMakeContextCurrent(windowID);
    }

    private void setWindowHint(){
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
    }

    public void updateDisplay(){
        glfwGetWindowSize(windowID, w, h);
        if(windowWidth != w.get(0) || windowHeight != h.get(0)){
            windowWidth = w.get(0);
            windowHeight = h.get(0);
        }

        GL11.glViewport(0, 0, windowWidth, windowHeight);
        glfwSwapBuffers(windowID);
    }

    public void showWindow(){
        glfwShowWindow(windowID);
    }

    public void closeWindow(){
        glfwSetWindowShouldClose(windowID, true);
    }

    public boolean shouldClose(){
        return glfwWindowShouldClose(windowID);
    }
}
