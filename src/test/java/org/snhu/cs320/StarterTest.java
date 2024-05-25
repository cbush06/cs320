package org.snhu.cs320;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StarterTest {
	
	@Test
	void test() {
		Starter starter = new Starter();
		assertEquals(4, starter.add(2, 2));
	}
	
}
