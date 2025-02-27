package com.nayan.app.crud_courses_app.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FormatErrorMessage {

    public static String formatErrorMessage( String errorMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(errorMessage);
            if (rootNode.isArray()) {
                return formatArrayErrorMessage(rootNode);
            }
            return rootNode.asText();
        } catch (Exception e) {
            return errorMessage;
        }

    }

    private static String formatArrayErrorMessage(JsonNode arrayNode) {
        StringBuilder formattedErrorMessage = new StringBuilder();
        for(JsonNode node : arrayNode){
            formattedErrorMessage.append("- ").append(node.get("field").asText()).append("\n");
        }
        return formattedErrorMessage.toString();
    }
}