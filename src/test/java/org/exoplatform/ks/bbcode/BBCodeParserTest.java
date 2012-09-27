/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.ks.bbcode;

import java.io.StringReader;

import junit.framework.TestCase;

/**
 * Created by The eXo Platform SAS
 * Author : thanh_vucong
 *          thanh_vucong@exoplatform.com
 * Sep 27, 2012  
 */
public class BBCodeParserTest extends TestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }
  
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }
  
  private DefaultBBCodeParser newParser(String input) {
    return new DefaultBBCodeParser(new StringReader(input));
  }
  
  public void testItalic() throws Exception {
    String bbCode = "[i] my italic test [/i]";
    DefaultBBCodeParser parser = newParser(bbCode);
    String got = parser.process();
    assertEquals("<i> my italic test </i>", got);
    
    //UPPER CASE
    {
    bbCode = "[I] my italic test [/I]";
    parser = newParser(bbCode);
    got = parser.process();
    assertEquals("<i> my italic test </i>", got);
    }
    
    //OTHER CASE 1
    {
    bbCode = "[i] my italic test [/I]";
    parser = newParser(bbCode);
    got = parser.process();
    assertEquals("<i> my italic test </i>", got);
    }
    
    //OTHER CASE 2
    {
    bbCode = "[I] my italic test [/i]";
    parser = newParser(bbCode);
    got = parser.process();
    assertEquals("<i> my italic test </i>", got);
    }
    
  }
  
  public void testBold() throws Exception {
    String bbCode = "[b] my bold test [/b]";
    DefaultBBCodeParser parser = newParser(bbCode);
    String got = parser.process();
    assertEquals("<b> my bold test </b>", got);
    
    //UPPER CASE
    {
    bbCode = "[B] my bold test [/B]";
    parser = newParser(bbCode);
    got = parser.process();
    assertEquals("<b> my bold test </b>", got);
    }
    
    //OTHER CASE 1
    {
    bbCode = "[b] my bold test [/B]";
    parser = newParser(bbCode);
    got = parser.process();
    assertEquals("<b> my bold test </b>", got);
    }
    
    //OTHER CASE 2
    {
    bbCode = "[B] my italic test [/b]";
    parser = newParser(bbCode);
    got = parser.process();
    assertEquals("<i> my bold test </i>", got);
    }
  }
  
  public void testQuote() throws Exception {
    String bbCode = "[quote] my bold test [/quote]";
    DefaultBBCodeParser parser = newParser(bbCode);
    String got = parser.process();
    assertEquals("<quote>my bold test</quote>", got);
  }
  
  public void testCode() throws Exception {
    String bbCode = "aa [code] my bold test [/code] bc";
    DefaultBBCodeParser parser = newParser(bbCode);
    String got = parser.process();
    assertEquals("aa<code>my bold test</code>bc", got);
  }
  
  public void testColor() throws Exception {   
    String bbCode = "[color=red]Red Text[/color]";
    DefaultBBCodeParser parser = newParser(bbCode);
    String got = parser.process();
    assertEquals("<span style='color:red'>Red Text</span>", got);
  }

  public void testSize() throws Exception {
    String bbCode = "[size=15]Red Text[/size]";
    DefaultBBCodeParser parser = newParser(bbCode);
    String got = parser.process();
    assertEquals("<span style='font-size:15px'>Red Text</span>", got);
  
  }
  
  public void testUrlWww() throws Exception {
    String bbCode = "[url]http://example.com[/url]";
    DefaultBBCodeParser parser = newParser(bbCode);
    String got = parser.process();
    assertEquals("<a href='http://example.com'>http://example.com</a>", got);
  }
  
  public void testUrlFtp() throws Exception {
    String bbCode = "[url]ftp://example.com/file-explorer[/url]";
    DefaultBBCodeParser parser = newParser(bbCode);
    String got = parser.process();
    assertEquals("<a href='ftp://example.com/file-explorer'>ftp://example.com/file-explorer</a>", got);
  }
  
}
