#version 330 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 texCoords;

out vec2 out_texCoords;

uniform mat4 transformMatrix;

void main(){
    gl_Position = transformMatrix * vec4(position, 1.0);
    out_texCoords = texCoords;
}