package com.chargnn.c;

import com.chargnn.m.ShaderModel;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

public class ShaderController {

    public ShaderModel shader;
    private FloatBuffer matrixBuffer4 = BufferUtils.createFloatBuffer(4*4);

    public ShaderController(ShaderModel shader){
        this.shader = shader;

        attachShader(shader.vertexShaderID);
        attachShader(shader.fragmentShaderID);
        linkProgram();
        validateProgram();
    }

    public static int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine())!=null){
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }
        return shaderID;
    }

    public void bind(){
        GL20.glUseProgram(shader.programID);
    }

    public void unbind(){
        GL20.glUseProgram(0);
    }

    public void setUniform1f(int loc, float v){
        GL20.glUniform1f(loc, v);
    }

    public void setUniform3f(int loc, Vector3f v){
        GL20.glUniform3f(loc, v.x, v.y, v.z);
    }

    public void setUniform1b(int loc, boolean v){
        GL20.glUniform1f(loc, v ? 1:0);
    }

    public void setUniformMat4f(int loc, Matrix4f v){
        GL20.glUniformMatrix4fv(loc, false, v.get(matrixBuffer4));
    }

    protected void attachShader(int shaderID){
        GL20.glAttachShader(shader.programID, shaderID);
    }

    protected void linkProgram(){
        GL20.glLinkProgram(shader.programID);
    }

    protected void validateProgram(){
        GL20.glValidateProgram(shader.programID);
    }
}
