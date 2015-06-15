package com.bazaarvoice.powermock.demo;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.easymock.EasyMock.expect;
import static org.powermock.api.easymock.PowerMock.*;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 11/3/14
 * Time: 10:18 AM
 * To change this template use File | Settings | File Templates.
 */
//@RunWith (PowerMockRunner.class)
@PrepareForTest (IdGenerator.class)
public class Demo {
    @Test
    public void testRegisterService() throws Exception {
        long expectedId = 42;

        // We create a new instance of test class under test as usually.
        ServiceRegistrator tested = new ServiceRegistrator();

        // This is the way to tell PowerMock to mock all static methods of a
        // given class
        PowerMock.mockStatic(IdGenerator.class);

        /*
         * The static method call to IdGenerator.generateNewId() expectation.
         * This is why we need PowerMock.
         */
        EasyMock.expect(IdGenerator.generateNewId()).andReturn(expectedId);

        // Note how we replay the class, not the instance!
        PowerMock.replay(IdGenerator.class);

        long actualId = tested.registerService(new Object());

        // Note how we verify the class, not the instance!
        verify(IdGenerator.class);

        // Assert that the ID is correct
        Assert.assertEquals(expectedId, actualId);
    }
}
class ServiceRegistrator {

    /**
     * Holds all services that has been registered to this service registry.
     */
    private final Map<Long, Object> serviceRegistry = new HashMap<Long, Object>();

    public long registerService(Object service) {
        final long id = IdGenerator.generateNewId();
        serviceRegistry.put(id, service);
        return id;
    }

}

class IdGenerator {

    /**
     * @return A new ID based on the current time.
     */
    public static long generateNewId() {
        return System.currentTimeMillis();
    }
}