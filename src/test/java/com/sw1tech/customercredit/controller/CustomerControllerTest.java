package com.sw1tech.customercredit.controller;

import com.sw1tech.customercredit.builder.CustomerDtoBuilder;
import com.sw1tech.customercredit.builder.CustomerLimitDtoBuilder;
import com.sw1tech.customercredit.dto.CustomerDto;
import com.sw1tech.customercredit.dto.CustomerLimitDto;
import com.sw1tech.customercredit.exception.CustomerLimitCreditOutOfRangeException;
import com.sw1tech.customercredit.exception.CustomerNotFoundException;
import com.sw1tech.customercredit.service.CustomerService;
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
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    private static final String API_URL = "/api/v1/customers";


    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenACustomerIsCreated() throws Exception {
        // given
        CustomerDto customerDto = CustomerDtoBuilder.builder().build().toCustomerDto();

        // when
        when(customerService.createCustomer(customerDto)).thenReturn(customerDto);

        // then
        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(customerDto.getName())))
                .andExpect(jsonPath("$.limitcredit", is(customerDto.getLimitcredit() )))
                .andExpect(jsonPath("$.type", is(customerDto.getType().toString())));
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
        // given
        CustomerDto customerDto = CustomerDtoBuilder.builder().build().toCustomerDto();
        customerDto.setName(null);

        // then
        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPOSTIsCalledCreateCustomerLimitUpThenAnErrorIsReturned() throws Exception {
        // given
        CustomerDto customerDto = CustomerDtoBuilder.builder().build().toCustomerDto();

        customerDto.setLimitcredit(3330.00);
        // when
        when(customerService.createCustomer(customerDto))
        .thenThrow(CustomerLimitCreditOutOfRangeException.class);

        // then
        mockMvc.perform(post(API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPATCHIsCalledSaveCustomerThenAnErrorIsReturned() throws Exception {
        // given
        CustomerDto customerDto = CustomerDtoBuilder.builder().build().toCustomerDto();
        customerDto.setId(-1);

        // when
        when(customerService.saveCustomer(customerDto))
                .thenThrow(CustomerNotFoundException.class);

        // then
        mockMvc.perform(patch(API_URL+"/saveCustomer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDELETEIsCalledSaveCustomerThenAnErrorIsReturned() throws Exception {
        // given
        CustomerDto customerDto = CustomerDtoBuilder.builder().build().toCustomerDto();

        // then
        mockMvc.perform(delete(API_URL+"/" + customerDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenPATCHIsCalledSaveLimitCreditThenOk() throws Exception {
        // given
        CustomerLimitDto customerLimitDto = CustomerLimitDtoBuilder.builder().build().toCustomerLimitDto();

        // then
        mockMvc.perform(patch(API_URL+"/saveLimit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerLimitDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenPATCHIsCalledSaveLimitCreditThenAnErrorIsReturned() throws Exception {
        // given
        CustomerLimitDto customerLimitDto = CustomerLimitDtoBuilder.builder().build().toCustomerLimitDto();

        customerLimitDto.setLimitcredit(3000.00);

        // when
        when(customerService.saveLimit(customerLimitDto))
                .thenThrow(CustomerLimitCreditOutOfRangeException.class);

        // then
        mockMvc.perform(patch(API_URL+"/saveLimit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerLimitDto)))
                .andExpect(status().isBadRequest());
    }

}
