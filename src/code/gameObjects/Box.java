/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.gameObjects;

import static code.gameObjects.GameObject.MAX_FRAME;
import code.transform.Vector2;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author a21rebecanf
 */
public class Box extends Entity{
    
      @Override
    public void getSprites() {
        
        BufferedImage[] tempSprites = new BufferedImage[MAX_FRAME];
        try {
            
            tempSprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/box/box_0.png"));
            tempSprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/box/box_1.png"));
            tempSprites[0] = ImageIO.read(getClass().getResourceAsStream("/resources/img/box/box_2.png"));
            System.out.println("Holi");
            
        } catch (Exception e) {
            
            System.out.println("adiosi");
            e.printStackTrace();
        }
        
    }
    
    @Override
    public boolean move(Vector2 pos) {

        return false;

    }

    public Box(Vector2 position) {
        super(position);
        getSprites();
    }
    
    
    
}
