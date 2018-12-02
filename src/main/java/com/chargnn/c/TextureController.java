package com.chargnn.c;

import com.chargnn.m.TextureModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class TextureController {

    public TextureModel texture;

    public TextureController(TextureModel texture){
        this.texture = texture;
        BufferedImage image;
        try {
            image = ImageIO.read(new File(texture.file));
            this.texture.width = image.getWidth();
            this.texture.height = image.getHeight();

            int[] pixels_raw;
            pixels_raw = image.getRGB(0, 0, this.texture.width, this.texture.height, null, 0, this.texture.width);

            ByteBuffer pixels = BufferUtils.createByteBuffer(this.texture.width * this.texture.height * 4);
            for(int i = 0; i < this.texture.width; i++){
                for(int j = 0; j < this.texture.height; j++){
                    int pixel = pixels_raw[i * this.texture.width + j];
                    pixels.put((byte) ((pixel >> 16) & 0xFF));
                    pixels.put((byte) ((pixel >> 8) & 0xFF));
                    pixels.put((byte) (pixel & 0xFF));
                    pixels.put((byte) ((pixel >> 24) & 0xFF));
                }
            }

            pixels.flip();

            bind();
            GL13.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MIN_FILTER, GL30.GL_LINEAR_MIPMAP_LINEAR);
            GL13.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MAG_FILTER, GL30.GL_LINEAR);
            GL13.glTexImage2D(GL30.GL_TEXTURE_2D, 0, GL30.GL_RGBA8, this.texture.width,  this.texture.height, 0, GL30.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
            GL30.glGenerateMipmap(GL30.GL_TEXTURE_2D);
            unbind();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bind(){
        GL30.glBindTexture(GL30.GL_TEXTURE_2D, texture.textureID);
    }

    public void unbind(){
        GL30.glBindTexture(GL30.GL_TEXTURE_2D, 0);
    }
}
