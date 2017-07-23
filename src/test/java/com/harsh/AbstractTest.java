package com.harsh;

import com.harsh.config.AppConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * An abstract class which can be extended to all tests to run with spring
 * Created by Harsh on 22-Jul-17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public abstract class AbstractTest {

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
}
