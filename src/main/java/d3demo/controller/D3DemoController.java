package d3demo.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import org.json.CDL;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class D3DemoController {

	@RequestMapping(value = "/simple")
	public ModelAndView showSimple(ModelMap model) {
		List<String> strings = new ArrayList<String>();
		strings.add("xoxo"); strings.add("xaxa");
		Collections.sort(strings);
		model.addAttribute("simpleStrings", strings);
		return new ModelAndView("simple", model);
	}
	
	@RequestMapping("/static")
	public String staticD3() {
		return "redirect:/staticD3.html";
	}
	
	@RequestMapping("/LoadCsvToD3BarChart")
	public String staticCsvForD3() {
		return "/LoadCsvToD3BarChart";
	}

	@RequestMapping("/staticGroupedJson")
	public String staticGroupJson() {
		return "redirect:/staticGroupedSeries.html";
	}
	
	
	/* Possibilities:
	 *   https://info.michael-simons.eu/2019/01/31/using-thymeleaf-inline-javascript-with-d3-js/
	 *   https://stackoverflow.com/questions/42529000/populating-the-json-data-from-spring-mvc-to-d3-chart-using-request-body
	 */
	@RequestMapping("/passMeDataNaiveD3")
    public ModelAndView passSimpleNaiveData(ModelMap model) {
		
        Map<Integer, Double> values = new HashMap<Integer, Double>();
        values.put(2020, 135.0);values.put(2021, 125.0);values.put(2022, 45.0);
        
        /* Sort the map: (a) make a sorted AL; (b) put them in a string in order
         * The string also has a "header" with the names of the attributes in each pair.
         * */
        List<Integer> sortedKeys=new ArrayList<Integer>(values.keySet());
        Collections.sort(sortedKeys);
        
        String s ="key, value\n";
        for(Integer i: sortedKeys) {
        	Double v = values.get(i);
        	s = s + i + ", " + v + "\n";
       }
       /* A couple of extra parameters, to show you can pass several items to the thymeleaf */
       String constructedText = "This is some text that accompanies the data sent to the page. "
       		+ "You can generate such a text to describe the query characteristics.";
       String pageTitle = "Bar Chart";
       
       /* Convert the string to json 
        * */
       //JSONObject jo = new JSONObject(values);
       //model.addAttribute("dataMap", jo);
       //System.out.println ("\n\n" + jo.toString() + "\n\n");
        JSONArray result = CDL.toJSONArray(s);
        model.addAttribute("dataMap", result);
        model.addAttribute("description", constructedText);
        model.addAttribute("title", pageTitle);
        return new ModelAndView("passMeDataNaiveD3", model);
    }
	
	@RequestMapping("/multiSeriesJsonFull")
	public ModelAndView multiSeriesJson(ModelMap model) {
		/* Adapted from https://bl.ocks.org/d3noob/8952219 Many thanks!!! */
		String jsonString = "[\r\n"
				+ "    {\r\n"
				+ "        \"categorie\": \"Student\", \r\n"
				+ "        \"values\": [\r\n"
				+ "            {\r\n"
				+ "                \"value\": 0, \r\n"
				+ "                \"rate\": \"Not at all\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 4, \r\n"
				+ "                \"rate\": \"Not very much\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 12, \r\n"
				+ "                \"rate\": \"Medium\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 6, \r\n"
				+ "                \"rate\": \"Very much\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 0, \r\n"
				+ "                \"rate\": \"Tremendously\"\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    }, \r\n"
				+ "    {\r\n"
				+ "        \"categorie\": \"Liberal Profession\", \r\n"
				+ "        \"values\": [\r\n"
				+ "            {\r\n"
				+ "                \"value\": 1, \r\n"
				+ "                \"rate\": \"Not at all\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 21, \r\n"
				+ "                \"rate\": \"Not very much\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 13, \r\n"
				+ "                \"rate\": \"Medium\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 18, \r\n"
				+ "                \"rate\": \"Very much\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 6, \r\n"
				+ "                \"rate\": \"Tremendously\"\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    }, \r\n"
				+ "    {\r\n"
				+ "        \"categorie\": \"Salaried Staff\", \r\n"
				+ "        \"values\": [\r\n"
				+ "            {\r\n"
				+ "                \"value\": 3, \r\n"
				+ "                \"rate\": \"Not at all\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 22, \r\n"
				+ "                \"rate\": \"Not very much\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 6, \r\n"
				+ "                \"rate\": \"Medium\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 15, \r\n"
				+ "                \"rate\": \"Very much\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 3, \r\n"
				+ "                \"rate\": \"Tremendously\"\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    }, \r\n"
				+ "    {\r\n"
				+ "        \"categorie\": \"Employee\", \r\n"
				+ "        \"values\": [\r\n"
				+ "            {\r\n"
				+ "                \"value\": 12, \r\n"
				+ "                \"rate\": \"Not at all\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 7, \r\n"
				+ "                \"rate\": \"Not very much\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 18, \r\n"
				+ "                \"rate\": \"Medium\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 13, \r\n"
				+ "                \"rate\": \"Very much\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 6, \r\n"
				+ "                \"rate\": \"Tremendously\"\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    }, \r\n"
				+ "    {\r\n"
				+ "        \"categorie\": \"Craftsman\", \r\n"
				+ "        \"values\": [\r\n"
				+ "            {\r\n"
				+ "                \"value\": 6, \r\n"
				+ "                \"rate\": \"Not at all\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 15, \r\n"
				+ "                \"rate\": \"Not very much\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 9, \r\n"
				+ "                \"rate\": \"Medium\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 12, \r\n"
				+ "                \"rate\": \"Very much\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 3, \r\n"
				+ "                \"rate\": \"Tremendously\"\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    }, \r\n"
				+ "    {\r\n"
				+ "        \"categorie\": \"Inactive\", \r\n"
				+ "        \"values\": [\r\n"
				+ "            {\r\n"
				+ "                \"value\": 6, \r\n"
				+ "                \"rate\": \"Not at all\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 6, \r\n"
				+ "                \"rate\": \"Not very much\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 6, \r\n"
				+ "                \"rate\": \"Medium\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 2, \r\n"
				+ "                \"rate\": \"Very much\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 3, \r\n"
				+ "                \"rate\": \"Tremendously\"\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    }\r\n"
				+ "]";
        model.addAttribute("dataGiven", jsonString);
		return new ModelAndView("PassJsonToMultiseriesBarChart", model);
	}
	
	@RequestMapping("/genericMultiSeries")
	public ModelAndView multiSeriesGenericJson(ModelMap model) {
		String jsonString = "[\r\n"
				+ "    {\r\n"
				+ "        \"xCoord\": \"Denmark\", \r\n"
				+ "        \"values\": [\r\n"
				+ "            {\r\n"
				+ "                \"value\": 100, \r\n"
				+ "                \"series\": \"1 Stars\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 400, \r\n"
				+ "                \"series\": \"2 Stars\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 1200, \r\n"
				+ "                \"series\": \"3 Stars\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 500, \r\n"
				+ "                \"series\": \"4 Stars\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 0, \r\n"
				+ "                \"series\": \"5 Stars\"\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    }, \r\n"
				+ "    {\r\n"
				+ "        \"xCoord\": \"Belgium\", \r\n"
				+ "        \"values\": [\r\n"
				+ "            {\r\n"
				+ "                \"value\": 150, \r\n"
				+ "                \"series\": \"1 Stars\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 210, \r\n"
				+ "                \"series\": \"2 Stars\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 1300, \r\n"
				+ "                \"series\": \"3 Stars\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 160, \r\n"
				+ "                \"series\": \"4 Stars\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 90, \r\n"
				+ "                \"series\": \"5 Stars\"\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    }, \r\n"
				+ "    {\r\n"
				+ "        \"xCoord\": \"Greece\", \r\n"
				+ "        \"values\": [\r\n"
				+ "            {\r\n"
				+ "                \"value\": 300, \r\n"
				+ "                \"series\": \"1 Stars\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 2000, \r\n"
				+ "                \"series\": \"2 Stars\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 400, \r\n"
				+ "                \"series\": \"3 Stars\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 150, \r\n"
				+ "                \"series\": \"4 Stars\"\r\n"
				+ "            }, \r\n"
				+ "            {\r\n"
				+ "                \"value\": 100, \r\n"
				+ "                \"series\": \"5 Stars\"\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    }\r\n"
				+ "]";
        model.addAttribute("dataGiven", jsonString);
		return new ModelAndView("GenericMultiSeriesBarChart", model);
	}
		
}//end class
