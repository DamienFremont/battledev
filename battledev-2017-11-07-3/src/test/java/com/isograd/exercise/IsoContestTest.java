package com.isograd.exercise;

import org.junit.*;
import common.*;

public class IsoContestTest {

	@Test
	public void test_case_01() {
		TestCase.execute("1", () -> {
			try {
				IsoContest.main(new String[] { "-debug" });
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Test
	public void test_case_02() {
		TestCase.execute("2", () -> {
			try {
				IsoContest.main(new String[] { "-debug" });
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Test
	public void test_case_03() {
		TestCase.execute("3", () -> {
			try {
				IsoContest.main(new String[] { "-debug" });
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

}
