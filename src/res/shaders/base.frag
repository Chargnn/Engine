#version 330 core

in vec2 out_texCoords;

out vec4 color;

uniform sampler2D texture0;
uniform sampler2D texture1;

void main(){
    color = mix(texture(texture0, out_texCoords), texture(texture1, out_texCoords ), 0.5f);
}
