package com.app.trinsi.service;

import org.apache.maven.shared.invoker.MavenInvocationException;

import java.io.IOException;

public interface RulesService {
    void add(String drl) throws IOException, MavenInvocationException;
}
