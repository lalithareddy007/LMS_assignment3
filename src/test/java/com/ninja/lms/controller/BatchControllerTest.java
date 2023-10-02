package com.ninja.lms.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja.lms.dto.BatchDto;
import com.ninja.lms.service.BatchService;

import java.sql.Timestamp;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BatchController.class})
@ExtendWith(SpringExtension.class)
class BatchControllerTest {
    @Autowired
    private BatchController batchController;

    @MockBean
    private BatchService batchService;

    /**
     * Method under test: {@link BatchController#updateBatch(Integer, Long)}
     */
    @Test
    void testUpdateBatch() throws Exception {
        when(batchService.updateBatch(Mockito.<Integer>any(), Mockito.<Long>any())).thenReturn("Batch program updated successfully.");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/batch/updateBatch/{batchId}/program/{programId}", 1, 503L);
        MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Batch program updated successfully."));
    }

    /**
     * Method under test: {@link BatchController#deleteBatch(Integer)}
     */
    @Test
    void testDeleteBatch() throws Exception {
        when(batchService.deleteBatch(Mockito.<Integer>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/batch/deleteBatch/{batchId}", 1);
        MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Batch deleted"));
    }

    /**
     * Method under test: {@link BatchController#deleteBatch(Integer)}
     */
    @Test
    void testDeleteBatch2() throws Exception {
        when(batchService.deleteBatch(Mockito.<Integer>any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/batch/deleteBatch/{batchId}", 1);
        MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Batch not found"));
    }

    /**
     * Method under test: {@link BatchController#getAllBatches()}
     */
    @Test
    void testGetAllBatches() throws Exception {
        when(batchService.fetchAllBatches()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/batch/batches");
        MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link BatchController#getBatchById(Integer)}
     */
    @Test
    void testGetBatchById() throws Exception {
        when(batchService.fetchBatchByBatchId(Mockito.<Integer>any())).thenReturn(new BatchDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/batch/batchId/{batchId}", 1);
        MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"batchId\":null,\"batchName\":null,\"batchDescription\":null,\"batchStatus\":null,\"batchNoOfClasses\":0,"
                                        + "\"programId\":null,\"creationTime\":null,\"lastModTime\":null}"));
    }

    /**
     * Method under test: {@link BatchController#getBatchesByProgramId(Long)}
     */
    @Test
    void testGetBatchesByProgramId() throws Exception {
        when(batchService.fetchBatchByProgramId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/batch/batches/{programId}", 1L);
        MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link BatchController#getBatchesByProgramId(Long)}
     */
    @Test
    void testGetBatchesByProgramId2() throws Exception {
        when(batchService.fetchAllBatches()).thenReturn(new ArrayList<>());
        when(batchService.fetchBatchByProgramId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/batch/batches/{programId}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link BatchController#saveBatch(BatchDto)}
     */
    @Test
    void testSaveBatch() throws Exception {
        when(batchService.createBatch(Mockito.<BatchDto>any())).thenReturn(new BatchDto());
        Timestamp creationTime = mock(Timestamp.class);
        when(creationTime.getTime()).thenReturn(10L);
        Timestamp lastModTime = mock(Timestamp.class);
        when(lastModTime.getTime()).thenReturn(10L);

        BatchDto batchDto = new BatchDto();
        batchDto.setBatchDescription("Batch Description");
        batchDto.setBatchId(1);
        batchDto.setBatchName("Batch Name");
        batchDto.setBatchNoOfClasses(1);
        batchDto.setBatchStatus("Active");
        batchDto.setCreationTime(creationTime);
        batchDto.setLastModTime(lastModTime);
        batchDto.setProgramId(1L);
        String content = (new ObjectMapper()).writeValueAsString(batchDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/batch/saveBatch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(batchController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"batchId\":null,\"batchName\":null,\"batchDescription\":null,\"batchStatus\":null,\"batchNoOfClasses\":0,"
                                        + "\"programId\":null,\"creationTime\":null,\"lastModTime\":null}"));
    }
}

