import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_MarylandWeatherApp_adminindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/admin/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("admin_layout")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',5,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(4)
invokeTag('textField','g',12,['name':("username"),'class':("form-control"),'placeholder':("Username"),'required':("requried")],-1)
printHtmlPart(5)
invokeTag('passwordField','g',15,['name':("password"),'class':("form-control"),'placeholder':("Password"),'required':("requried")],-1)
printHtmlPart(6)
invokeTag('submitButton','g',17,['name':("login"),'value':("Login"),'class':("btn btn-block btn-primary")],-1)
printHtmlPart(7)
})
invokeTag('form','g',18,['action':("authenticate")],2)
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('link','g',24,['url':("/")],2)
printHtmlPart(10)
invokeTag('javascript','asset',27,['src':("app/login.js")],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',28,[:],1)
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1489693512000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
