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

/**
 * Created by The eXo Platform SAS
 * Author : thanh_vucong
 *          thanh_vucong@exoplatform.com
 * Sep 27, 2012  
 */
public class DefaultBBCodeParser extends BBCodeParser {

  private StringBuffer sb = new StringBuffer();

  // CONSTRUCTORS
  public DefaultBBCodeParser(java.io.InputStream stream) {
      super(stream);
  }

  public DefaultBBCodeParser(java.io.Reader stream) {
      super(stream);
  }

  public String process() throws ParseException {
     Input();
     return sb.toString();
  }
  
  protected void processWord(String s)   { sb.append(s); }
  protected void processSpace(String s)  { sb.append(s); }
  protected void processEol(String s)    { sb.append(s); }
  protected void processEmail(String s)  { addHref("mailto:" + s, s); }
  protected void processUrl(String s)    { addHref(s, s); }
  protected void processImgUrl(String s) { addImage(s); }
  protected void processFtp(String s)    { addHref("ftp://" + s, s); }
  protected void processWww(String s)    { addHref("http://" + s, s); }

  protected void processInvalidOpen(String s) { sb.append(s); }
  protected void processOpenSimpleTag(String s) { sb.append("<" + s.toLowerCase() + ">"); }
  protected void processCloseTag(String s) { sb.append("</" + s.toLowerCase() + ">"); }

  protected void processOpenUrlFtpTag(String s) { processOpenUrlTag("ftp://" + s); }
  protected void processOpenUrlWwwTag(String s) { processOpenUrlTag("http://" + s); }
  protected void processOpenUrlEmailTag(String s) { processOpenUrlTag("mailto:" + s); }

  protected void processOpenUrlTag(String s) { sb.append("<a href='" + s + "'>"); }
  protected void processOpenColorTag(String s) { sb.append("<span style='color:" + s + "'>"); }
  protected void processOpenSizeTag(String s) { sb.append("<span style='font-size:" + s + "px'>"); }

  private void addHref(String href, String display) {
      sb.append("<a href='" + href + "'>" + display + "</a>");
  }
  
  private void addImage(String href) {
    sb.append("<img src='" + href + "' alt=''></img>");
  }
}
