package com.chargnn.v;

import com.chargnn.c.ShaderController;
import com.chargnn.m.Camera;
import com.chargnn.m.Model;
import com.chargnn.utils.EngineMath;
import com.sun.xml.internal.ws.api.pipe.Engine;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class RendererView {



    public static void renderVerticesModel(Model model, ShaderController shader){
        GL30.glBindVertexArray(model.vaoID);
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        renderTextures(model, shader);
        updateTransformMatrix(model, shader);

        GL11.glDrawElements(GL11.GL_TRIANGLES, model.indices.length, GL11.GL_UNSIGNED_INT, 0);

        unbindTextures(model);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);

    }

    private static void updateTransformMatrix(Model model, ShaderController shader){
        Matrix4f mat = EngineMath.createTransformMatrix(model.pos, model.rot, model.scale);
        shader.setUniformMat4f(GL30.glGetUniformLocation(shader.shader.programID, "transformMatrix"), mat);
    }

    private static void renderTextures(Model model, ShaderController shader){
        for(int i = 0; i < model.textures.size(); i++){
            GL30.glActiveTexture(GL30.GL_TEXTURE0 + i);
            model.textures.get(i).bind();
            GL30.glUniform1i(GL30.glGetUniformLocation(shader.shader.programID, "texture" + i), i);
        }
    }

    private static void unbindTextures(Model model){
        for(int i = 0; i < model.textures.size(); i++) {
            model.textures.get(i).unbind();
        }
    }
}
