/*
 GNU LESSER GENERAL PUBLIC LICENSE
 Version 3, 29 June 2007

 Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.


 This version of the GNU Lesser General Public License incorporates
 the terms and conditions of version 3 of the GNU General Public
 License
 */
package com.jalios.ejpt.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 * 
 * @author Xuan Tuong LE - lxtuong@gmail.com
 *
 */
class InternalEntityResolver implements EntityResolver {
  private final HashMap<String, String> dtdMap = new HashMap<String, String>();

  public InternalEntityResolver() {
    dtdMap.put("-//JALIOS//DTD JCMS-PLUGIN 1.0//EN", "jcms-plugin-1.0.dtd");
    dtdMap.put("-//JALIOS//DTD JCMS-PLUGIN 1.1//EN", "jcms-plugin-1.1.dtd");
    dtdMap.put("-//JALIOS//DTD JCMS-PLUGIN 1.2//EN", "jcms-plugin-1.2.dtd");
    dtdMap.put("-//JALIOS//DTD JCMS-PLUGIN 1.3//EN", "jcms-plugin-1.3.dtd");
    dtdMap.put("-//JALIOS//DTD JCMS-PLUGIN 1.4//EN", "jcms-plugin-1.4.dtd");
    dtdMap.put("-//JALIOS//DTD JCMS-PLUGIN 1.5//EN", "jcms-plugin-1.5.dtd");
    dtdMap.put("-//JALIOS//DTD JCMS-PLUGIN 1.6//EN", "jcms-plugin-1.6.dtd");
  }

  public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
    String filename = dtdMap.get(publicId);
    if (filename == null) {
      throw new IOException("Unknown dtd specification : " + publicId);
    }

    InputStream is = this.getClass().getResourceAsStream("/" + filename);
    if (is == null) {
      throw new IOException("internal DTD file not found with filename " + filename);
    }

    return new InputSource(is);
  }
}
