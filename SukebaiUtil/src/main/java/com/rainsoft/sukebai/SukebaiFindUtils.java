package com.rainsoft.sukebai;

import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SukebaiFindUtils {
	public final static String SUKEBEI_URL = "http://sukebei.nyaa.se/?page=search&cats=0_0&filter=0&term=";
	public final static String DASH = "-";

	public static Collection<String> searchByFileName(final String name) {
		final TreeSet<String> set = new TreeSet<String>();
		final String url = SUKEBEI_URL + name;
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (final IOException e) {
			throw new AppException(AppException.URL_ERROR, "Error in reaching " + url, e);
		}

		// multifile cases
		final Elements elems1 = doc.select("td.tlistname");
		for (final Element elem : elems1) {
			set.add(elem.text());
		}

		// single file case
		final Elements elems2 = doc
				.select("#main > div.content.remake > div > table.viewtable > tbody > tr:nth-child(2) > td.viewtorrentname");
		for (final Element elem : elems2) {
			set.add(elem.text());
		}
		return set;
	}

	public static String separateAlphaNumber(final String name) {
		final Pattern pattern = Pattern.compile("(?<Alpha>[a-zA-z]*)(?<Nothing>\\W*+)(?<Numeric>[0-9]*)");
		final Matcher m = pattern.matcher(name);
		String result = name;
		if (m.matches()) {
			result = m.group("Alpha") + DASH + m.group("Numeric");
		}
		return result;
	}

}
