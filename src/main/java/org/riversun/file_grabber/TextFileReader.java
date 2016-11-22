/*  file-grabber Java lib for easy to handle and manipulate the text file
 *
 *  Copyright (c) 2006-2016 Tom Misawa, riversun.org@gmail.com
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a
 *  copy of this software and associated documentation files (the "Software"),
 *  to deal in the Software without restriction, including without limitation
 *  the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *  and/or sell copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 *  DEALINGS IN THE SOFTWARE.
 *  
 */
package org.riversun.file_grabber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Read text from file/inputStream easily
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public class TextFileReader {

	/**
	 * Read whole text char by char
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String readTextCharByChar(File file) throws IOException {
		return readTextCharByChar(file, null);
	}

	/**
	 * Read whole text from file char by char
	 * 
	 * @param file
	 * @param charset
	 *            specify character set like 'UTF-8'
	 * @return
	 * @throws IOException
	 */
	public String readTextCharByChar(File file, String charset) throws IOException {

		FileInputStream fis = null;

		fis = new FileInputStream(file);

		return readTextCharByChar(fis, charset);

	}

	/**
	 * Read whole text from input stream char by char
	 * 
	 * @param is
	 * @param charset
	 *            specify character set like 'UTF-8'
	 * @return
	 * @throws IOException
	 */
	public String readTextCharByChar(InputStream is, String charset) throws IOException {

		final StringBuilder sb = new StringBuilder();

		InputStreamReader isr = null;
		BufferedReader br = null;

		try {

			if (isNotBlank(charset)) {
				isr = new InputStreamReader(is, charset);
			} else {
				isr = new InputStreamReader(is);
			}

			br = new BufferedReader(isr);

			int iChar = br.read();

			while (iChar != -1) {
				sb.append((char) iChar);
				iChar = br.read();
			}

		} finally {

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return sb.toString();

	}

	/**
	 * Read whole text from file line by line
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String readText(File file) throws IOException {
		return readText(file, null);
	}

	/**
	 * Read whole text from file line by line
	 * 
	 * @param file
	 * @param charset
	 *            specify character set like 'UTF-8'
	 * @return
	 * @throws IOException
	 */
	public String readText(File file, String charset) throws IOException {
		return readTextCharByChar(file, charset);
	}

	/**
	 * Read whole text as list from file line by line
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public List<String> readTextAsList(File file) throws IOException {
		return readTextAsList(file, null);
	}

	/**
	 * Read whole text as list from file line by line
	 * 
	 * @param file
	 * @param charset
	 *            specify character set like 'UTF-8'
	 * @return
	 * @throws IOException
	 */
	public List<String> readTextAsList(File file, String charset) throws IOException {

		FileInputStream fis = new FileInputStream(file);

		return readTextAsList(fis, charset);

	}

	/**
	 * Read whole text as list from inputStream line by line
	 * 
	 * @param is
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public List<String> readTextAsList(InputStream is, String charset) throws IOException {

		final List<String> lineList = new ArrayList<String>();

		InputStreamReader isr = null;
		BufferedReader br = null;

		try {

			if (isNotBlank(charset)) {
				isr = new InputStreamReader(is, charset);
			} else {
				isr = new InputStreamReader(is);
			}

			br = new BufferedReader(isr);

			String line;

			while ((line = br.readLine()) != null) {
				lineList.add(line);
			}

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return lineList;
	}

	/**
	 * Returns true if string is not NULL and if length greater than 0.
	 * 
	 * @param str
	 * @return
	 */
	private boolean isNotBlank(String str) {

		if (str != null && !str.isEmpty()) {
			return true;
		}
		return false;
	}
}