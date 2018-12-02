package com.chargnn.m;

import org.joml.Vector3f;

public abstract class Entity {

    public Vector3f pos;
    public Vector3f rot;
    public Vector3f scale;

    public Entity(){
        this.pos = new Vector3f();
        this.rot = new Vector3f();
        this.scale = new Vector3f(1,1,1);
    }

    public abstract void translate(Vector3f np);
    public abstract void rotate(Vector3f nr);
    public abstract void scale(Vector3f ns);
}
