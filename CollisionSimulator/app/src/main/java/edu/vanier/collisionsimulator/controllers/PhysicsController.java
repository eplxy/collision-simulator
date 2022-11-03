package edu.vanier.collisionsimulator.controllers;

import edu.vanier.collisionsimulator.simulator.CollisionObject;

/**
 *
 * @author 2186084
 */
public class PhysicsController {

    public static CustomVector[] collidedSpeed(CollisionObject objA, CollisionObject objB) {

        double aVelX = objA.getVelocityX();
        double aVelY = objA.getVelocityY();
        double bVelX = objB.getVelocityX();
        double bVelY = objB.getVelocityY();
        double aMass = objA.getMass();
        double bMass = objB.getMass();
        
        double aCenterX = objA.getPosX();
        double aCenterY = objA.getPosY();
        double bCenterX = objB.getPosX();
        double bCenterY = objB.getPosY();
        
        CustomVector v = new CustomVector(aCenterX-bCenterX, aCenterY-bCenterY);
        CustomVector cvAVel = new CustomVector(((aMass - bMass) * aVelX + 2 * bMass * bVelX) / (aMass + bMass), ((aMass - bMass) * aVelY + 2 * bMass * bVelY) / (aMass + bMass));
        CustomVector cvBVel = new CustomVector(((bMass - aMass) * bVelX + 2 * aMass * aVelX) / (aMass + bMass), ((bMass - aMass) * bVelY + 2 * aMass * aVelY) / (aMass + bMass));

        
        
        
        return new CustomVector[]{cvAVel, cvBVel};

    }

}
