package com.kingz.utils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtils {
 /** Returns an immutable copy of {@code map}. */
  public static <K, V> Map<K, V> immutableMap(Map<K, V> map) {
    return map.isEmpty()
        ? Collections.<K, V>emptyMap()
        : Collections.unmodifiableMap(new LinkedHashMap<>(map));
  }
}
