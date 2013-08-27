import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlParser {
	public static void main(String[] grean) {
		System.out.println("hello java xml");
		XmlParserJa();
	}

	public static String getXmlString() {
		String encode = "<?xml version='1.0' encoding='UTF-8'?>";
		String root = "<info api='getIncident' status='OK'>";
		String body = "<news id='398655' type='การจราจร' primarysource='Twitter' secondarysource='longdotraffic' starttime='2013-08-26 16:33:44' endtime='2013-08-26 16:48:44'>"
				+ "<media type='map' path='http://longdo.com/t/1994e'/>"
				+ "<title>จราจรติดขัด</title>"
				+ "<description>รถติด ถนนสุขุมวิท ขาออก มุ่งหน้าแยกพระโขนง รถมากเคลื่อนตัวช้า ท้ายอยู่แยกพร้อมพงษ์ http://t.co/vbcuFJXjtH #thtraffic</description>"
				+ "<location type='line'><road name='ถนนสุขุมวิท'></road>"
				+ "<startpoint name='แยกพระโขนง' latitude='13.7131304405499' longitude='100.594145179429'></startpoint>"
				+ "<endpoint name='แยกพร้อมพงษ์' latitude='13.7473496140597' longitude='100.573324330695'></endpoint>"
				+ "08-26 16:41:07.616: I/RequestTask(1431): </location></news>";
		return encode + root + body + body + "</info>";

	}

	public static String getXmlExampleString() {
		String xmlString = "<?xml version='1.0'?>" + "<company>"
				+ "<staff id='1001'>" + "<firstname>yong</firstname>"
				+ "<lastname>mook kim</lastname>"
				+ "<nickname>mkyong</nickname>" + "<salary>100000</salary>"
				+ "</staff>" + "<staff id='2001'>"
				+ "<firstname>low</firstname>"
				+ "<lastname>yin fong</lastname>"
				+ "<nickname>fong fong</nickname>" + "<salary>200000</salary>"
				+ "</staff>" + "</company>";
		return xmlString;
	}

	public static void XmlParserJa() {

		Document doc = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(getXmlString()));
			doc = builder.parse(is);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// optional, but recommended
		// read this -
		// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		System.out.println("Root element :"
				+ doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("news");

		System.out.println("----------------------------" + nList.getLength());

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				String id = eElement.getAttribute("id");
				String type = eElement.getAttribute("type");
				String primarySource = eElement.getAttribute("primarysource");
				String secondarySource = eElement
						.getAttribute("secondarysource");
				String startTime = eElement.getAttribute("starttime");
				String endTime = eElement.getAttribute("endtime");

				String mediaType = eElement.getElementsByTagName("media")
						.item(0).getAttributes().getNamedItem("type")
						.getNodeValue();
				String mediaPath = eElement.getElementsByTagName("media")
						.item(0).getAttributes().getNamedItem("path")
						.getNodeValue();

				String title = eElement.getElementsByTagName("title").item(0)
						.getTextContent();
				String description = eElement
						.getElementsByTagName("description").item(0)
						.getTextContent();
				
				String locationType = eElement.getElementsByTagName("location")
						.item(0).getAttributes().getNamedItem("type")
						.getNodeValue();
				
				String roadName = eElement.getElementsByTagName("road")
						.item(0).getAttributes().getNamedItem("name")
						.getNodeValue();
				
				String startPointName = eElement.getElementsByTagName("startpoint")
						.item(0).getAttributes().getNamedItem("name")
						.getNodeValue();
				
				String startPointLat = eElement.getElementsByTagName("startpoint")
						.item(0).getAttributes().getNamedItem("latitude")
						.getNodeValue();
				
				String startPointLong = eElement.getElementsByTagName("startpoint")
						.item(0).getAttributes().getNamedItem("longitude")
						.getNodeValue();
				
				String endPointName = eElement.getElementsByTagName("endpoint")
						.item(0).getAttributes().getNamedItem("name")
						.getNodeValue();
				
				String endPointLat = eElement.getElementsByTagName("endpoint")
						.item(0).getAttributes().getNamedItem("latitude")
						.getNodeValue();
				
				String endPointLong = eElement.getElementsByTagName("endpoint")
						.item(0).getAttributes().getNamedItem("longitude")
						.getNodeValue();
				
				
				System.out.println("Staff id : " + eElement.getAttribute("id"));
				System.out
						.println("type id : " + eElement.getAttribute("type"));

				System.out.println("title: "
						+ eElement.getElementsByTagName("title").item(0)
								.getTextContent());

				System.out.println("mediaType id : " + mediaType);
				System.out.println("path id : " + mediaPath);

			}
		}

	}// end xml parser
}
