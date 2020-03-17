test xmlQueryResult :: <sparql xmlns="http://www.w3.org/2005/sparql-results#">
 <head>
  <variable name="o1"/>
  <variable name="o2"/>
  <variable name="s"/>
 </head>
 <results>
  <result>
   <binding name="o1">
    <literal>Alan</literal>
   </binding>
   <binding name="o2">
    <literal>Alan</literal>
   </binding>
   <binding name="s">
    <uri>http://example.org/a</uri>
   </binding>
  </result>
  <result>
   <binding name="o1">
    <uri>http://example.org/b</uri>
   </binding>
   <binding name="o2">
    <literal>Alan</literal>
   </binding>
   <binding name="s">
    <uri>http://example.org/a</uri>
   </binding>
  </result>
  <result>
   <binding name="o1">
    <literal>alan@example.org</literal>
   </binding>
   <binding name="o2">
    <literal>Alan</literal>
   </binding>
   <binding name="s">
    <uri>http://example.org/a</uri>
   </binding>
  </result>
  <result>
   <binding name="o1">
    <literal>alan@example.org</literal>
   </binding>
   <binding name="o2">
    <literal>Alan</literal>
   </binding>
   <binding name="s">
    <uri>http://example.org/a</uri>
   </binding>
  </result>
  <result>
   <binding name="o1">
    <uri>http://example.org/b</uri>
   </binding>
   <binding name="o2">
    <literal>Alan</literal>
   </binding>
   <binding name="s">
    <uri>http://example.org/a</uri>
   </binding>
  </result>
  <result>
   <binding name="o1">
    <uri>http://example.org/b</uri>
   </binding>
   <binding name="o2">
    <uri>http://example.org/b</uri>
   </binding>
   <binding name="s">
    <uri>http://example.org/a</uri>
   </binding>
  </result>
  <result>
   <binding name="o1">
    <uri>http://example.org/b</uri>
   </binding>
   <binding name="o2">
    <literal>alan@example.org</literal>
   </binding>
   <binding name="s">
    <uri>http://example.org/a</uri>
   </binding>
  </result>
 </results>
</sparql>
