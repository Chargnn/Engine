package com.chargnn.c;

import com.chargnn.i.IUpdateLogic;
import com.chargnn.m.ShaderModel;
import com.chargnn.m.Model;
import com.chargnn.v.RendererView;
import org.joml.Vector3f;

public class GameController implements IUpdateLogic {


    private Model model;
    private ShaderController shader;

    @Override
    public void init() {
        model = new Model(new float[]{-0.5f, 0.5f, 0f, -0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f, 0.5f, 0f},
                new int[]{0, 1, 3, 3, 1, 2},
                new float[]{0, 0, 0, 1, 1, 1, 1, 0});

        model.addTexture("src/res/textures/test.png");
        model.addTexture("src/res/textures/test2.png");

        shader = new ShaderController(new ShaderModel("src/res/shaders/base.vert", "src/res/shaders/base.frag"));
    }

    @Override
    public void update(double delta) {
        model.rotate(new Vector3f(0, 0, (float) (0.001f * delta)));
    }

    @Override
    public void render() {
        shader.bind();
        RendererView.renderVerticesModel(model, shader);
        shader.unbind();
    }
}
