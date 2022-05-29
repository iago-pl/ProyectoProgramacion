/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.gameObjects;

import code.transform.Vector2;

/**
 *
 * @author test
 */
public class UI extends GameObject{
    
    public UI(Vector2 position, GameObjectSprite objectType) {
        super(position, objectType);
        sep = 0;
    }
    
}
