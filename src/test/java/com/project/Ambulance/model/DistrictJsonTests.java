package com.project.Ambulance.model;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class DistrictJsonTests {

    @Test
    void convertJsonProducesValidJson() throws Exception {
        District district = new District(1, "Central");
        String json = district.convertJson();
        assertNotNull(json);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        assertEquals(1, node.get("idDistrict").asInt());
        assertEquals("Central", node.get("nameDistrict").asText());
    }
}
