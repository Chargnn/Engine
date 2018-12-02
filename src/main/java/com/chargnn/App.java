package com.chargnn;

import com.chargnn.c.DisplayController;
import com.chargnn.c.GameController;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Hello world!
 *
 */
public class App
{
    DisplayController display = new DisplayController();
    GameController game = new GameController();
    public static void main( String[] args ) throws Exception {
        new App().init();
    }

    private void init() throws InstantiationException {
        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit())
            throw new InstantiationException("Unable to init glfw");

        if(!display.createDisplay(500, 500, "Test")){
            throw new InstantiationException("Unable to create display");
        }
        display.setCurrentContext();
        display.showWindow();

        GL.createCapabilities();

        game.init();

        run();
    }

    private void run(){
        long lastTime = System.nanoTime();
        long now;
        long timer = System.currentTimeMillis();
        double delta = 0;
        int frames = 0;

        GL11.glClearColor(0.2f, 0, 0, 0);
        GL11.glViewport(0, 0, display.windowWidth, display.windowHeight);

        while(!display.shouldClose()){
            glfwPollEvents();
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            now = System.nanoTime();
            delta = (now - lastTime)/ (1000000000f / 60.0f);
            lastTime = now;

            update(delta);
            render();

            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("frames: " + frames);
                frames = 0;
            }
            display.updateDisplay();
        }

        stop();
    }

    private void update(double delta){
        game.update(delta);
    }

    private void render(){
        game.render();
    }

    private void stop(){
        display.closeWindow();
        glfwDestroyWindow(display.windowID);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
