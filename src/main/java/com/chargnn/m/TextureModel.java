package com.chargnn.m;

import org.lwjgl.opengl.GL30;

public class TextureModel {

    public int textureID;
    public String file;
    public int width;
    public int height;

    public TextureModel(String file){
        this.file = file;
        textureID = GL30.glGenTextures();
    }
}
