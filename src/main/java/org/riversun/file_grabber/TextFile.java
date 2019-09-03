/*
 * file-grabber Java lib for easy to handle and manipulate the text file
 *
 * Copyright (c) 2006-2019 Tom Misawa, riversun.org@gmail.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 * 
 */
package org.riversun.file_grabber;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Read text from file/inputStream easily
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public class TextFile {

  private static final String NEW_LINE = System.getProperty("line.separator");

  /**
   * Read whole text char by char
   * 
   * @param file
   * @return
   * @throws IOException
   */
  public static String readCharByChar(File file) throws IOException {
    return readCharByChar(file, null);
  }

  /**
   * Read whole text from file char by char
   * 
   * @param file
   * @param charset
   *          specify character set like 'UTF-8'
   * @return
   * @throws IOException
   */
  public static String readCharByChar(File file, String charset) throws IOException {

    FileInputStream fis = null;

    fis = new FileInputStream(file);

    return readCharByChar(fis, charset);

  }

  /**
   * Read whole text from input stream char by char
   * 
   * @param is
   * @param charset
   *          specify character set like 'UTF-8'
   * @return
   * @throws IOException
   */
  public static String readCharByChar(InputStream is, String charset) throws IOException {

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
  public static String read(File file) throws IOException {
    return read(file, null);
  }

  /**
   * Read whole text from file char by char
   * 
   * @param file
   * @param charset
   *          specify character set like 'UTF-8'
   * @return
   * @throws IOException
   */
  public static String read(File file, String charset) throws IOException {
    return readCharByChar(file, charset);
  }

  /**
   * Read whole text from file char by char
   * 
   * @param is
   * @param charset
   * @return
   * @throws IOException
   */
  public static String read(InputStream is, String charset) throws IOException {
    return readCharByChar(is, charset);
  }

  /**
   * Read whole text from resource directory as UTF-8
   * 
   * @param fileName
   * @param charset
   * @return
   * @throws IOException
   */
  public static String readFromResource(String fileName) throws IOException {
    return readFromResource(fileName, StandardCharsets.UTF_8.toString());
  }

  /**
   * Read whole text from resource directory
   * 
   * @param fileName
   * @param charset
   * @return
   * @throws IOException
   */
  public static String readFromResource(String fileName, String charset) throws IOException {
    final InputStream is = TextFile.class.getClassLoader().getResourceAsStream(fileName);
    return read(is, charset);
  }

  /**
   * Read whole text from resource directory
   * 
   * @param clazz
   * @param fileName
   * @param charset
   * @return
   * @throws IOException
   *           <br>
   *           <code>
   * Example
   * tr.readFromResource(this.getClass(),"res.properties","UTF-8");
   * </code>
   */
  public static String readFromResource(Class<?> clazz, String fileName, String charset) throws IOException {
    final InputStream is = clazz.getResourceAsStream(fileName);
    return read(is, charset);
  }

  /**
   * Read whole text from resource directory as List<String>
   * 
   * @param clazz
   * @param fileName
   * @param charset
   * @return
   * @throws IOException
   */
  public static List<String> readFromResourceAsList(Class<?> clazz, String fileName, String charset, String newLine) throws IOException {
    String text = readFromResource(clazz, fileName, charset);
    String[] lines = text.split(newLine);
    return Arrays.asList(lines);

  }

  /**
   * Read whole text as list from file line by line
   * 
   * @param file
   * @return
   * @throws IOException
   */
  public static List<String> readAsList(File file) throws IOException {
    return readAsList(file, null);
  }

  /**
   * Read text as list from file line by line with range
   * 
   * @param file
   * @param beginLineNum
   *          line number starts with 0
   * @param endLineNum
   * @return
   * @throws IOException
   */
  public static List<String> readAsListWithRange(File file, int beginLineNum, int endLineNum) throws IOException {
    return readAsListWithRange(file, null, beginLineNum, endLineNum);
  }

  /**
   * Read whole text as list from file line by line
   * 
   * @param file
   * @param charset
   *          specify character set like 'UTF-8'
   * @return
   * @throws IOException
   */
  public static List<String> readAsList(File file, String charset) throws IOException {

    FileInputStream fis = new FileInputStream(file);

    return readAsList(fis, charset);

  }

  /**
   * Read text as list from file line by line with range
   * 
   * @param file
   * @param charset
   *          specify character set like 'UTF-8'
   * @param beginLineNum
   *          line number starts with 0
   * @param endLineNum
   * @return
   * @throws IOException
   */
  public static List<String> readAsListWithRange(File file, String charset, int beginLineNum, int endLineNum) throws IOException {

    FileInputStream fis = new FileInputStream(file);

    return readAsListWithRange(fis, charset, beginLineNum, endLineNum);

  }

  /**
   * Read whole text as list from inputStream line by line
   * 
   * @param is
   * @param charset
   * @return
   * @throws IOException
   */
  public static List<String> readAsList(InputStream is, String charset) throws IOException {
    return readAsListWithRange(is, charset, -1, -1);
  }

  /**
   * Read text as list from inputStream line by line with range
   * 
   * @param is
   * @param charset
   * @param beginLineNum
   *          line number starts with 0
   * @param endLineNum
   * @return
   * @throws IOException
   */
  public static List<String> readAsListWithRange(InputStream is, String charset, int beginLineNum, int endLineNum) throws IOException {

    long crrReadingLineNum = 0;

    final List<String> lineList = new ArrayList<String>();

    if (beginLineNum < 0) {
      beginLineNum = 0;
    }

    if (beginLineNum > endLineNum) {
      return lineList;
    }

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

        boolean isSkip = false;
        boolean isFinish = false;

        if (beginLineNum <= crrReadingLineNum) {
          isSkip = false;
        } else {
          isSkip = true;
        }

        if (crrReadingLineNum <= endLineNum || endLineNum < 0) {
          isFinish = false;
        } else {
          isFinish = true;
        }

        if (isFinish) {
          break;
        }
        if (isSkip) {

        } else {
          lineList.add(line);
        }

        crrReadingLineNum++;

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
   * Write text to file as UTF-8
   * 
   * @param file
   * @param text
   * @return
   */
  public static boolean write(File file, String text) {
    return write(file, text, StandardCharsets.UTF_8.toString(), false);
  }

  /**
   * Write text to TEXT file as 'UTF-8' text
   * 
   * @param file
   * @param lines
   * @param append
   */
  public static boolean write(File file, String text, boolean append) {
    return write(file, text, StandardCharsets.UTF_8.toString(), append);
  }

  /**
   * Write lines(List<String>) to TEXT file as 'UTF-8' text
   * 
   * @param file
   * @param lines
   * @param append
   */
  public static boolean writeLines(File file, List<String> lines, boolean append) {
    return writeLines(file, lines, StandardCharsets.UTF_8.toString(), append);
  }

  /**
   * Write lines(List<String>) to TEXT file
   * 
   * @param file
   * @param lines
   * @param charset
   * @param append
   */
  public static boolean writeLines(File file, List<String> lines, String charset, boolean append) {
    final StringBuilder sb = new StringBuilder();

    for (String line : lines) {
      sb.append(line);
      sb.append(NEW_LINE);
    }

    return write(file, sb.toString(), charset, append);
  }

  /**
   * Write text to TEXT file
   * 
   * @param file
   * @param text
   * @param charset
   * @param append
   */
  public static boolean write(File file, String text, String charset, boolean append) {

    if (file == null) {
      return false;
    }

    FileOutputStream fos = null;

    try {

      fos = new FileOutputStream(file, append);
      writeToStream(fos, text, charset);

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
  public static boolean writeToStream(OutputStream os, String text, String charset) {

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

  /**
   * Write text to TEXT file as UTF8 with BOM
   * 
   * @param file
   * @param text
   * @param charset
   * @param append
   */
  public static boolean writeAsUTF8WithBOM(File file, String text, boolean append) {

    if (file == null) {
      return false;
    }

    FileOutputStream fos = null;

    try {

      fos = new FileOutputStream(file, append);
      // Write BOM
      try {
        fos.write(0xef);
        fos.write(0xbb);
        fos.write(0xbf);
      } catch (IOException e) {
        e.printStackTrace();
      }

      writeToStream(fos, text, StandardCharsets.UTF_8.toString());

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
   * Returns true if string is not NULL and if length greater than 0.
   * 
   * @param str
   * @return
   */
  private static boolean isNotBlank(String str) {

    if (str != null && !str.isEmpty()) {
      return true;
    }
    return false;
  }
}