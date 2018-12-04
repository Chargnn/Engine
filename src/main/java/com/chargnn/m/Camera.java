package com.chargnn.m;

import com.chargnn.c.Keyboard;
import com.chargnn.c.Mouse;
import com.chargnn.c.ShaderController;
import com.chargnn.utils.EngineMath;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Camera extends Entity {

    public float fov = 70f;
    public float nearPlane = 0.01f;
    public float farPlane = 100f;
    public ShaderController shader;
    public Matrix4f projectionMatrix;

    private float maxXDegree = 360;
    private float maxYDegree = 360;

    public Camera(ShaderController shader){
        this.shader = shader;

        projectionMatrix = EngineMath.createProjectionMatrix(this);
        shader.bind();
        shader.setUniformMat4f(GL20.glGetUniformLocation(shader.shader.programID, "projectionMatrix"), projectionMatrix);
        shader.unbind();
    }


    /**
     * x - pitch
     * y - yaw
     * z - roll
     */

    public void inputUpdate(double delta){
        float speed = (float) (0.01f*delta);

        rot.x += (float) Mouse.getMouseDY();
        rot.y += (float) Mouse.getMouseDX();

        if(Keyboard.isKeyDown(GLFW_KEY_W)) {
            pos.z -= (float) (Math.cos(Math.toRadians(rot.y)) * speed);
            pos.y -= (float) (Math.sin(Math.toRadians(rot.x)) * speed);
            pos.x += (float) (Math.sin(Math.toRadians(rot.y)) * speed);
        }
        if(Keyboard.isKeyDown(GLFW_KEY_S)){
            pos.z += (float) (Math.cos(Math.toRadians(rot.y)) * speed);
            pos.y += (float) (Math.sin(Math.toRadians(rot.x)) * speed);
            pos.x -= (float) (Math.sin(Math.toRadians(rot.y)) * speed);
        }
        if(Keyboard.isKeyDown(GLFW_KEY_A)){
            pos.z -= (float) (Math.cos(Math.toRadians(rot.y - 90)) * speed);
            pos.x += (float) (Math.sin(Math.toRadians(rot.y - 90)) * speed);
        }
        if(Keyboard.isKeyDown(GLFW_KEY_D)){
            pos.z -= (float) (Math.cos(Math.toRadians(rot.y + 90)) * speed);
            pos.x += (float) (Math.sin(Math.toRadians(rot.y + 90)) * speed);
        }
        if(Keyboard.isKeyDown(GLFW_KEY_SPACE))
            pos.y -= 0.1f * delta;
        if(Keyboard.isKeyDown(GLFW_KEY_LEFT_SHIFT))
            pos.y += 0.1f * delta;

        shader.bind();
        shader.setUniformMat4f(GL30.glGetUniformLocation(shader.shader.programID, "viewMatrix"), EngineMath.createViewMatrix(this));
        shader.unbind();
    }

    @Override
    public void translate(Vector3f np) {

    }

    @Override
    public void rotate(Vector3f nr) {

    }

    @Override
    public void scale(Vector3f ns) {

    }
}
