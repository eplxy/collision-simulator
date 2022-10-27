package edu.vanier.collisionsimulator.controllers;

import edu.vanier.collisionsimulator.simulator.CollisionObject;

/**
 *
 * @author 2186084
 */
public class PhysicsController {

    public static CustomVector[] collidedSpeed(CollisionObject objA, CollisionObject objB) {

        CustomVector cvAVel = new CustomVector(objB.getVelocityX(), objB.getVelocityY());
        CustomVector cvBVel = new CustomVector(objA.getVelocityX(), objA.getVelocityY());
        
        
        //current issue is that the collision i've implemented does not account for
        //angles or mass. it only switches two objects' velocities so far.
        
        
        //TODO: implement angle interaction
        //TODO: implement mass interaction (conservation of momentum)
        return new CustomVector[]{cvAVel, cvBVel};

    }

}
