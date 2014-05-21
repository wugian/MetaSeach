
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta name="robots" content="index,follow">
<meta http-equiv="expires" content="0">
<meta name="description"
content="MetaCrawler - Metasearch.">
<meta name="KEYWORDS"
content="metacrawler,meta,metasearch,metasearchengine,search,searchengine">
<title>MetaCrawler - Metasearch.</title>
<script language="JavaScript" type="text/javascript" src="SearchOptions.js"></script>
<script language="JavaScript">
<!--
function Fokus()
{
	document.getElementById('search').focus();
}

function SetupSearchOptions()
{
	var NrOfResults = 30;
	var Type = 'de';

	// Cookie holen
	var strCookie = GetCookie('metacrawlercouk_search_options');
	if(strCookie)
	{
		var NrOfResults = strCookie.substring(0, strCookie.indexOf(','))
		var Type = strCookie.substring(strCookie.indexOf(',')+1, strCookie.length)
	
		// Werte setzen
		if(NrOfResults >= 10 && NrOfResults <= 100) {
			document.getElementById('form1').per_page.value = NrOfResults;
		}
		if(Type == 'int') {
			document.getElementById('TypeINT').checked = true;
		}
		else {
			document.getElementById('TypeDE').checked = true;
		}
	}
}
//-->
</script>

<style type="text/css">
<!--
/* querybox */
#querybox {
	text-align:left;
	clear:both;
	height: 150px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	border: 1px none #669999;
	width: 430px;
	float: none;
	margin-right: auto;
	margin-left: auto;
	line-height: 20px;
}


#topbox {
	text-align:center;
	border-top:1px solid #707070;
	clear:left;
	height: 50px;
	padding-right: 0px;
	padding-left: 0px;
	margin-top: 1px;
}


#botbox {
	text-align:center;
	border-top:1px none #707070;
	clear:both;
	height: 200px;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #707070;
}

/* Die Titelzeile */
#title {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #666666;
	padding-top: 47px;
	clear: none;
	float: none;
	text-align: right;
}

a.title:link, a.title:visited
{
	color:#666666;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	text-decoration: none;
}


/* Die footerzeile */
a.footie:link, a.footie:visited
{
	color:#666666;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
}




#footie {
	height: 15px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #666666;
	float: none;
	clear: both;
}


.formtype {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	margin-top: 6px;
}
.style3 {
	color: #666666;
	font-weight: bold;
}
#statbox {
	text-align:left;
	clear:both;
	height: 150px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	border: 1px none #669999;
	width: 430px;
	float: none;
	margin-right: auto;
	margin-left: auto;
	line-height: 20px;
	color: #666666;
}
.kleingrau {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: #666666;
}
.uschr2 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #000000;
	font-weight: bold;
}
a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	color: #0000FF;
}
h1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	font-style: normal;
	font-weight: bold;
	color: #000000;
	display: inline;
}
h2 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
	display: inline;
	color: #666666;
	font-style: normal;
	font-weight: normal;
	font-variant: normal;
}
-->
</style>
</head>

<body onload="SetupSearchOptions(); Fokus();" style="text-align:center;">

<!--  alles -->
<div style="width:760px;margin:0px auto">

<!--  logo u date  -->

<a href="http://www.metacrawler.co.uk/"><img border="0" src="log.jpg" alt="metacrawler" width="195" height="60" style="float:left;width:195px; height:60px;" /></a>
<div id="title"><script type="text/javascript" src="http://www.metacrawler.de/cgi-bin/uz_uk.pl"></script></div>

<!--  platz  -->

<div id="topbox">

</div>

<!--  query  -->
<div id="querybox"> 
<form name="form1" id="form1" method="get" action="http://85.214.60.244">
  
  <h1>Metasearch: </h1>
  [ <span class="style3">Web</span> | <a href="http://213.133.108.102/news-metasuche/">News</a> | <a href="http://213.133.108.102/auction/auctions.html">Auctions</a> | <a href="http://www.metacrawler.co.uk/setup.htm">Search options</a> ]
      <br>
      <input name="qry" type="text" class="formtype" id="search" size="45" maxlength="256">      
		  <input type="hidden" name="per_page" value="20" id="per_page">
	  
	  <input type="submit" name="Submit" value="SEARCH">
      <span class="tester">&nbsp;</span><br> 
    
    <input type="radio" name="catg" value="webint" id="TypeINT" checked>
                        international results 
                        <input type="radio" name="catg" value="web" id="TypeDE">
                        results in English
    </form>


</div>



<div id="botbox">
<br>
<br>
<br>
<br>
<br>





</div>


<div id="footie">
  <div align="left"> 
    &copy;2005 <h2>MetaCrawler - Metasearch</h2>
    - [ <a href="http://www.metacrawler.co.uk/imprint.htm" class="footie">Corporate
    Info</a>    | <a href="http://www.metacrawler.co.uk/ads.htm" class="footie">Advertise
    with metacrawler</a> | <a href="http://www.metacrawler.co.uk/help.htm" class="footie">Help</a>
| <a href="http://www.metacrawler.de" class="footie">Metasuchmaschine</a> ]    
  </div>
 
</div>
</div>



</body>
</html>
