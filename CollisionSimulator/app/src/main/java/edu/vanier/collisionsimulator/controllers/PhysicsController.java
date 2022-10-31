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

        //velocity calculations with mass! STILL MISSING ANGLES
        //TODO: implement angle interaction
        CustomVector cvAVel = new CustomVector(((aMass - bMass) * aVelX + 2 * bMass * bVelX) / (aMass + bMass), ((aMass - bMass) * aVelY + 2 * bMass * bVelY) / (aMass + bMass));
        CustomVector cvBVel = new CustomVector(((bMass - aMass) * bVelX + 2 * aMass * aVelX) / (aMass + bMass), ((bMass - aMass) * bVelY + 2 * aMass * aVelY) / (aMass + bMass));

        //CustomVector cvAVelM = new CustomVector(objB.getVelocityX(), objB.getVelocityY());
        //CustomVector cvBVelM = new CustomVector(objA.getVelocityX(), objA.getVelocityY());
        
        return new CustomVector[]{cvAVel, cvBVel};

    }

}
