package com.chargnn.m;

import com.chargnn.c.ShaderController;
import org.lwjgl.opengl.GL20;

public class ShaderModel {

    public int programID;
    public int vertexShaderID;
    public int fragmentShaderID;

    public ShaderModel(String vertexFile, String fragFile){
        vertexShaderID = ShaderController.loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = ShaderController.loadShader(fragFile, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
    }

}
