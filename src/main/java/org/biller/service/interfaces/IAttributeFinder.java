package org.biller.service.interfaces;

import java.util.Optional;

@FunctionalInterface
public interface IAttributeFinder<T> {

  Optional<T> find(String... x);
}
