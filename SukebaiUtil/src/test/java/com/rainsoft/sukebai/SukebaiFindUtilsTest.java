package com.rainsoft.sukebai;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

public class SukebaiFindUtilsTest {

	@Test
	public void testSearchByFileName() {
		final Collection<String> restult = SukebaiFindUtils.searchByFileName("IPTD-777");
		for (final String string : restult) {
			System.out.println(string);
		}
		final String result2 = SukebaiFindUtils.separateAlphaNumber("IPTD777");
		Assert.assertEquals("IPTD-777", result2);
		final String result3 = SukebaiFindUtils.separateAlphaNumber("IPTD-777");
		Assert.assertEquals("IPTD-777", result3);
	}

}
