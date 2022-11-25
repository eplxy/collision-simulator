package edu.vanier.collisionsimulator.controllers;

import edu.vanier.collisionsimulator.simulator.CollisionObject;

/**
 *
 * @author 2186084
 */
public class PhysicsController {

    public static CustomVector[] collidedSpeed(CollisionObject a, CollisionObject b) {

        double m1 = a.getMass();
        double m2 = b.getMass();

        CustomVector v1 = a.getV();
        CustomVector v2 = b.getV();
        CustomVector x1 = new CustomVector(a.getPosX(), a.getPosY());
        CustomVector x2 = new CustomVector(b.getPosX(), b.getPosY());

        CustomVector deltaV1 = new CustomVector(v1.x - v2.x, v1.y - v2.y);
        CustomVector deltaV2 = new CustomVector(v2.x - v1.x, v2.y - v1.y);
        CustomVector deltaX1 = new CustomVector(x1.x - x2.x, x1.y - x2.y);
        CustomVector deltaX2 = new CustomVector(x2.x - x1.x, x2.y - x1.y);

        CustomVector proj1 = deltaX1.scalarMult(CustomVector.dotProduct(deltaV1, deltaX1) / Math.pow(deltaX1.length, 2));
        CustomVector proj2 = deltaX2.scalarMult(CustomVector.dotProduct(deltaV2, deltaX2) / Math.pow(deltaX2.length, 2));

        CustomVector massv1 = proj1.scalarMult((2 * m2) / (m1 + m2));
        CustomVector massv2 = proj2.scalarMult((2 * m1) / (m1 + m2));

        CustomVector vf1 = new CustomVector(v1.x - massv1.x, v1.y - massv1.y);
        CustomVector vf2 = new CustomVector(v2.x - massv2.x, v2.y - massv2.y);
        return new CustomVector[]{vf1, vf2};

    }
;

/*public static CustomVector[] collidedSpeed(CollisionObject objA, CollisionObject objB) {

        double aVelX = objA.getVelocityX();
        double aVelY = objA.getVelocityY();
        double bVelX = objB.getVelocityX();
        double bVelY = objB.getVelocityY();
        double aMass = objA.getMass();
        double bMass = objB.getMass();

        CustomVector v = new CustomVector(objA.getPosX() - objB.getPosX(), objA.getPosY() - objB.getPosY());
        v = v.normalize().scalarMult(objA.getWidth() + objB.getWidth());

        //FIXME: issue here - having objects of different masses messes with kinetic energy of the system
        //my theory is that the formula calls for actual speed (norm of velocity vector) and so we can't use components.
        //to test
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

        return new CustomVector[]{vfB, vfA};

    }
 */
}
