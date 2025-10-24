package com.company.project.data;

public record ExtractPayload(
    String url,
    String cssSelector,
    String urlSelector,
    String titleSelector,
    String contentSelector
) {
}