package net.suncorp.service;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AtmServiceTest {
    @Mock
    private AtmService atmService = new AtmService();

    @Test
    public void testServiceInitialization() {
    }

    @Test
    public void withdrawMoney() {
    }

    @Test
    public void getTotals() {
        assertThat(atmService.getTotals().getMoneyTotal(), is(500));
    }

    @Test
    public void getTotalDenom() {
    }
}