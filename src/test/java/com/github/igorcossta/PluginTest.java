package com.github.igorcossta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PluginTest {

  @Test
  @DisplayName("Always pass")
  void onEnableShouldPass() {
    boolean isTrue = true;
    assertTrue(isTrue);
  }

  @ParameterizedTest
  @ValueSource(booleans = false)
  @DisplayName("Always fail")
  void onEnableShouldFail(boolean isTrue) {
    assertTrue(isTrue);
  }
}
