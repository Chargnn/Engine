package com.chargnn.utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class EngineMath {

    public static Matrix4f createTransformMatrix(Vector3f pos, Vector3f rot, Vector3f scale){
        Matrix4f mat = new Matrix4f().identity();

        mat.translate(pos);
        mat.rotateXYZ(rot);
        mat.scale(scale);

        return mat;
    }
}
