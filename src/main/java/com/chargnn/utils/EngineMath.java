package com.chargnn.utils;

import com.chargnn.App;
import com.chargnn.m.Camera;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class EngineMath {

    public static Matrix4f createTransformMatrix(Vector3f pos, Vector3f rot, Vector3f scale){
        return new Matrix4f().identity().translate(pos).rotateXYZ(rot).scale(scale);
    }

    public static Matrix4f createProjectionMatrix(Camera camera){
        return new Matrix4f().perspective((float) Math.toRadians(camera.fov), (float) (App.getDisplayWidth() / App.getDisplayHeight()), camera.nearPlane, camera.farPlane);
    }

    public static Matrix4f createViewMatrix(Camera camera){
        Matrix4f mat = new Matrix4f().identity();
        mat.rotate((float) Math.toRadians(camera.rot.x), new Vector3f(1, 0, 0));
        mat.rotate((float) Math.toRadians(camera.rot.y), new Vector3f(0, 1, 0));
        mat.translate(-camera.pos.x, -camera.pos.y, -camera.pos.z);

        return mat;
    }
}
