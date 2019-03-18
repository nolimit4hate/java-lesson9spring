package com.tmg.lesson9.dao.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class DefaultUserDaoImplTest {

    @Resource
    UserDao userDao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectUserByNameFromUsers() {
    }

    @Test
    public void selectUserByNamePasswordFromUsers() {
    }

    @Test
    public void insertIntoUsers() {
    }
}