package edu.vanier.collisionsimulator.controllers;

import edu.vanier.collisionsimulator.simulator.CollisionObject;

/**
 *
 * @author Steven Lam, Matthew Hantar
 *
 */
public class PhysicsController {

    /**
     * Calculates speed post collision.
     * See https://en.wikipedia.org/wiki/Elastic_collision#Two-dimensional_collision_with_two_moving_objects
     * @param a
     * @param b
     * @return 
     */
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
}
