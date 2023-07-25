package geodatahubback.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


/**
 * @Description
 * @Auther wyjq
 * @Date 2023/2/15
 **/
public class ParseXMLToJSON {
    public static JSONObject parse(String data)
    {
        try
        {

            // Create a new XmlMapper to read XML tags
            XmlMapper xmlMapper = new XmlMapper();

            //Reading the XML
            JsonNode jsonNode = xmlMapper.readTree(data.getBytes());

            //Create a new ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            //Get JSON as a string
            String jsonStr = objectMapper.writeValueAsString(jsonNode);

//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            JsonElement je = JsonParser.parseString(jsonStr);
//            jsonStr = gson.toJson(je);

            return JSONObject.parseObject(jsonStr);

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
