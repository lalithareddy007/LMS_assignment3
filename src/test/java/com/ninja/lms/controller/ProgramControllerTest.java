package com.ninja.lms.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja.lms.dto.ProgramBatchDto;
import com.ninja.lms.dto.ProgramDto;
import com.ninja.lms.service.ProgramService;

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

@ContextConfiguration(classes = {ProgramController.class})
@ExtendWith(SpringExtension.class)
class ProgramControllerTest {
    @Autowired
    private ProgramController programController;

    @MockBean
    private ProgramService programService;

    /**
     * Method under test: {@link ProgramController#updateProgram(ProgramDto)}
     */
    @Test
    void testUpdateProgram() throws Exception {
        when(programService.updateProgram(Mockito.<ProgramDto>any())).thenReturn(new ProgramDto());
        Timestamp creationTime = mock(Timestamp.class);
        when(creationTime.getTime()).thenReturn(10L);
        Timestamp lastModTime = mock(Timestamp.class);
        when(lastModTime.getTime()).thenReturn(10L);

        ProgramDto programDto = new ProgramDto();
        programDto.setCreationTime(creationTime);
        programDto.setLastModTime(lastModTime);
        programDto.setProgramDescription("Program Description");
        programDto.setProgramId(1L);
        programDto.setProgramName("Program Name");
        programDto.setProgramStatus("Program Status");
        String content = (new ObjectMapper()).writeValueAsString(programDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/program/updateProgram")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(programController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"programId\":null,\"programName\":null,\"programDescription\":null,\"programStatus\":null,\"creationTime\""
                                        + ":null,\"lastModTime\":null}"));
    }

    /**
     * Method under test: {@link ProgramController#createAndSaveProgram(ProgramDto)}
     */
    @Test
    void testCreateAndSaveProgram() throws Exception {
        when(programService.createProgram(Mockito.<ProgramDto>any())).thenReturn(new ProgramDto());
        Timestamp creationTime = mock(Timestamp.class);
        when(creationTime.getTime()).thenReturn(10L);
        Timestamp lastModTime = mock(Timestamp.class);
        when(lastModTime.getTime()).thenReturn(10L);

        ProgramDto programDto = new ProgramDto();
        programDto.setCreationTime(creationTime);
        programDto.setLastModTime(lastModTime);
        programDto.setProgramDescription("Program Description");
        programDto.setProgramId(1L);
        programDto.setProgramName("Program Name");
        programDto.setProgramStatus("Program Status");
        String content = (new ObjectMapper()).writeValueAsString(programDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/program/saveProgram")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(programController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"programId\":null,\"programName\":null,\"programDescription\":null,\"programStatus\":null,\"creationTime\""
                                        + ":null,\"lastModTime\":null}"));
    }

    /**
     * Method under test: {@link ProgramController#deleteProgram(Long)}
     */
    @Test
    void testDeleteProgram() throws Exception {
        when(programService.deleteProgram(Mockito.<Long>any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/program/deleteProgram/{programId}",
                1L);
        MockMvcBuilders.standaloneSetup(programController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Program deleted"));
    }

    /**
     * Method under test: {@link ProgramController#deleteProgram(Long)}
     */
    @Test
    void testDeleteProgram2() throws Exception {
        when(programService.deleteProgram(Mockito.<Long>any())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/program/deleteProgram/{programId}",
                1L);
        MockMvcBuilders.standaloneSetup(programController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Program not found"));
    }

    /**
     * Method under test: {@link ProgramController#getOneProgramById(Long)}
     */
    @Test
    void testGetOneProgramById() throws Exception {
        when(programService.fetchProgramByProgramId(Mockito.<Long>any())).thenReturn(new ProgramBatchDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/program/programId/{programId}", 1L);
        MockMvcBuilders.standaloneSetup(programController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"programId\":null,\"programName\":null,\"programDescription\":null,\"programStatus\":null,\"creationTime\""
                                        + ":null,\"lastModTime\":null,\"batches\":null}"));
    }

    /**
     * Method under test: {@link ProgramController#getPrograms()}
     */
    @Test
    void testGetPrograms() throws Exception {
        when(programService.getAllPrograms()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/program/programs");
        MockMvcBuilders.standaloneSetup(programController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

