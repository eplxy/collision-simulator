package edu.vanier.collisionsimulator.simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author stella
 */
public class CollisionObjectManager {

    
    //deferring from assignment 2's code to implement a manager
    //used simulation by simulation instance rather than globally
    public CollisionObjectManager() {
    }
    

    //TODO: code indices for deletion and creation of object

    private final List<CollisionObject> colObjs = new ArrayList<>();

    private final List<CollisionObject> collisionList = new ArrayList<>();

    private final List<CollisionObject> colObjsToBeRemoved = new ArrayList<>();

    public List<CollisionObject> getAllColObjs() {
        return colObjs;
    }

    public void addCollisionObjects(CollisionObject... incolObjs) {
        colObjs.addAll(Arrays.asList(incolObjs));
    }

    public void removeCollisionObjects(CollisionObject... incolObjs) {
        colObjs.removeAll(Arrays.asList(incolObjs));
    }

    public List<CollisionObject> getColObjsToBeRemoved() {
        return colObjsToBeRemoved;
    }

    public void addCollisionObjectsToBeRemoved(CollisionObject... colObjs) {
        if (colObjs.length > 1) {
            colObjsToBeRemoved.addAll(Arrays.asList((CollisionObject[]) colObjs));
        } else {
            colObjsToBeRemoved.add(colObjs[0]);
        }
    }

    public List<CollisionObject> getCollisionsToCheck() {
        return collisionList;
    }

    public void resetCollisionsToCheck() {
        collisionList.clear();
        collisionList.addAll(colObjs);
    }

    public void cleanupCollisionObjects() {

        colObjs.removeAll(colObjsToBeRemoved);

        colObjsToBeRemoved.clear();
    }

    
    
    
}
