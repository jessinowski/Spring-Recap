package com.github.springrecap;

import lombok.With;

@With
public record ToDo(String id, String description, Status status) {
}
