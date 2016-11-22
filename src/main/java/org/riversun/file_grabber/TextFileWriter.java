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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Write text to file/stream easily
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public class TextFileWriter {

	private static final String UTF_8 = "UTF-8";

	private final String NEW_LINE = System.getProperty("line.separator");

	/**
	 * Write text to TEXT file as 'UTF-8' text
	 * 
	 * @param file
	 * @param lines
	 * @param append
	 */
	public boolean writeText(File file, String text, boolean append) {
		return writeText(file, text, UTF_8, append);
	}

	/**
	 * Write lines(List<String>) to TEXT file as 'UTF-8' text
	 * 
	 * @param file
	 * @param lines
	 * @param append
	 */
	public boolean writeLines(File file, List<String> lines, boolean append) {
		return writeLines(file, lines, UTF_8, append);
	}

	/**
	 * Write lines(List<String>) to TEXT file
	 * 
	 * @param file
	 * @param lines
	 * @param charset
	 * @param append
	 */
	public boolean writeLines(File file, List<String> lines, String charset, boolean append) {
		final StringBuilder sb = new StringBuilder();

		for (String line : lines) {
			sb.append(line);
			sb.append(NEW_LINE);
		}

		return writeText(file, sb.toString(), charset, append);
	}

	/**
	 * Write text to TEXT file
	 * 
	 * @param file
	 * @param text
	 * @param charset
	 * @param append
	 */
	public boolean writeText(File file, String text, String charset, boolean append) {

		if (file == null) {
			return false;
		}

		FileOutputStream fos = null;

		try {

			fos = new FileOutputStream(file, append);
			writeTextToStream(fos, text, charset);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;

		} finally {

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}

		return true;

	}

	/**
	 * Write text to Stream
	 * 
	 * @param os
	 * @param text
	 * @param charset
	 * @return
	 */
	public boolean writeTextToStream(OutputStream os, String text, String charset) {

		if (os == null) {
			return false;
		}

		OutputStreamWriter osw = null;
		BufferedWriter bw = null;

		try {

			osw = new OutputStreamWriter(os, charset);
			bw = new BufferedWriter(osw);

			bw.write(text);
			bw.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {

			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
				}
			}

			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
				}
			}

		}
		return true;
	}
}