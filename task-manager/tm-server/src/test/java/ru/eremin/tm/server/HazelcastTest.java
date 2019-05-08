package ru.eremin.tm.server;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * @autor av.eremin on 08.05.2019.
 */

public class HazelcastTest {

    @Test
    public void mapTest() {
        final HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        final Map map1 = instance.getMap("testmap");
        for (int i = 0; i < 1000; i++) {
            map1.put(i, "value" + i);
        }
        assertEquals(1000, map1.size());
        final HazelcastInstance instance1 = Hazelcast.newHazelcastInstance();
        final Map map2 = instance1.getMap("testmap");
        assertEquals(1000, map2.size());
        assertEquals(1000, map1.size());
        instance1.getMap("testmap").clear();
    }

    @Test
    @Ignore
    public void membersTest() {
        final HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        final HazelcastInstance instance1 = Hazelcast.newHazelcastInstance();
        assertEquals(2, instance.getCluster().getMembers().size());
        assertEquals(2, instance1.getCluster().getMembers().size());
    }

}
