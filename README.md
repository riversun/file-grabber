# file-grabber
Java lib for easy to handle and manipulate the text file

# Read Text from Text File

## Read full text as String
- org.riversun.file_grabber.TextFileReader.readText(File)
- org.riversun.file_grabber.TextFileReader.readText(File, String)
- org.riversun.file_grabber.TextFileReader.readText(InputStream, String)

## Read text from resource (source code resources folder)
- org.riversun.file_grabber.TextFileReader.readTextFromResource(Class<?>, String, String)

```java
import java.io.IOException;
import org.riversun.file_grabber.TextFileReader;

public class TextReaderExample {

	public static void main(String[] args) throws IOException {
		TextReaderExample o = new TextReaderExample();
		o.doTest();
	}

	public void doTest() throws IOException {
		TextFileReader tr = new TextFileReader();
		String text = tr.readTextFromResource(this.getClass(), "test.txt", "UTF-8");
		System.out.println(text);

	}
}

```
 
# Read fulltext char by char

- org.riversun.file_grabber.TextFileReader.readTextCharByChar(File)
- org.riversun.file_grabber.TextFileReader.readTextCharByChar(File, String)
- org.riversun.file_grabber.TextFileReader.readTextCharByChar(InputStream, String)
 
# Read fulltext as List<String> 
- org.riversun.file_grabber.TextFileReader.readTextAsList(File)
- org.riversun.file_grabber.TextFileReader.readTextAsListWithRange(File, int, int)
- org.riversun.file_grabber.TextFileReader.readTextAsList(File, String)
- org.riversun.file_grabber.TextFileReader.readTextAsListWithRange(File, String, int, int)
- org.riversun.file_grabber.TextFileReader.readTextAsList(InputStream, String)
- org.riversun.file_grabber.TextFileReader.readTextAsListWithRange(InputStream, String, int, int)


# Maven
```xml
<dependency>
		<groupId>org.riversun</groupId>
		<artifactId>file-grabber</artifactId>
		<version>0.3.0</version>
</dependency>
```
