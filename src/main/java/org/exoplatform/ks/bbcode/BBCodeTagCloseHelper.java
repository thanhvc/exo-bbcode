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
import java.util.ArrayList;
import java.util.List;

public class BBCodeTagCloseHelper {

  private BBCodeParser parser;

  private List<String>         tagStack          = new ArrayList<String>();

  private List<String>         alreadyClosedList = new ArrayList<String>();

  /*
   * - Starting a tag is always legal (can all tags be nested?) 
   * - Closing a tag
   * - If tag specified matches most recently opened tag, close it. 
   * - Elseif  most recently started tag != tag specified 
   * - If tag specified is in the tag close ignore list, remove from tag close ignore list 
   * - Elseif tag specified was opened at some point, just not most recently: 
   * - Close all tags in the stack up to and including the specified tag 
   * - For all tags that were closed other than the specified tag, add to tag close ignore list 
   * - Elseif tag  specified was never opened, execute badTag(....) 
   * - Consider reopening some tags?
   */

  public BBCodeTagCloseHelper(BBCodeParser parser) {
    this.parser = parser;
  }

  public void openTag(String tag) {
    tagStack.add(tag);
  }

  public void closeTag(String tag) {
    int alreadyClosedIndex = alreadyClosedList.indexOf(tag);
    if (alreadyClosedIndex != -1) {
      alreadyClosedList.remove(alreadyClosedIndex);
      // throw tag away
    } else if (tagStack.isEmpty()) {
      // throw tag away?
    } else if (tag.equals(tagStack.get(tagStack.size() - 1))) {
      parser.processCloseTag(tag);
      tagStack.remove(tagStack.size() - 1);
    } else if (tagStack.lastIndexOf(tag) != -1) {
      for (int currentIndex = tagStack.size() - 1; currentIndex > -1; currentIndex--) {
        String currentTag = (String) tagStack.get(currentIndex);
        parser.processCloseTag(currentTag);
        tagStack.remove(currentIndex);
        if (currentTag.equals(tag)) {
          break;
        } else {
          alreadyClosedList.add(currentTag);
        }
      }
    }
  }

  public void endDocument() {
    for (int currentIndex = tagStack.size() - 1; currentIndex > -1; currentIndex--) {
      String currentTag = (String) tagStack.get(currentIndex);
      parser.processCloseTag(currentTag);
      tagStack.remove(currentIndex);
    }
  }

}
