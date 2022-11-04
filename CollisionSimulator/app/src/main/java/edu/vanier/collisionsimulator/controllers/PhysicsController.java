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

        CustomVector v = new CustomVector(objA.getPosX() - objB.getPosX(), objA.getPosY() - objB.getPosY());

        CustomVector vA = new CustomVector(
                ((aMass - bMass) * aVelX + 2 * bMass * bVelX) / (aMass + bMass),
                ((aMass - bMass) * aVelY + 2 * bMass * bVelY) / (aMass + bMass)
        );
        CustomVector vB = new CustomVector(
                ((bMass - aMass) * bVelX + 2 * aMass * aVelX) / (aMass + bMass),
                ((bMass - aMass) * bVelY + 2 * aMass * aVelY) / (aMass + bMass)
        );

        //components parallel to v
        CustomVector vA1 = new CustomVector(
                ((vA.x * v.x + vA.y * v.y) / (Math.pow(v.length, 2)) * v.x),
                ((vA.x * v.x + vA.y * v.y) / (Math.pow(v.length, 2)) * v.y)
        );
        CustomVector vB1 = new CustomVector(
                ((vB.x * v.x + vB.y * v.y) / (Math.pow(v.length, 2)) * v.x),
                ((vB.x * v.x + vB.y * v.y) / (Math.pow(v.length, 2)) * v.y)
        );

        //components perpendicular to v
        CustomVector vA2 = new CustomVector(
                (vA.x - vA1.x),
                (vA.y - vA1.y)
        );

        CustomVector vB2 = new CustomVector(
                (vB.x - vB1.x),
                (vB.y - vB1.y)
        );

        //final velocity vectors
        CustomVector vfA = new CustomVector(
                (vB1.x + vA2.x),
                (vB1.y + vA2.y)
        );
        CustomVector vfB = new CustomVector(
                (vB2.x + vA1.x),
                (vB2.y + vA1.y)
        );

        System.out.println("AposX=" + objA.getPosX() + " AposY=" + objA.getPosY() + " BposX="+ objB.getPosX()+ " BposY=" + objB.getPosY()+ " vy=" + v.y + " vx=" + v.x + " vAx=" + vA.x+ " vAy=" + vA.y+ " vA1x=" + vA1.x + " vA2x=" + vA2.x + " vfAx=" + vfA.x);
        return new CustomVector[]{vfB, vfA};

    }

}
