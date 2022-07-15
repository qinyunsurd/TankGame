package com.surd.one.cor;

import com.surd.one.GameObject;
import com.surd.one.PropertyMgr;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author admin
 * @date 责任链
 */
public class ColliderChain implements Collider {
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        //add(new BulletTankCollider());
        //add(new TankTankCollider());
        String colliders1 = PropertyMgr.getString("colliders");
        String[] split = colliders1.split(",");
        try {
            for (String s : split) {
                Collider o = (Collider) Class.forName(s).getDeclaredConstructor().newInstance();
                add(o);
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Collider c) {
        colliders.add(c);
    }

    public boolean collider(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collider(o1, o2)) {
                return false;
            }
        }
        return true;
    }
}
