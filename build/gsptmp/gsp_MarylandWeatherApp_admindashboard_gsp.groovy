import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_MarylandWeatherApp_admindashboard_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/admin/dashboard.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("admin_layout")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',5,['src':("jquery-ui.min.css")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
createClosureForHtmlPart(4, 2)
invokeTag('link','g',25,['action':("downloadFile"),'id':("xls")],2)
printHtmlPart(5)
createClosureForHtmlPart(6, 2)
invokeTag('link','g',30,['action':("downloadFile"),'id':("xlsx")],2)
printHtmlPart(7)
invokeTag('textField','g',39,['name':("name"),'class':("form-control"),'placeholder':("State"),'ng-model':("saveName")],-1)
printHtmlPart(8)
invokeTag('textField','g',42,['name':("city"),'class':("form-control"),'placeholder':("Capital City"),'ng-model':("saveCity")],-1)
printHtmlPart(8)
invokeTag('textField','g',45,['name':("lgas"),'class':("form-control"),'placeholder':("Number of LGAs"),'ng-model':("saveLgas")],-1)
printHtmlPart(8)
invokeTag('textField','g',48,['name':("latitude"),'class':("form-control"),'placeholder':("Latitude"),'ng-model':("saveLat")],-1)
printHtmlPart(8)
invokeTag('textField','g',51,['name':("longitude"),'class':("form-control"),'placeholder':("Longitude"),'ng-model':("saveLng")],-1)
printHtmlPart(9)
invokeTag('textField','g',97,['name':("name"),'class':("form-control"),'placeholder':("State"),'ng-model':("editName")],-1)
printHtmlPart(10)
invokeTag('textField','g',102,['name':("city"),'class':("form-control"),'placeholder':("Capital City"),'ng-model':("editCity")],-1)
printHtmlPart(10)
invokeTag('textField','g',106,['name':("lgas"),'class':("form-control"),'placeholder':("Number of LGAs"),'ng-model':("editLgas")],-1)
printHtmlPart(10)
invokeTag('textField','g',109,['name':("latitude"),'class':("form-control"),'placeholder':("Latitude"),'ng-model':("editLat")],-1)
printHtmlPart(10)
invokeTag('textField','g',113,['name':("longitude"),'class':("form-control"),'placeholder':("Longitude"),'ng-model':("editLng")],-1)
printHtmlPart(11)
invokeTag('hiddenField','g',117,['name':("id"),'ng-model':("editId")],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',118,['name':("index"),'ng-model':("editIndex")],-1)
printHtmlPart(13)
invokeTag('javascript','asset',124,['src':("app/angularjs/app.module.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',126,['src':("app/angularjs/app.controller.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',126,['src':("app/angularjs/app.service.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',127,['src':("lib/jquery-ui.min.js")],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',127,[:],1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1489690559000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
