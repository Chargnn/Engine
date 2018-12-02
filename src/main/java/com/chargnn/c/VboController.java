package com.chargnn.c;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class VboController {

    public static void storeData(float[] data, int vertexSize, int attributeNumber){
        int vbo = GL15.glGenBuffers();

        FloatBuffer vertexData = BufferUtils.createFloatBuffer(data.length * vertexSize);
        vertexData.put(data);
        vertexData.flip();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexData, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, vertexSize, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    public static void storeIndices(int[] data, int vertexSize){
        int vbo = GL15.glGenBuffers();

        IntBuffer vertexData = BufferUtils.createIntBuffer(data.length * vertexSize);
        vertexData.put(data);
        vertexData.flip();

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, vertexData, GL15.GL_STATIC_DRAW);
    }
}
