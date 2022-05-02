/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.gameObjects;

import code.transform.Vector2;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author a21rebecanf
 */
public class GameObject {

    public Vector2 position;
    public BufferedImage[] sprites;
    public static int frame;
    public static final int MAX_FRAME=3;

    public GameObject(Vector2 position) {
        this.position = position;
    }

    public void draw(Graphics2D g2) {

    }

    public void changeFrame() {

        frame++;

        if (frame >= MAX_FRAME) {
            frame = 0;
        }

    }
    
    protected void getSprites(){
    
    
    
    }

}
