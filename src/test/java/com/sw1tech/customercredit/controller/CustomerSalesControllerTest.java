package com.sw1tech.customercredit.controller;

import com.sw1tech.customercredit.builder.CustomerSalesDtoBuilder;
import com.sw1tech.customercredit.dto.CustomerSalesDto;
import com.sw1tech.customercredit.exception.CustomerLimitCreditOutOfRangeException;
import com.sw1tech.customercredit.exception.CustomerSalesExceedLimitCreditException;
import com.sw1tech.customercredit.service.CustomerSalesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static com.sw1tech.customercredit.jsonconverterutils.JsonConvertUtils.asJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class CustomerSalesControllerTest {
    private static final String API_URL = "/api/v1/sales";


    private MockMvc mockMvc;

    @Mock
    private CustomerSalesService customerSalesService;

    @InjectMocks
    private CustomerSalesController customerSalesController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerSalesController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenACustomerIsCreated() throws Exception {
        // given
        CustomerSalesDto customerSalesDto = CustomerSalesDtoBuilder.builder().build().toCustomerSalesDto();

        // when
        when(customerSalesService.createCustomerSales(customerSalesDto)).thenReturn(customerSalesDto);

        // then
        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerSalesDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenPOSTIsCalledCreateCustomerLimitUpThenAnErrorIsReturned() throws Exception {
        // given
        CustomerSalesDto customerSalesDto = CustomerSalesDtoBuilder.builder().build().toCustomerSalesDto();

        customerSalesDto.setPrice(3330.00);
        // when
        when(customerSalesService.createCustomerSales(customerSalesDto))
        .thenThrow(CustomerSalesExceedLimitCreditException.class);

        // then
        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerSalesDto)))
                .andExpect(status().isBadRequest());
    }

}
