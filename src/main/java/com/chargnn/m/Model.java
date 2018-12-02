package com.chargnn.m;

import com.chargnn.c.TextureController;
import com.chargnn.c.VboController;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;


public class Model extends Entity{

    public float[] vertices;
    public int[] indices;
    public float[] texCoords;
    public int vaoID;
    public List<TextureController> textures = new ArrayList<>();

    public Model(float[] vertices, int[] indices, float[] texCoords){
        this.vertices = vertices;
        this.indices = indices;
        this.texCoords = texCoords;
        this.vaoID = GL30.glGenVertexArrays();

        GL30.glBindVertexArray(vaoID);
        VboController.storeIndices(indices, 3);
        VboController.storeData(vertices, 3, 0);
        VboController.storeData(texCoords, 2, 1);
        GL30.glBindVertexArray(0);
    }

    public void addTexture(String file){
        textures.add(new TextureController(new TextureModel(file)));
    }

    @Override
    public void translate(Vector3f np) {
        this.pos.add(np);
    }

    @Override
    public void rotate(Vector3f nr) {
        this.rot.add(nr);
    }

    @Override
    public void scale(Vector3f ns) {
        this.scale.add(ns);
    }
}
