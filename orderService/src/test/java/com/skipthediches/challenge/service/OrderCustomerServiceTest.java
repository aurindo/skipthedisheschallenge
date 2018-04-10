package com.skipthediches.challenge.service;

import com.skipthediches.challenge.service.entity.Customer;
import com.skipthediches.challenge.service.entity.OrderCustomer;
import com.skipthediches.challenge.service.entity.enumerators.OrderCustomerStatusEnum;
import com.skipthediches.challenge.service.entity.Product;
import com.skipthediches.challenge.service.repository.OrderCustomerRepository;
import com.skipthediches.challenge.service.service.OrderCustomerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderCustomerServiceTest {

    @Mock
    private OrderCustomerRepository orderCustomerRepository;

    @InjectMocks
    private OrderCustomerServiceImpl orderCustomerService;

    @Before
    public void init() {
    }

    @Test
    public void whenSaveAnOrderWithProductsShouldReturnOKAY() throws Exception {

        List<Product> productList = new ArrayList<Product>();
        productList.add(new Product(1L));

        OrderCustomer orderCustomer = new OrderCustomer(
                1L, null, new Customer(), productList);

        OrderCustomer orderCustomerSavedMock = new OrderCustomer(
                1L, OrderCustomerStatusEnum.WAITTING_RESTAURANT, new Customer(), productList);

        when(orderCustomerRepository.save(orderCustomer)).thenReturn(orderCustomerSavedMock);

        OrderCustomer orderCustomerSaved = orderCustomerService.save(orderCustomer);

        Assert.assertNotNull(orderCustomerSaved);
        Assert.assertEquals(OrderCustomerStatusEnum.WAITTING_RESTAURANT, orderCustomerSaved.getStatus());
    }

}
