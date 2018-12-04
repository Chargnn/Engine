package com.chargnn.c;

import com.chargnn.i.IUpdateLogic;
import com.chargnn.m.Camera;
import com.chargnn.m.ShaderModel;
import com.chargnn.m.Model;
import com.chargnn.v.RendererView;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class GameController implements IUpdateLogic {


    private Model model;
    private ShaderController shader;
    private Camera camera;

    @Override
    public void init() {
        model = new Model(new float[]{-0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,0.5f,-0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,-0.5f,0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                0.5f,0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                -0.5f,-0.5f,0.5f,
                -0.5f,0.5f,0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,0.5f,-0.5f,
                0.5f,0.5f,-0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,-0.5f,0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f},
                new int[]{0,1,3,
                        3,1,2,
                        4,5,7,
                        7,5,6,
                        8,9,11,
                        11,9,10,
                        12,13,15,
                        15,13,14,
                        16,17,19,
                        19,17,18,
                        20,21,23,
                        23,21,22},
                new float[]{0,0,
                        0,1,
                        1,1,
                        1,0,
                        0,0,
                        0,1,
                        1,1,
                        1,0,
                        0,0,
                        0,1,
                        1,1,
                        1,0,
                        0,0,
                        0,1,
                        1,1,
                        1,0,
                        0,0,
                        0,1,
                        1,1,
                        1,0,
                        0,0,
                        0,1,
                        1,1,
                        1,0});

        model.addTexture("src/res/textures/test.png");
        model.addTexture("src/res/textures/test2.png");
        model.translate(new Vector3f(0, 0, -1));

        shader = new ShaderController(new ShaderModel("src/res/shaders/base.vert", "src/res/shaders/base.frag"));
        camera = new Camera(shader);
    }

    @Override
    public void update(double delta) {
        camera.inputUpdate(delta);
    }

    @Override
    public void render() {
        shader.bind();
        RendererView.renderVerticesModel(model, shader);
        shader.unbind();
    }
}
