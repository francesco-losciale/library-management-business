package com.frank.capabilities;

public interface Repository {

    Object add(Hydratable hydratable);

    Hydratable get(Object id);
}
